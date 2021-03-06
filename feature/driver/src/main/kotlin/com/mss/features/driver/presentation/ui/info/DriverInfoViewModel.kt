package com.mss.features.driver.presentation.ui.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mss.core.ui.components.common.viewmodel.AbstractViewModel
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.navigation.Route
import com.mss.core.utils.Result.Success
import com.mss.features.driver.R
import com.mss.features.driver.domain.usecases.GetDriverInfo
import com.mss.features.driver.domain.usecases.GetDriverLastTeams
import com.mss.features.driver.presentation.ui.info.state.DriverInfoModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverInfoViewModel @Inject constructor(
    private val getDriverInfo: GetDriverInfo,
    private val getDriverLastTeams: GetDriverLastTeams,
    savedStateHandle: SavedStateHandle
) : AbstractViewModel() {
    private val driverSlug: String =
        requireNotNull(savedStateHandle[Route.DriverInfo.Arguments.DRIVER_SLUG])
    private val viewModelState = MutableStateFlow(DriverInfoModelState(isLoading = true))

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
            is UiAction.SelectSeries -> {
                val series = viewModelState.value.series.getOrNull(action.idx) ?: return
                sendUiEvent(UiEvent.Navigate(Route.SeriesInfo(series.slug)))
            }
            is UiAction.SelectRelatedDriver -> {
                val driver = viewModelState.value.driverInfo
                    ?.relations?.getOrNull(action.idx)?.driver ?: return
                sendUiEvent(UiEvent.Navigate(Route.DriverInfo(driver.slug)))
            }
            is UiAction.SelectTeam -> {
                val team = viewModelState.value.teams.getOrNull(action.idx) ?: return
                sendUiEvent(UiEvent.Navigate(Route.TeamInfo(team.slug)))
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
            val infoDeferred = async { getDriverInfo(driverSlug) }
            val lastTeamsDeferred = async { getDriverLastTeams(driverSlug) }

            val info = infoDeferred.await()
            val lastTeams = lastTeamsDeferred.await()

            viewModelState.update { state ->
                if (info is Success && lastTeams is Success)
                    state.copy(
                        driverInfo = info.data,
                        series = lastTeams.data.map { it.series },
                        teams = lastTeams.data.asSequence()
                            .mapNotNull { it.team }
                            .filter { it.name.isNotEmpty() }
                            .distinct().toList(),
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