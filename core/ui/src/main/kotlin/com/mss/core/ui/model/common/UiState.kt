package com.mss.core.ui.model.common

interface UiState {
    val isLoading: Boolean
    val errorMessageId: Int?
    val hasData: Boolean
}