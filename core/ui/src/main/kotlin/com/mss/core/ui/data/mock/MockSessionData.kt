package com.mss.core.ui.data.mock

import com.mss.core.ui.model.landing.SimpleUiItem
import com.mss.core.ui.model.landing.UiItem
import kotlin.random.Random

@Suppress("MagicNumber")
object MockSessionData {
    private val random = Random(12_345)

    private val names = listOf(
        "3rd Qualifying", "Race", "Race 2", "Race 3",
        "SS21 Fafe 2 [Power Stage]",
    )

    val mostRecent = getSessionList()
    val mostPopular = getSessionList()
    val forthcoming = getSessionList()
    val categorySessions = getSessionList()

    private fun getSessionList(size: Int = 10) = List<UiItem>(size) {
        SimpleUiItem(
            imageUrl = MockSeriesData.pictures.random(random),
            title = MockEventData.names.random(random),
            subtitles = listOf(
                names.random(),
                "Thursday May ${getDay()}, ${getHour()}:${getMinute()}hrs (local time)"
            ),
        )
    }

    private fun getDay() = random.nextInt(1, 32)
    private fun getHour() = random.nextInt(24)
    private fun getMinute() = random.nextInt(10, 60)
}