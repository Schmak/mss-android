package com.mss.core.test.extensions

import io.mockk.MockK
import io.mockk.MockKGateway

val <T : Any> T.isMock: Boolean
    get() = MockK.useImpl { MockKGateway.implementation().mockFactory.isMock(this) }