package com.smartfit.smartfit.di

import android.content.Context
import com.smartfit.smartfit.ui.AuthActivity
import com.smartfit.smartfit.ui.LauncherActivity
import com.smartfit.smartfit.ui.MainActivity
import com.smartfit.smartfit.ui.account.AccountFragment
import com.smartfit.smartfit.ui.addmeal.AddMealFragment
import com.smartfit.smartfit.ui.checkout.CheckoutFragment
import com.smartfit.smartfit.ui.courseclass.CourseClassFragment
import com.smartfit.smartfit.ui.coursepreview.CoursePreviewFragment
import com.smartfit.smartfit.ui.courses.CoursesFragment
import com.smartfit.smartfit.ui.courseworkout.CourseWorkoutFragment
import com.smartfit.smartfit.ui.dashboard.DashboardFragment
import com.smartfit.smartfit.ui.meal.MealFragment
import com.smartfit.smartfit.ui.paymenthistory.PaymentHistoryFragment
import com.smartfit.smartfit.ui.settings.SettingsFragment
import com.smartfit.smartfit.ui.updateaccount.UpdateAccountFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun injectLauncherActivity(launcherActivity: LauncherActivity)
    fun injectAuthActivity(authActivity: AuthActivity)
    fun injectMainActivity(mainActivity: MainActivity)

    fun injectAccountFragment(accountFragment: AccountFragment)
    fun injectAddMealFragment(addMealFragment: AddMealFragment)
    fun injectCheckoutFragment(checkoutFragment: CheckoutFragment)
    fun injectCourseClassFragment(courseClassFragment: CourseClassFragment)
    fun injectCoursePreviewFragment(coursePreviewFragment: CoursePreviewFragment)
    fun injectCoursesFragment(coursesFragment: CoursesFragment)
    fun injectCourseWorkoutFragment(courseWorkoutFragment: CourseWorkoutFragment)
    fun injectDashboardFragment(dashboardFragment: DashboardFragment)
    fun injectMealFragment(mealFragment: MealFragment)
    fun injectPaymentHistoryFragment(paymentHistoryFragment: PaymentHistoryFragment)
    fun injectSettingsFragment(settingsFragment: SettingsFragment)
    fun injectUpdateAccountFragment(updateDateAccountFragment: UpdateAccountFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): AppComponent
    }
}