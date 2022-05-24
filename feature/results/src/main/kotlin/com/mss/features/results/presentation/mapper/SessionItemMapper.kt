package com.mss.features.results.presentation.mapper

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mss.core.domain.SessionItem
import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper
import com.mss.features.results.R
import com.mss.features.results.presentation.ui.landing.state.SessionUiItem
import java.time.OffsetDateTime

sealed class SessionItemMapper(
    @StringRes private val dateSubtitleId: Int,
) : Mapper<SessionItem, UiItem> {
    protected abstract fun convert(time: OffsetDateTime): String

    override fun SessionItem.map(): SessionUiItem {
        val nameLambda = @Composable { name }
        val dateLambda = @Composable {
            stringResource(dateSubtitleId, startTime?.let(::convert).orEmpty())
        }
        return SessionUiItem(
            imageUrl = series.picture.orEmpty(),
            title = event.name,
            subtitlesGetters = listOf(nameLambda, dateLambda),
        )
    }

    object VenueTime : SessionItemMapper(R.string.venue_time) {
        override fun convert(time: OffsetDateTime): String = time.toString()
    }

    object ClientTime : SessionItemMapper(R.string.your_time) {
        override fun convert(time: OffsetDateTime): String = time.toString()
    }
}