package com.mss.features.team.presentation.ui.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mss.core.domain.SeriesWithDrivers
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.common.LoadingAndErrorWrapper
import com.mss.core.ui.components.info.*
import com.mss.core.ui.data.mock.MockDriverData
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.data.mock.MockSocialData
import com.mss.core.ui.data.mock.MockTeamData
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.infoSubtitleColor
import com.mss.features.team.R
import com.mss.features.team.presentation.ui.info.state.TeamInfoUiState

@Composable
fun TeamInfoScreen(
    viewModel: TeamInfoViewModel,
    modifier: Modifier = Modifier,
    onUiEvent: (UiEvent) -> Unit = {},
) {
    LaunchedEffect(key1 = true) { viewModel.uiEvents.collect(onUiEvent) }
    val uiState by viewModel.uiState.collectAsState()
    TeamInfoScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        modifier = modifier,
    )
}

@Composable
fun TeamInfoScreen(
    uiState: TeamInfoUiState,
    modifier: Modifier = Modifier,
    onAction: (UiAction) -> Unit = {},
) {
    LoadingAndErrorWrapper(
        uiState = uiState,
        onRetry = { onAction(UiAction.Refresh) },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            InfoBlockImage(url = uiState.picture, modifier = Modifier.align(CenterHorizontally))
            Text(
                text = uiState.name.uppercase(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(top = 10.dp)
            )
            ConstructorsBlock(uiState.constructors)
            SeriesBlock(
                firstSeries = uiState.firstSeries,
                otherSeries = uiState.otherSeries,
            )
            SocialBlock(uiState.links)
        }
    }
}

@Composable
@Suppress("unused")
private fun ColumnScope.ConstructorsBlock(constructors: List<String>) {
    if (constructors.isNotEmpty()) {
        Text(
            text = constructors.first(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.infoSubtitleColor,
        )
        ExpandingInfoBlock(
            buttonTitleId = R.string.all_constructors,
            dialogTitleId = R.string.constructors,
            items = constructors,
        )
    }
}

@Composable
@Suppress("unused")
private fun ColumnScope.SeriesBlock(
    firstSeries: SeriesWithDrivers?,
    otherSeries: List<SeriesWithDrivers>,
) {
    if (firstSeries != null) {
        InfoBlock(titleId = R.string.series, value = firstSeries.series.name)
        BlockHeader(titleId = R.string.drivers)
        firstSeries.drivers.forEach { driver ->
            TextRowWithImage(
                text = driver.name,
                imageUrl = driver.picture,
            )
        }
    }
    if (otherSeries.isNotEmpty())
        Text(
            text = stringResource(com.mss.core.ui.R.string.previous),
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.infoSubtitleColor,
        )
    otherSeries.forEach {
        Text(
            text = it.series.name,
            style = MaterialTheme.typography.body2,
        )
        Text(
            text = it.drivers.first().name,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.infoSubtitleColor,
        )
        ExpandingInfoBlock(
            buttonTitleId = R.string.all_drivers,
            dialogTitleId = R.string.drivers,
            items = it.drivers.map { driver -> driver.name },
        )
    }
}

@MultiPreview
@Composable
@Suppress("MagicNumber")
fun PreviewTeamInfoScreen() {
    AppTheme {
        Surface {
            TeamInfoScreen(
                TeamInfoUiState(
                    name = MockTeamData.names.first(),
                    picture = MockTeamData.pictures.first(),
                    constructors = MockTeamData.names.take(5),
                    firstSeries = SeriesWithDrivers(
                        series = MockSeriesData.references[5],
                        drivers = MockDriverData.driverReferences.shuffled().take(3),
                    ),
                    otherSeries = List(2) {
                        SeriesWithDrivers(
                            series = MockSeriesData.references[it],
                            drivers = MockDriverData.driverReferences.shuffled().take(it + 1),
                        )
                    },
                    links = MockSocialData.links,
                    hasData = true,
                    isLoading = false,
                    errorMessageId = null,
                )
            )
        }
    }
}