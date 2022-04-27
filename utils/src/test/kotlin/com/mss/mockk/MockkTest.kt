package com.mss.mockk

import com.mss.annotation.UnitTest
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class MockkTest {
    private val mocked = mockk<Mock> {
        every { answer() } returns MOCKED_ANSWER
    }

    @Test
    fun `check that mockk is working`() {
        val actual = mocked.answer()
        assertThat(actual).isEqualTo(MOCKED_ANSWER)
    }

    companion object {
        const val ANSWER = 42
        const val MOCKED_ANSWER = 24
    }

    class Mock {
        fun answer(): Int = ANSWER
    }
}