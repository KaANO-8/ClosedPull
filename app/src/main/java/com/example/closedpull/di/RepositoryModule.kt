package com.example.closedpull.di

import com.example.closedpull.api.GithubService
import com.example.closedpull.repository.ClosedPrRepository
import com.example.closedpull.repository.ClosedPrRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(service: GithubService): ClosedPrRepository =
        ClosedPrRepositoryImpl(service)
}
