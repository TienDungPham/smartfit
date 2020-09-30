package com.smartfit.smartfit.data.source.local

import com.smartfit.smartfit.data.entity.UserAccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LocalDataSource(
    private val appDatabase: AppDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun findUserAccess(): UserAccess =
        appDatabase.userDao().findUserAccess()

    fun saveUserAccess(userAccess: UserAccess) =
        appDatabase.userDao().saveUserAccess(userAccess)
}