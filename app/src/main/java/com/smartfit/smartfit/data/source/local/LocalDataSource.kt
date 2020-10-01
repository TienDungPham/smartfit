package com.smartfit.smartfit.data.source.local

import com.smartfit.smartfit.data.entity.*
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

    fun deleteUserAccess(userAccess: UserAccess) =
        appDatabase.userDao().deleteUserAccess(userAccess)

    fun findUserProgress(): Flow<UserProgress> =
        appDatabase.userDao().findUserProgress()

    fun saveUserProgress(userProgress: UserProgress) =
        appDatabase.userDao().saveUserProgress(userProgress)

    fun findUserCourses(): Flow<List<UserCourse>> =
        appDatabase.userDao().findUserCourses()

    fun saveUserCourses(userCourses: List<UserCourse>) =
        appDatabase.userDao().saveUserCourses(userCourses)

    fun findAllCourses(): Flow<List<Course>> =
        appDatabase.courseDao().findAllCourses()

    fun findCourseDetail(id: Long): CourseWithSteps =
        appDatabase.courseDao().findCourseDetail(id)

    fun saveCourse(course: Course) =
        appDatabase.courseDao().saveCourse(course)

    fun saveCourses(courses: List<Course>) =
        appDatabase.courseDao().saveCourses(courses)

    fun saveCourseSteps(courseSteps: List<CourseStep>) =
        appDatabase.courseDao().saveCourseSteps(courseSteps)

    fun findAllMeals(): Flow<List<Meal>> =
        appDatabase.mealDao().findAllMeals()

    fun saveMeals(meals: List<Meal>) =
        appDatabase.mealDao().saveMeals(meals)

    fun findUserMeals(): Flow<List<UserMeal>> = appDatabase.userDao().findUserMeals()

    fun saveUserMeals(meals: List<UserMeal>) =
        appDatabase.userDao().saveUserMeals(meals)

    fun findUserProfile(): Flow<UserProfile> = appDatabase.userDao().findUserProfile()

    fun saveUserProfile(userProfile: UserProfile) =
        appDatabase.userDao().saveUserProfile(userProfile)

    fun findUserOrderWithPayment(): Flow<UserOrderWithPayments> =
        appDatabase.userDao().findUserOrder()

    fun saveUserOrder(userOrder: UserOrder) =
        appDatabase.userDao().saveUserOrder(userOrder)

    fun saveUserPayments(userPayments: List<UserPayment>) =
        appDatabase.userDao().saveUserPayments(userPayments)

    fun findStepDetail(id: Long): CourseStep =
        appDatabase.courseDao().findStepDetail(id)
}