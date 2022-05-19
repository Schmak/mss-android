package com.mss.core.ui.utils

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun <T : Any> List<T>.asPageFlow(): Flow<PagingData<T>> =
    flowOf(PagingData.from(this))