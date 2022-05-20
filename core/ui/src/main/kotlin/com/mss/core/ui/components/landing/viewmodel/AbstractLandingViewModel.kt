package com.mss.core.ui.components.landing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mss.core.ui.components.landing.UiAction
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch

abstract class AbstractLandingViewModel : ViewModel() {
    private val _actions = MutableSharedFlow<UiAction>()
    protected val actions = _actions.asSharedFlow().onSubscription { emit(UiAction.Refresh) }
    protected val refreshActions = actions.filterIsInstance<UiAction.Refresh>()

    protected open val categories: List<Any> = emptyList()

    protected abstract fun refresh()
    protected open fun selectCategory(action: UiAction.SelectCategory) {}

    fun handleAction(action: UiAction) {
        viewModelScope.launch { _actions.emit(action) }
        when (action) {
            is UiAction.Refresh -> refresh()
            is UiAction.SelectCategory -> selectCategory(action)
        }
    }

    fun resetCategories() {
        categories.forEach { handleAction(UiAction.SelectCategory(it, idx = 0)) }
    }
}