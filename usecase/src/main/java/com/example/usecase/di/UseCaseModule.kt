package com.example.usecase.di

import com.example.usecase.GetListDataUseCase
import com.example.usecase.GetListDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @get:Binds
    val GetListDataUseCaseImpl.bindGetListDataUseCase: GetListDataUseCase
}