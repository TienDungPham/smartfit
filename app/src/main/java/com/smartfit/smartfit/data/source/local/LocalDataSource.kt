package com.smartfit.smartfit.data.source.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LocalDataSource(
    private val appDatabase: AppDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
}