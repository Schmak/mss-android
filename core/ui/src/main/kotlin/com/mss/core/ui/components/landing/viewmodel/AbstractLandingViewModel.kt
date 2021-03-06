package com.mss.core.ui.components.landing.viewmodel

import androidx.lifecycle.viewModelScope
import com.mss.core.ui.components.common.viewmodel.AbstractViewModel
import com.mss.core.ui.components.landing.UiAction
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.model.landing.UiItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch

abstract class AbstractLandingViewModel : AbstractViewModel() {
    private val _actions = MutableSharedFlow<UiAction>()
    protected val actions = _actions.asSharedFlow().onSubscription { emit(UiAction.Refresh) }
    protected val refreshActions = actions.filterIsInstance<UiAction.Refresh>()

    protected open val categories: List<Any> = emptyList()

    protected abstract fun refresh()
    protected open fun selectCategory(action: UiAction.SelectCategory) {}
    protected open fun handleActionButtonClick(blockId: Any?) {}

    fun handleAction(action: UiAction) {
        viewModelScope.launch { _actions.emit(action) }
        when (action) {
            is UiAction.Refresh -> refresh()
            is UiAction.SelectCategory -> selectCategory(action)
            is UiAction.ActionButtonClick -> handleActionButtonClick(action.blockId)
            is UiAction.ItemClicked -> handleItemClick(action.item)
        }
    }

    private fun handleItemClick(item: UiItem) {
        val route = item.route ?: return
        sendUiEvent(UiEvent.Navigate(route))
    }

    fun resetCategories() {
        categories.forEach { handleAction(UiAction.SelectCategory(it, idx = 0)) }
    }
}