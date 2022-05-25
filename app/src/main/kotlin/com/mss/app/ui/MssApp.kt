package com.mss.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mss.app.ui.components.TopBar
import com.mss.core.ui.navigation.NavGraph
import com.mss.core.ui.navigation.Route
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.Transparent
import kotlinx.coroutines.launch

@Composable
fun MssApp() {
    AppTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Transparent, darkIcons = false)
            }

            val coroutineScope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(DrawerValue.Closed)

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: Route.Series.value
            ModalDrawer(
                drawerContent = {
                    AppDrawer(
                        currentRoute = currentRoute,
                        navigateTo = {
                            navController.navigate(it.value) {
                                popUpTo(Route.Series.value) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        closeDrawer = { coroutineScope.launch { drawerState.close() } },
                    )
                },
                drawerState = drawerState,
            ) {
                Scaffold(
                    topBar = {
                        TopBar(
                            modifier = Modifier.statusBarsPadding(),
                            onMenuClick = { coroutineScope.launch { drawerState.open() } },
                            onSearchClick = {},
                        )
                    }
                ) { paddingValues ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}