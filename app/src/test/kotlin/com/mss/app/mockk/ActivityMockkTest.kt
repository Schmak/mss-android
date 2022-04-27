package com.mss.app.mockk

import android.app.Activity
import com.mss.annotation.UnitTest
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class ActivityMockkTest {
    private val activity = mockk<Activity> {
        every { onCreateDescription() } returns DESCRIPTION
    }

    @Test
    fun `check that mockk is working`() {
        val actual = activity.onCreateDescription()
        assertThat(actual).isEqualTo(DESCRIPTION)
    }

    companion object {
        const val DESCRIPTION = "description"
    }
}