package com.mss.core.network.v4.api

import com.mss.core.network.v4.model.PageDto
import com.mss.core.test.annotation.IntegrationTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
@IntegrationTest
open class AbstractApiTest {
    protected fun test(apiCall: suspend () -> Any?) = runTest {
        when (val actual = assertDoesNotThrow { apiCall() }) {
            is PageDto<*> -> assertThat(actual.content).isNotEmpty
            is List<*> -> assertThat(actual).isNotEmpty
            else -> assertThat(actual).isNotNull
        }
    }

    protected fun assertThrows404(apiCall: suspend () -> Any?) = runTest {
        val code = assertThrows<HttpException> { apiCall() }.code()
        assertThat(code).isEqualTo(404)
    }
}