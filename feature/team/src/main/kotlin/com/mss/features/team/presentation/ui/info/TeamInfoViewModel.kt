package com.mss.features.team.presentation.ui.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mss.core.ui.components.common.viewmodel.AbstractViewModel
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.navigation.Route
import com.mss.core.utils.Result.Success
import com.mss.features.team.R
import com.mss.features.team.domain.usecases.GetTeamInfo
import com.mss.features.team.domain.usecases.GetTeamLastDrivers
import com.mss.features.team.presentation.ui.info.state.TeamInfoModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamInfoViewModel @Inject constructor(
    private val getTeamInfo: GetTeamInfo,
    private val getTeamLastDrivers: GetTeamLastDrivers,
    savedStateHandle: SavedStateHandle
) : AbstractViewModel() {
    private val teamSlug: String =
        requireNotNull(savedStateHandle[Route.TeamInfo.Arguments.TEAM_SLUG])
    private val viewModelState = MutableStateFlow(TeamInfoModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toUiState()
        )

    init {
        refresh()
    }

    fun handleAction(action: UiAction) {
        when (action) {
            UiAction.Refresh -> refresh()
            is UiAction.SeriesSelected -> {
                val series = viewModelState.value.series.getOrNull(action.idx)?.series ?: return
                sendUiEvent(UiEvent.Navigate(Route.SeriesInfo(series.slug)))
            }
            is UiAction.DriverSelected -> {
                val driver = viewModelState.value
                    .series.getOrNull(action.seriesIdx)
                    ?.drivers?.getOrNull(action.driverIdx) ?: return
                sendUiEvent(UiEvent.Navigate(Route.DriverInfo(driver.slug)))
            }
        }
    }

    private fun refresh() {
        viewModelState.update {
            it.copy(
                isLoading = true,
                errorMessageId = null,
            )
        }

        viewModelScope.launch {
            val infoDeferred = async { getTeamInfo(teamSlug) }
            val lastDriversDeferred = async { getTeamLastDrivers(teamSlug) }

            val info = infoDeferred.await()
            val lastDrivers = lastDriversDeferred.await()

            viewModelState.update { state ->
                if (info is Success && lastDrivers is Success)
                    state.copy(
                        teamInfo = info.data,
                        series = lastDrivers.data,
                        isLoading = false,
                        errorMessageId = null,
                    )
                else
                    state.copy(
                        errorMessageId = R.string.try_again,
                        isLoading = false,
                    )
            }
        }
    }
}