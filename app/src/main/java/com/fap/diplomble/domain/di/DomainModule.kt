package com.fap.diplomble.domain.di

import com.fap.diplomble.domain.Config
import com.fap.diplomble.domain.emitter.AbstractEmitter
import com.fap.diplomble.domain.emitter.DeviceEmitter
import com.fap.diplomble.domain.filter.kalman.AbstractFilter
import com.fap.diplomble.domain.filter.kalman.BeaconKalmanFilter
import com.fap.diplomble.domain.filter.kalman.base.KalmanFilter
import com.fap.diplomble.domain.mapper.AbstractScanRecordMapper
import com.fap.diplomble.domain.mapper.BeaconMapper
import com.fap.diplomble.domain.predict_model.AbstractPredictedModel
import com.fap.diplomble.domain.predict_model.KNeighborsClassifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FilterModule {

    @Singleton
    @Provides
    fun provideKalmanFilter(): AbstractFilter =
        BeaconKalmanFilter(
            Config.beacons.map {
                it.minor
            }
        )
}

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindKnnModel(kNeighborsClassifier: KNeighborsClassifier): AbstractPredictedModel<Int>

    @Singleton
    @Binds
    abstract fun bindEmitter(deviceEmitter: DeviceEmitter): AbstractEmitter

    @Singleton
    @Binds
    abstract fun bindMapper(beaconMapper: BeaconMapper): AbstractScanRecordMapper
}
