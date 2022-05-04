package com.mss.app.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.mss.app.R
import com.mss.app.navigation.Route
import com.mss.app.ui.components.MssLogo
import com.mss.app.ui.theme.AppTheme
import com.mss.app.ui.theme.Blue25

private val routes = mapOf(
    Route.Series to R.string.series,
    Route.Results to R.string.results,
    Route.Drivers to R.string.drivers,
    Route.Teams to R.string.teams,
    Route.Venues to R.string.venues,
)

@Composable
fun AppDrawer(
    currentRoute: String,
    navigateTo: (Route) -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Surface(
            color = Blue25,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.statusBarsPadding()) {
                MssLogo(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(start = 16.dp, bottom = 8.dp)
                )
            }
        }
        Spacer(Modifier.height(40.dp))
        for ((route, label) in routes)
            DrawerButton(
                label = stringResource(id = label).uppercase(),
                targetRoute = route,
                currentRoute = currentRoute,
                navigateTo = navigateTo,
                closeDrawer = closeDrawer,
            )
    }
}

@Composable
fun DrawerButton(
    label: String,
    targetRoute: Route,
    currentRoute: String,
    navigateTo: (Route) -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelected = targetRoute.value == currentRoute
    val colors = MaterialTheme.colors
    val textColor = if (isSelected)
        colors.primary
    else
        colors.onSurface
    Row {
        Spacer(Modifier.width(60.dp))
        TextButton(
            onClick = {
                navigateTo(targetRoute)
                closeDrawer()
            },
            modifier = modifier
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.h6,
                color = textColor
            )
        }
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    AppTheme {
        Surface {
            AppDrawer(
                currentRoute = Route.Drivers.value,
                navigateTo = {},
                closeDrawer = {},
            )
        }
    }
}