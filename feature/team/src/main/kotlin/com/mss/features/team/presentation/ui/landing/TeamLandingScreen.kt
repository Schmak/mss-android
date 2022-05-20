package com.mss.features.team.presentation.ui.landing

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.landing.LandingScreen
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.data.mock.MockTeamData
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.team.presentation.ui.landing.state.TeamLandingModelState

@Composable
fun TeamLandingScreen(
    viewModel: TeamLandingViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    LandingScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        modifier = modifier
    )
}

@MultiPreview
@Composable
fun PreviewTeamLandingScreen() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = TeamLandingModelState(
                    goldenSeries = MockSeriesData.goldenSeries,
                    collections = Collections.values().toList(),
                    currentSeasonTeams = MockTeamData.currentSeasonTeams.asPageFlow(),
                    champions = MockTeamData.champions.asPageFlow(),
                    winners = MockTeamData.winners.asPageFlow(),
                    collectionTeams = MockTeamData.collectionTeams.asPageFlow(),
                )
                    .toUiState()
            )
        }
    }
}