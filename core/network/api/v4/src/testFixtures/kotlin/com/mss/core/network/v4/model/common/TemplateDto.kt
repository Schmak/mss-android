package com.mss.core.network.v4.model.common

fun TemplateDto.Companion.create(
    code: String? = "template code",
    seriesCategory: String = "template seriesCategory",
    timingFormat: String? = "template timingFormat",
    isMotorcycle: Boolean = false,
) = TemplateDto(
    code = code,
    seriesCategory = seriesCategory,
    timingFormat = timingFormat,
    isMotorcycle = isMotorcycle
)