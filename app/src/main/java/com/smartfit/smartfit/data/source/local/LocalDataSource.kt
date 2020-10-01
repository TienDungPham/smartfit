package com.smartfit.smartfit.data.source.local

import com.smartfit.smartfit.data.entity.UserAccess
import com.smartfit.smartfit.data.entity.UserCourse
import com.smartfit.smartfit.data.entity.UserProgress
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val appDatabase: AppDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun findUserAccess(): UserAccess =
        appDatabase.userDao().findUserAccess()

    fun saveUserAccess(userAccess: UserAccess) =
        appDatabase.userDao().saveUserAccess(userAccess)

    fun findUserProgress(): Flow<UserProgress> =
        appDatabase.userDao().findUserProgress()

    fun saveUserProgress(userProgress: UserProgress) =
        appDatabase.userDao().saveUserProgress(userProgress)

    fun findUserCourses(): Flow<List<UserCourse>> =
        appDatabase.userDao().findUserCourses()

    fun saveUserCourses(userCourses: List<UserCourse>) =
        appDatabase.userDao().saveUserCourses(userCourses)
}