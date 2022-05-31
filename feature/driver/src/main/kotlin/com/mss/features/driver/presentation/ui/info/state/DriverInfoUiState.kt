package com.mss.features.driver.presentation.ui.info.state

import com.mss.core.domain.Driver
import com.mss.core.ui.model.SocialLink
import com.mss.core.ui.model.common.UiState

data class DriverInfoUiState(
    val driver: Driver?,
    val series: List<String>,
    val teams: List<String>,
    val links: List<SocialLink>,
    override val isLoading: Boolean,
    override val errorMessageId: Int?,
    override val hasData: Boolean,
) : UiState