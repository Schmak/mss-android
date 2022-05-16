package com.mss.core.ui.utils

import androidx.paging.PagingData
import androidx.paging.map
import com.mss.core.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T : Any, R : Any> Flow<PagingData<T>>.mapPage(
    mapper: Mapper<T, R>
): Flow<PagingData<R>> = map { it.map(mapper) }