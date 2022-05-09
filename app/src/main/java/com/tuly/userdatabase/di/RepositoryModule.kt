package com.tuly.userdatabase.di

import com.tuly.userdatabase.data.repository.UserRepositoryImpl
import com.tuly.userdatabase.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImplementation: UserRepositoryImpl): UserRepository
}