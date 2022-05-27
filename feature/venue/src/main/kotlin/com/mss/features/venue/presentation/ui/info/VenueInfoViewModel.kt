package com.mss.features.venue.presentation.ui.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mss.core.ui.components.common.viewmodel.AbstractViewModel
import com.mss.core.ui.navigation.Route
import com.mss.core.utils.Result.Success
import com.mss.features.venue.R
import com.mss.features.venue.domain.usecases.GetVenueDetails
import com.mss.features.venue.domain.usecases.GetVenueInfo
import com.mss.features.venue.presentation.ui.info.state.VenueInfoModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VenueInfoViewModel @Inject constructor(
    private val getVenueInfo: GetVenueInfo,
    private val getVenueDetails: GetVenueDetails,
    savedStateHandle: SavedStateHandle
) : AbstractViewModel() {
    private val venueSlug: String =
        requireNotNull(savedStateHandle[Route.VenueInfo.Arguments.VENUE_SLUG])
    private val viewModelState = MutableStateFlow(VenueInfoModelState(isLoading = true))

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
            val infoDeferred = async { getVenueInfo(venueSlug) }
            val detailsDeferred = async { getVenueDetails(venueSlug) }

            val info = infoDeferred.await()
            val details = detailsDeferred.await()

            viewModelState.update {
                if (info is Success && details is Success)
                    it.copy(
                        venueInfo = info.data,
                        venueDetails = details.data,
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