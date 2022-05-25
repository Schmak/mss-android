package com.mss.core.ui.components.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mss.core.ui.model.common.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class AbstractViewModel : ViewModel() {
    private val _uiEvents = Channel<UiEvent>()

    /** UI should subscribe to this [Flow] and process all the events. */
    val uiEvents: Flow<UiEvent> = _uiEvents.receiveAsFlow()

    /** Send event from [ViewModel] to UI layer */
    protected fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvents.send(uiEvent)
        }
    }
}