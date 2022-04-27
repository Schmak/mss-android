package com.mss.extensions

import kotlin.time.Duration

fun Duration.formatAsHHMMSS(): String = this.toComponents { hours, minutes, seconds, _ ->
    if (hours == 0L)
        "%02d:%02d".format(minutes, seconds)
    else
        "%d:%02d:%02d".format(hours, minutes, seconds)
}