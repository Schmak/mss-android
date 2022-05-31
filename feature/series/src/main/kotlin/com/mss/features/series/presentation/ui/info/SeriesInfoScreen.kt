package com.mss.features.series.presentation.ui.info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mss.core.domain.LastSeriesChampions
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.common.LoadingAndErrorWrapper
import com.mss.core.ui.components.info.*
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.data.mock.MockSocialData
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.infoSubtitleColor
import com.mss.features.series.R
import com.mss.features.series.presentation.ui.info.state.SeriesInfoUiState

@Composable
fun SeriesInfoScreen(
    viewModel: SeriesInfoViewModel,
    modifier: Modifier = Modifier,
    onUiEvent: (UiEvent) -> Unit = {},
) {
    LaunchedEffect(key1 = true) { viewModel.uiEvents.collect(onUiEvent) }
    val uiState by viewModel.uiState.collectAsState()
    SeriesInfoScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        modifier = modifier,
    )
}

@Composable
fun SeriesInfoScreen(
    uiState: SeriesInfoUiState,
    modifier: Modifier = Modifier,
    onAction: (UiAction) -> Unit = {}
) {
    LoadingAndErrorWrapper(
        uiState = uiState,
        onRetry = { onAction(UiAction.Refresh) },
    ) {
        val series = requireNotNull(uiState.seriesInfo)
        val lastSeriesChampions = uiState.lastChampions
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            InfoBlockImage(series.picture)
            Text(
                text = (series.shortName ?: series.name).uppercase(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(top = 10.dp)

            )
            if (series.shortName != null)
                Text(
                    text = series.name.uppercase(),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.infoSubtitleColor,
                )
            InfoBlock(titleId = R.string.category, value = series.category)
            InfoBlock(titleId = R.string.affiliation, value = series.organisation)
            InfoBlock(titleId = R.string.established, value = series.firstSeason)
            if (lastSeriesChampions != null)
                LastSeriesChampions(lastSeriesChampions)
            SocialBlock(uiState.links)
        }
    }
}

@Composable
private fun LastSeriesChampions(lastSeriesChampions: LastSeriesChampions) {
    Spacer(modifier = Modifier.height(20.dp))
    BlockHeader(titleId = R.string.current_champions)
    lastSeriesChampions.drivers.forEach {
        TextRowWithImage(text = it.name, imageUrl = it.picture)
    }
    InfoBlock(
        titleId = R.string.current_team_champion,
        value = lastSeriesChampions.team?.name
    )
}

@MultiPreview
@Composable
fun PreviewSeriesInfoScreen() {
    AppTheme {
        Surface {
            SeriesInfoScreen(
                uiState = SeriesInfoUiState(
                    seriesInfo = MockSeriesData.series,
                    lastChampions = MockSeriesData.lastSeriesChampions,
                    links = MockSocialData.links,
                    hasData = true,
                    errorMessageId = null,
                    isLoading = false,
                )
            )
        }
    }
}