package com.mss.core.data.repository

import com.mss.core.test.annotation.CoroutineTest
import com.mss.core.test.annotation.UnitTest
import com.mss.core.test.junit5.AbstractTestCaseWithOrigin
import com.mss.core.test.utils.coroutines.TestDispatchers
import com.mss.core.test.utils.coroutines.verify
import com.mss.core.utils.Result
import io.mockk.MockKMatcherScope
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@UnitTest
@CoroutineTest
@OptIn(ExperimentalCoroutinesApi::class)
abstract class AbstractRepositoryTest {
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
        Assertions.assertThat(actual).isEqualTo(Result.Success(case.expectedRepositoryResponse))
    }

    @ParameterizedTest
    @MethodSource("cases")
    fun failing(case: TestCase<*, *>) = runTest {
        coEvery { case.apiQuery(this) } throws EXCEPTION

        val actual = case.repositoryQuery()
        coVerify(exactly = 1) { case.apiQuery(this) }
        Assertions.assertThat(actual).isEqualTo(Result.Error(EXCEPTION))
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