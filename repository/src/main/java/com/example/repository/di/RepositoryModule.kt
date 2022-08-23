package com.example.repository.di

import com.example.repository.ListRepository
import com.example.repository.ListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @get:Binds val ListRepositoryImpl.bindListRepository: ListRepository
}