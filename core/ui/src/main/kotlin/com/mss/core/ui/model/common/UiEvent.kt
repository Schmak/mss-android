package com.mss.core.ui.model.common

import com.mss.core.ui.navigation.Route

sealed interface UiEvent {
    /**Open selected [route] and display its content on the main screen*/
    data class Navigate(val route: Route) : UiEvent
}