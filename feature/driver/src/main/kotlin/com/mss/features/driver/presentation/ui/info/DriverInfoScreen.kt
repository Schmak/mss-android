package com.mss.features.driver.presentation.ui.info

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mss.core.domain.Driver
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
import com.mss.features.driver.R
import com.mss.features.driver.presentation.ui.info.state.DriverInfoUiState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DriverInfoScreen(
    viewModel: DriverInfoViewModel,
    modifier: Modifier = Modifier,
    onUiEvent: (UiEvent) -> Unit = {},
) {
    LaunchedEffect(key1 = true) { viewModel.uiEvents.collect(onUiEvent) }
    val uiState by viewModel.uiState.collectAsState()
    DriverInfoScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        modifier = modifier,
    )
}

@Composable
fun DriverInfoScreen(
    uiState: DriverInfoUiState,
    modifier: Modifier = Modifier,
    onAction: (UiAction) -> Unit = {},
) {
    LoadingAndErrorWrapper(
        uiState = uiState,
        onRetry = { onAction(UiAction.Refresh) },
    ) {
        val driver = requireNotNull(uiState.driver)
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            InfoBlockImage(url = driver.picture, modifier = Modifier.align(CenterHorizontally))
            Text(
                text = driver.name.uppercase(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(top = 10.dp)
            )
            EntityWithPreviousBlock(
                titleId = R.string.series,
                buttonTitleId = R.string.all_series,
                items = uiState.series,
                onItemClick = { onAction(UiAction.SelectSeries(it)) }
            )
            EntityWithPreviousBlock(
                titleId = R.string.teams,
                buttonTitleId = R.string.all_teams,
                items = uiState.teams,
            )
            DateBlock(titleId = R.string.date_of_birth, date = driver.dateOfBirth)
            InfoBlock(titleId = R.string.place_of_birth, value = driver.placeOfBirth)
            DateBlock(titleId = R.string.date_of_death, date = driver.dateOfDeath)
            InfoBlock(titleId = R.string.place_of_death, value = driver.placeOfDeath)
            InfoBlock(titleId = R.string.age, value = driver.age?.toString())
            InfoBlockWithImage(
                titleId = R.string.nationality,
                text = driver.nationality?.name,
                imageUrl = driver.nationality?.picture
            )
            if (driver.relations.isNotEmpty())
                Relations(
                    relations = driver.relations,
                    onItemClick = { onAction(UiAction.SelectRelatedDriver(it)) }
                )
            SocialBlock(uiState.links)
        }
    }
}

@Composable
private fun Relations(
    relations: List<Driver.Relation>,
    onItemClick: (idx: Int) -> Unit,
) {
    Spacer(modifier = Modifier.height(20.dp))
    BlockHeader(titleId = R.string.relations)
    Column {
        relations.forEachIndexed { idx, (driver, relationship) ->
            TextRowWithImage(
                text = driver.name,
                imageUrl = driver.picture,
                modifier = Modifier.clickable { onItemClick(idx) }
            )
            Text(
                text = relationship,
                color = MaterialTheme.colors.infoSubtitleColor,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Composable
private fun DateBlock(
    @StringRes titleId: Int,
    date: LocalDate?,
) {
    InfoBlock(
        titleId = titleId,
        value = date?.format(DateTimeFormatter.ofPattern(stringResource(R.string.date_format)))
    )
}

@MultiPreview
@Composable
@Suppress("MagicNumber")
fun PreviewDriverInfoScreen() {
    AppTheme {
        Surface {
            DriverInfoScreen(
                DriverInfoUiState(
                    driver = MockDriverData.driver,
                    teams = MockTeamData.names.take(5),
                    series = MockSeriesData.names.take(2),
                    links = MockSocialData.links,
                    hasData = true,
                    isLoading = false,
                    errorMessageId = null,
                )
            )
        }
    }
}