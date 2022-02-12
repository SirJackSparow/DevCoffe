package com.startup.decofflaundry.di

import com.startup.decofflaundry.repository.NewsRepo
import com.startup.decofflaundry.repository.NewsRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Singleton
    @Binds
    abstract fun providesNewsRepository(newsRepoImpl: NewsRepoImpl) : NewsRepo
}