package com.mss.core.network.v4.api

import com.mss.annotation.IntegrationTest
import com.mss.core.network.v4.model.PageDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow

@OptIn(ExperimentalCoroutinesApi::class)
@IntegrationTest
open class AbstractApiTest {
    protected fun test(apiCall: suspend () -> Any) = runTest {
        when (val actual = assertDoesNotThrow { apiCall() }) {
            is PageDto<*> -> assertThat(actual.content).isNotEmpty
            is List<*> -> assertThat(actual).isNotEmpty
            else -> assertThat(actual).isNotNull
        }
    }
}