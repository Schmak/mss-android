package com.mss.features.series.presentation.ui.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mss.core.utils.Result.Success
import com.mss.features.series.R
import com.mss.features.series.domain.usecases.GetLastChampions
import com.mss.features.series.domain.usecases.GetSeriesInfo
import com.mss.features.series.presentation.ui.info.state.SeriesInfoModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesInfoViewModel @Inject constructor(
    private val getSeriesInfo: GetSeriesInfo,
    private val getLastChampions: GetLastChampions,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val seriesSlug: String = requireNotNull(savedStateHandle["series"])
    private val viewModelState = MutableStateFlow(SeriesInfoModelState(isLoading = true))

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

    fun handleAction(action: UiAction) = when (action) {
        UiAction.Refresh -> refresh()
    }

    private fun refresh() {
        viewModelState.update {
            it.copy(
                isLoading = true,
                errorMessageId = null,
            )
        }

        viewModelScope.launch {
            val seriesInfoDeferred = async { getSeriesInfo(seriesSlug) }
            val lastChampionsDeferred = async { getLastChampions(seriesSlug) }

            val seriesInfo = seriesInfoDeferred.await()
            val lastChampions = lastChampionsDeferred.await()

            viewModelState.update {
                if (seriesInfo is Success && lastChampions is Success)
                    it.copy(
                        seriesInfo = seriesInfo.data,
                        lastChampions = lastChampions.data,
                        isLoading = false,
                        errorMessageId = null,
                    )
                else
                    it.copy(
                        errorMessageId = R.string.try_again,
                        isLoading = false,
                    )
            }
        }
    }
}