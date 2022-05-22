package com.mss.core.network.utils

import com.mss.network.ApiServer

object TestApiServer : ApiServer {
    override val url = "https://api.motorsportstats.com"

    override val apiKey: String =
        requireNotNull(System.getenv("X_API_KEY")) { "Put API key to X_API_KEY environment variable" }
}