package com.mss.core.ui.components.landing

import kotlinx.coroutines.flow.*

sealed interface UiAction {
    object Refresh : UiAction
    data class SelectCategory(
        val blockId: Any?,
        val idx: Int
    ) : UiAction

    data class ActionButtonClick(val blockId: Any?) : UiAction

    companion object {
        fun SharedFlow<UiAction>.filterByCategory(blockId: Any): Flow<SelectCategory> =
            this.onSubscription { emit(SelectCategory(blockId, 0)) }
                .filterIsInstance<SelectCategory>()
                .filter { it.blockId == blockId }
    }
}