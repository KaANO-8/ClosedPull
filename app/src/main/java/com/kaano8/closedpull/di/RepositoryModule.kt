package com.kaano8.closedpull.di

import com.kaano8.closedpull.api.GithubService
import com.kaano8.closedpull.repository.ClosedPrRepository
import com.kaano8.closedpull.repository.ClosedPrRepositoryImpl
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
