package com.mss.features.team.presentation.ui.landing

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.landing.LandingScreen
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.data.mock.MockTeamData
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.team.presentation.ui.landing.state.TeamLandingModelState

@MultiPreview
@Composable
fun PreviewTeamLandingScreen() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = TeamLandingModelState(
                    goldenSeries = MockSeriesData.goldenSeries,
                    collections = MockTeamData.collections,
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