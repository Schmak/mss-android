package com.mss.core.network.v4.model.common

data class TemplateDto(
    val code: String?,
    val seriesCategory: String,
    val timingFormat: String?,
    val isMotorcycle: Boolean
) {
    companion object
}