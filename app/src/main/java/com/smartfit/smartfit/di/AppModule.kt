package com.smartfit.smartfit.di

import android.content.Context
import androidx.room.Room
import com.smartfit.smartfit.data.source.AppRepository
import com.smartfit.smartfit.data.source.local.AppDatabase
import com.smartfit.smartfit.data.source.local.LocalDataSource
import com.smartfit.smartfit.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
abstract class AppModule {
    companion object {
        @Singleton
        @Provides
        fun provideCoroutineDispatcher(): CoroutineDispatcher {
            return Dispatchers.IO
        }

        @Singleton
        @Provides
        fun provideDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
        }

        @Singleton
        @Provides
        fun provideLocalDataSource(
            appDatabase: AppDatabase,
            dispatcher: CoroutineDispatcher
        ): LocalDataSource {
            return LocalDataSource(appDatabase, dispatcher)
        }

        @Singleton
        @Provides
        fun provideRemoteDataSource(
            dispatcher: CoroutineDispatcher
        ): RemoteDataSource {
            return RemoteDataSource(dispatcher)
        }

        @Singleton
        @Provides
        fun provideAppRepository(
            localDataSource: LocalDataSource,
            remoteDataSource: RemoteDataSource,
            dispatcher: CoroutineDispatcher
        ): AppRepository {
            return AppRepository(localDataSource, remoteDataSource, dispatcher)
        }
    }
}