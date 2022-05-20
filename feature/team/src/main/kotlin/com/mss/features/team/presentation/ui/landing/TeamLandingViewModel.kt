package com.mss.features.team.presentation.ui.landing

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mss.core.ui.components.landing.UiAction
import com.mss.core.ui.components.landing.UiAction.Companion.filterByCategory
import com.mss.core.ui.components.landing.viewmodel.AbstractLandingViewModel
import com.mss.core.ui.utils.mapPage
import com.mss.core.utils.Result.Success
import com.mss.features.team.R
import com.mss.features.team.domain.usecases.*
import com.mss.features.team.presentation.mapper.TeamCollectionMapper
import com.mss.features.team.presentation.mapper.TeamItemMapper
import com.mss.features.team.presentation.ui.landing.BlockId.*
import com.mss.features.team.presentation.ui.landing.state.TeamLandingModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class TeamLandingViewModel @Inject constructor(
    private val getGoldenSeries: GetGoldenSeries,
    private val getSeasonTeams: GetSeasonTeams,
    private val getChampions: GetChampions,
    private val getWinners: GetWinners,
    private val getTeamCollection: GetTeamCollection,
) : AbstractLandingViewModel() {
    override val categories = BlockId.values().toList()
    private val viewModelState = MutableStateFlow(TeamLandingModelState(isLoading = true))

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
                collections = Collections.values().toList(),
                currentSeasonTeams = actions.filterByCategory(CurrentSeason)
                    .mapNotNull { viewModelState.value.goldenSeries.getOrNull(it.idx) }
                    .mapNotNull { it.currentSeason?.slug }
                    .flatMapLatest { getSeasonTeams(it) }
                    .mapPage(TeamItemMapper)
                    .cachedIn(viewModelScope),
                champions = actions.filterByCategory(Champions)
                    .mapNotNull { viewModelState.value.goldenSeries.getOrNull(it.idx) }
                    .flatMapLatest { getChampions(it.series.slug) }
                    .mapPage(TeamItemMapper)
                    .cachedIn(viewModelScope),
                winners = actions.filterByCategory(Winners)
                    .mapNotNull { viewModelState.value.goldenSeries.getOrNull(it.idx) }
                    .flatMapLatest { getWinners(it.series.slug) }
                    .mapPage(TeamItemMapper)
                    .cachedIn(viewModelScope),
                collectionTeams = actions.filterByCategory(Collections)
                    .mapNotNull { viewModelState.value.collections[it.idx] }
                    .map { it.let(TeamCollectionMapper) }
                    .flatMapLatest { getTeamCollection(it) }
                    .mapPage(TeamItemMapper)
                    .cachedIn(viewModelScope),
            )
        }
        refresh()
    }

    override fun selectCategory(action: UiAction.SelectCategory) = when (action.blockId) {
        CurrentSeason -> viewModelState.update { it.copy(selectedSeasonIdx = action.idx) }
        Champions -> viewModelState.update { it.copy(selectedChampionSeriesIdx = action.idx) }
        Winners -> viewModelState.update { it.copy(selectedWinnerSeriesIdx = action.idx) }
        Collections -> viewModelState.update { it.copy(selectedCollectionIdx = action.idx) }
        else -> Timber.e("'${action.blockId}' block is not supported")
    }

    override fun refresh() {
        viewModelState.update {
            it.copy(
                isLoading = true,
                errorMessageId = null,
            )
        }

        viewModelScope.launch {
            val goldenSeries = getGoldenSeries()

            if (goldenSeries !is Success) {
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
                    goldenSeries = goldenSeries.data,
                    isLoading = false,
                    errorMessageId = null,
                )
            }
            resetCategories()
        }
    }
}