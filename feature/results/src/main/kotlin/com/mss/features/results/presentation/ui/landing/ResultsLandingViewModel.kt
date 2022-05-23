package com.mss.features.results.presentation.ui.landing

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mss.core.domain.repository.SessionRepository.Collection.*
import com.mss.core.domain.usecases.GetSeriesCategories
import com.mss.core.ui.components.landing.UiAction
import com.mss.core.ui.components.landing.UiAction.Companion.filterByCategory
import com.mss.core.ui.components.landing.viewmodel.AbstractLandingViewModel
import com.mss.core.ui.utils.mapPage
import com.mss.core.utils.Result.Success
import com.mss.features.results.R
import com.mss.features.results.domain.usecases.GetSessionCollection
import com.mss.features.results.domain.usecases.GetSessionsBySeriesCategory
import com.mss.features.results.presentation.mapper.SessionItemMapper
import com.mss.features.results.presentation.ui.landing.BlockId.Categories
import com.mss.features.results.presentation.ui.landing.state.ResultsLandingModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class ResultsLandingViewModel @Inject constructor(
    private val getSeriesCategories: GetSeriesCategories,
    private val getSessionCollection: GetSessionCollection,
    private val getSessionsBySeriesCategory: GetSessionsBySeriesCategory,
) : AbstractLandingViewModel() {
    override val categories = BlockId.values().toList()
    private val viewModelState = MutableStateFlow(ResultsLandingModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toUiState()
        )

    init {
        viewModelState.update { state ->
            state.copy(
                mostRecent = refreshActions
                    .flatMapLatest { getSessionCollection(MostRecent) }
                    .mapPage(SessionItemMapper)
                    .cachedIn(viewModelScope),
                mostPopular = refreshActions
                    .flatMapLatest { getSessionCollection(MostPopular) }
                    .mapPage(SessionItemMapper)
                    .cachedIn(viewModelScope),
                forthcoming = refreshActions
                    .flatMapLatest { getSessionCollection(Forthcoming) }
                    .mapPage(SessionItemMapper)
                    .cachedIn(viewModelScope),
                categorySessions = actions.filterByCategory(Categories)
                    .mapNotNull { viewModelState.value.categories.getOrNull(it.idx) }
                    .flatMapLatest { getSessionsBySeriesCategory(it) }
                    .mapPage(SessionItemMapper)
                    .cachedIn(viewModelScope),
            )
        }
        refresh()
    }

    override fun selectCategory(action: UiAction.SelectCategory) =
        if (action.blockId == Categories)
            viewModelState.update { it.copy(selectedCategoryIdx = action.idx) }
        else
            Timber.e("'${action.blockId}' block is not supported")

    override fun refresh() {
        viewModelState.update {
            it.copy(
                isLoading = true,
                errorMessageId = null,
            )
        }

        viewModelScope.launch {
            val categories = getSeriesCategories()

            if (categories !is Success) {
                viewModelState.update {
                    it.copy(
                        errorMessageId = R.string.try_again,
                        isLoading = false,
                    )
                }
                return@launch
            }

            viewModelState.update {
                it.copy(
                    categories = categories.data,
                    isLoading = false,
                    errorMessageId = null,
                )
            }
            resetCategories()
        }
    }
}