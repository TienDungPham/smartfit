package com.smartfit.smartfit.data.source

import com.smartfit.smartfit.data.source.local.LocalDataSource
import com.smartfit.smartfit.data.source.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
}