package com.mss.features.venue.presentation.ui.info

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mss.core.domain.VenueDetails
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.common.LoadingAndErrorWrapper
import com.mss.core.ui.components.info.ExpandingInfoBlock
import com.mss.core.ui.components.info.InfoBlock
import com.mss.core.ui.components.info.InfoBlockImage
import com.mss.core.ui.components.info.SocialBlock
import com.mss.core.ui.data.mock.MockSocialData
import com.mss.core.ui.data.mock.MockVenueData
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.theme.AppTheme
import com.mss.features.venue.R
import com.mss.features.venue.presentation.ui.info.state.VenueInfoUiState

@Composable
fun VenueInfoScreen(
    viewModel: VenueInfoViewModel,
    modifier: Modifier = Modifier,
    onUiEvent: (UiEvent) -> Unit = {},
) {
    LaunchedEffect(key1 = true) { viewModel.uiEvents.collect(onUiEvent) }
    val uiState by viewModel.uiState.collectAsState()
    VenueInfoScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        modifier = modifier,
    )
}

@Composable
fun VenueInfoScreen(
    uiState: VenueInfoUiState,
    modifier: Modifier = Modifier,
    onAction: (UiAction) -> Unit = {},
) {
    LoadingAndErrorWrapper(
        uiState = uiState,
        onRetry = { onAction(UiAction.Refresh) },
    ) {
        val venue = requireNotNull(uiState.venue)
        val venueDetails = uiState.details
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            InfoBlockImage(url = venue.picture)
            Text(
                text = venue.name.uppercase(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(top = 10.dp)
            )
            ExpandingInfoBlock(
                buttonTitleId = R.string.all_series,
                dialogTitleId = R.string.series,
                items = venueDetails?.series?.map { it.name }.orEmpty(),
                onItemClick = { onAction(UiAction.SeriesSelected(it)) },
            )
            val address = venue.address
            if (address != null)
                Text(
                    text = address,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(top = 10.dp)
                )
            val length = venueDetails?.lastCircuit?.lengthMeters
            if (length != null)
                InfoBlock(
                    titleId = R.string.length,
                    value = stringResource(id = R.string.length_format, length.toInt())
                )
            val corners = venueDetails?.lastCircuit?.totalCorners?.toString()
            if (corners != null)
                InfoBlock(titleId = R.string.corners, value = corners)
            RaceBlock(R.string.first_race, venueDetails?.firstEvent)
            RaceBlock(R.string.last_race, venueDetails?.lastEvent)
            SocialBlock(uiState.links)
        }
    }
}

@Composable
private fun RaceBlock(
    @StringRes titleId: Int,
    eventYear: VenueDetails.EventYear?
) {
    if (eventYear != null)
        InfoBlock(
            titleId = titleId,
            value = stringResource(id = R.string.race_format, eventYear.year, eventYear.event.name)
        )
}

@MultiPreview
@Composable
fun PreviewVenueInfoScreen() {
    AppTheme {
        Surface {
            VenueInfoScreen(
                VenueInfoUiState(
                    venue = MockVenueData.venue,
                    details = MockVenueData.venueDetails,
                    links = MockSocialData.links,
                    hasData = true,
                    isLoading = false,
                    errorMessageId = null,
                )
            )
        }
    }
}

@MultiPreview
@Composable
fun PreviewNoDetails() {
    AppTheme {
        Surface {
            VenueInfoScreen(
                VenueInfoUiState(
                    venue = MockVenueData.venue,
                    details = null,
                    links = MockSocialData.links,
                    hasData = true,
                    isLoading = false,
                    errorMessageId = null,
                )
            )
        }
    }
}