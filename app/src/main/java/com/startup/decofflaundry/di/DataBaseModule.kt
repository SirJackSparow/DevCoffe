package com.startup.decofflaundry.di

import android.app.Application
import androidx.room.Room
import com.startup.core.localdatabase.AppDatabase
import com.startup.core.service.NewssterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(app: Application) :  AppDatabase {
        return Room.databaseBuilder(app,AppDatabase::class.java,"jancok.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideNewsDao(appDatabase: AppDatabase) : NewssterService {
        return appDatabase.getNews()
    }
}
