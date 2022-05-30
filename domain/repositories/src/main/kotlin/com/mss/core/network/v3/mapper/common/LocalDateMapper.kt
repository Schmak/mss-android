package com.mss.core.network.v3.mapper.common

import com.mss.core.utils.Mapper
import java.time.LocalDate
import kotlin.time.Duration.Companion.seconds

object LocalDateMapper : Mapper<Long, LocalDate> {
    override fun Long.map(): LocalDate =
        LocalDate.ofEpochDay(this.seconds.inWholeDays)
}