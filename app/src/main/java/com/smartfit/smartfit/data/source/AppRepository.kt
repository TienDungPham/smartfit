package com.smartfit.smartfit.data.source

import com.smartfit.smartfit.data.entity.*
import com.smartfit.smartfit.data.source.local.LocalDataSource
import com.smartfit.smartfit.data.source.remote.RemoteDataSource
import com.smartfit.smartfit.data.transfer.*
import com.smartfit.smartfit.data.transfer.up.SignIn
import com.smartfit.smartfit.data.transfer.up.SignUp
import com.smartfit.smartfit.data.transfer.up.UpdateUserMeal
import com.smartfit.smartfit.utils.Utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.*

class AppRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun isUserSignIn(): Boolean = withContext(dispatcher) {
        val localUserAccess = localDataSource.findUserAccess() ?: return@withContext false
        try {
            val expiryTime = Utils.parseDate(localUserAccess.expiryTime)
            if (expiryTime!!.before(Date())) {
                val remoteUserAccess = remoteDataSource
                    .refreshToken(localUserAccess.refreshToken)
                localDataSource
                    .saveUserAccess(UserAccessDTO.mapToUserAccess(remoteUserAccess))
                return@withContext true
            } else {
                return@withContext true
            }
        } catch (e: HttpException) {
            return@withContext false
        }
    }

    suspend fun signIn(signIn: SignIn): Boolean =
        withContext(dispatcher) {
            try {
                val remoteUserAccess = remoteDataSource.signIn(signIn)
                localDataSource.saveUserAccess(UserAccessDTO.mapToUserAccess(remoteUserAccess))
                return@withContext true
            } catch (e: HttpException) {
                return@withContext false
            }
        }

    suspend fun signUp(signUp: SignUp): Boolean =
        withContext(dispatcher) {
            try {
                val remoteUserAccess = remoteDataSource.signUp(signUp)
                localDataSource.saveUserAccess(UserAccessDTO.mapToUserAccess(remoteUserAccess))
                return@withContext true
            } catch (e: HttpException) {
                return@withContext false
            }
        }

    fun findUserProgress(): Flow<UserProgress> =
        localDataSource.findUserProgress().flowOn(dispatcher)

    suspend fun syncUserProgress() = withContext(dispatcher) {
        try {
            val userAccess = findAccessToken()
            val remoteUserProgress = remoteDataSource.findUserProgress(userAccess.accessToken)
            localDataSource.saveUserProgress(UserProgressDTO.mapToUserProgress(remoteUserProgress))
        } catch (e: HttpException) {
            throw IllegalStateException("Http Error")
        }
    }

    fun findUserCourses(): Flow<List<UserCourse>> =
        localDataSource.findUserCourses().flowOn(dispatcher)

    suspend fun syncUserCourses() = withContext(dispatcher) {
        try {
            val userAccess = findAccessToken()
            val remoteUserCourses = remoteDataSource.findUserCourses(userAccess.accessToken)
            val userCourse = LinkedList<UserCourse>()
            remoteUserCourses.forEach {
                userCourse.add(UserCourseDTO.mapToUserCourse(it))
            }
            localDataSource.saveUserCourses(userCourse)
        } catch (e: HttpException) {
            throw IllegalStateException("Http Error")
        }
    }

    suspend fun findAccessToken(): UserAccess = withContext(dispatcher) {
        return@withContext localDataSource.findUserAccess()
    }

    fun findAllCourses(): Flow<List<Course>> =
        localDataSource.findAllCourses().flowOn(dispatcher)

    suspend fun syncAllCourses() = withContext(dispatcher) {
        try {
            val remoteCourses = remoteDataSource.findAllCourses()
            val courses = LinkedList<Course>()
            remoteCourses.forEach {
                courses.add(CourseDTO.mapToCourse(it))
            }
            localDataSource.saveCourses(courses)
        } catch (e: HttpException) {
            throw IllegalStateException("Http Error")
        }
    }

    suspend fun findCourseDetail(id: Long): CourseWithSteps =
        withContext(dispatcher) {
            return@withContext localDataSource.findCourseDetail(id)
        }

    suspend fun syncCourseDetail(id: Long) = withContext(dispatcher) {
        try {
            val remoteCourseDetail = remoteDataSource.findCourseDetail(id.toInt())
            localDataSource.saveCourse(CourseDetailDTO.mapToCourse(remoteCourseDetail))
            localDataSource.saveCourseSteps(CourseDetailDTO.mapToCourseSteps(remoteCourseDetail))
        } catch (e: HttpException) {
            throw java.lang.IllegalStateException("Http Error")
        }
    }

    suspend fun checkUserAccess(id: Long): Boolean = withContext(dispatcher) {
        try {
            val userAccess = findAccessToken()
            return@withContext remoteDataSource.checkUserAccess(id.toInt(), userAccess.accessToken)
        } catch (e: HttpException) {
            return@withContext true
        }
    }

    fun findAllMeals(): Flow<List<Meal>> =
        localDataSource.findAllMeals().flowOn(dispatcher)

    suspend fun syncMeals() = withContext(dispatcher) {
        try {
            val remoteMeals = remoteDataSource.findAllMeals()
            localDataSource.saveMeals(MealDTO.mapToMeals(remoteMeals))
        } catch (e: HttpException) {
            throw java.lang.IllegalStateException("Http Error")
        }
    }

    fun findUserMeal(): Flow<List<UserMeal>> =
        localDataSource.findUserMeals().flowOn(dispatcher)

    suspend fun syncUserMeals() = withContext(dispatcher) {
        try {
            val userAccess = findAccessToken()
            val remoteMeals = remoteDataSource.findUserMeals(userAccess.accessToken)
            localDataSource.saveUserMeals(UserMealDTO.mapToUserMeals(remoteMeals))
        } catch (e: HttpException) {
            throw java.lang.IllegalStateException("Http Error")
        }
    }

    suspend fun updateUserMeal(updateUserMeal: UpdateUserMeal) = withContext(dispatcher) {
        try {
            val userAccess = findAccessToken()
            val remoteMeals =
                remoteDataSource.updateUserMeal(userAccess.accessToken, updateUserMeal)
        } catch (e: HttpException) {
            throw java.lang.IllegalStateException("Http Error")
        }
    }
}