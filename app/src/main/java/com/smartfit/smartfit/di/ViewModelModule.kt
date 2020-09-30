package com.smartfit.smartfit.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smartfit.smartfit.data.source.AppRepository
import com.smartfit.smartfit.ui.AuthViewModel
import com.smartfit.smartfit.ui.LauncherViewModel
import com.smartfit.smartfit.ui.MainViewModel
import com.smartfit.smartfit.ui.account.AccountViewModel
import com.smartfit.smartfit.ui.addmeal.AddMealViewModel
import com.smartfit.smartfit.ui.checkout.CheckoutViewModel
import com.smartfit.smartfit.ui.courseclass.CourseClassViewModel
import com.smartfit.smartfit.ui.coursepreview.CoursePreviewViewModel
import com.smartfit.smartfit.ui.courses.CoursesViewModel
import com.smartfit.smartfit.ui.courseworkout.CourseWorkoutViewModel
import com.smartfit.smartfit.ui.dashboard.DashboardViewModel
import com.smartfit.smartfit.ui.meal.MealViewModel
import com.smartfit.smartfit.ui.paymenthistory.PaymentHistoryViewModel
import com.smartfit.smartfit.ui.settings.SettingsViewModel
import com.smartfit.smartfit.ui.updateaccount.UpdateAccountViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    companion object {
        @Singleton
        @Provides
        fun provideViewModeFactory(appRepository: AppRepository): ViewModelProvider.Factory {
            return ViewModelFactory(appRepository)
        }
    }
}

class ViewModelFactory(private val appRepository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LauncherViewModel::class.java)) {
            return LauncherViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(AddMealViewModel::class.java)) {
            return AddMealViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(CheckoutViewModel::class.java)) {
            return CheckoutViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(CourseClassViewModel::class.java)) {
            return CourseClassViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(CoursePreviewViewModel::class.java)) {
            return CoursePreviewViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(CoursesViewModel::class.java)) {
            return CoursesViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(CourseWorkoutViewModel::class.java)) {
            return CourseWorkoutViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            return MealViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(PaymentHistoryViewModel::class.java)) {
            return PaymentHistoryViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(UpdateAccountViewModel::class.java)) {
            return UpdateAccountViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}