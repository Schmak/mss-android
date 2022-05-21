package com.mss.test

import com.mss.annotation.CoroutineTest
import com.mss.annotation.UnitTest
import com.mss.core.utils.Result
import com.mss.junit5.AbstractTestCaseWithOrigin
import com.mss.utils.coroutines.TestDispatchers
import com.mss.utils.coroutines.verify
import io.mockk.MockKMatcherScope
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@UnitTest
@CoroutineTest
@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseRepositoryTest {
    @ParameterizedTest
    @MethodSource("cases")
    fun `check dispatcher`(case: TestCase<*, *>) = runTest {
        verify { case.apiQuery(this) } isCalledFrom case.repositoryQuery with TestDispatchers.IO
    }

    @ParameterizedTest
    @MethodSource("cases")
    fun success(case: TestCase<*, *>) = runTest {
        coEvery { case.apiQuery(this) } returns case.apiResponse

        val actual = case.repositoryQuery()
        coVerify(exactly = 1) { case.apiQuery(this) }
        assertThat(actual).isEqualTo(Result.Success(case.expectedRepositoryResponse))
    }

    @ParameterizedTest
    @MethodSource("cases")
    fun failing(case: TestCase<*, *>) = runTest {
        coEvery { case.apiQuery(this) } throws EXCEPTION

        val actual = case.repositoryQuery()
        coVerify(exactly = 1) { case.apiQuery(this) }
        assertThat(actual).isEqualTo(Result.Error(EXCEPTION))
    }

    protected abstract fun cases(): List<TestCase<*, *>>

    data class TestCase<A, R>(
        val name: String,
        val apiQuery: suspend MockKMatcherScope.() -> A,
        val apiResponse: A,
        val repositoryQuery: suspend () -> R,
        val expectedRepositoryResponse: R,
    ) : AbstractTestCaseWithOrigin() {
        override fun toString() = name
    }

    private companion object {
        private val EXCEPTION = RuntimeException("Some exception")
    }
}