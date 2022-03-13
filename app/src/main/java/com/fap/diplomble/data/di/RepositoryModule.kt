package com.fap.diplomble.data.di

import com.fap.diplomble.data.repository.BleScanRepositoryImpl
import com.fap.diplomble.domain.repository.BleScanRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBleScanRepository(bleScanRepository: BleScanRepositoryImpl): BleScanRepository
}