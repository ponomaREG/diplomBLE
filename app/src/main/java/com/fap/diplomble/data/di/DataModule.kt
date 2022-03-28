package com.fap.diplomble.data.di

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanSettings
import com.fap.diplomble.data.bluetooth.BLEScanner
import com.fap.diplomble.data.repository.BleScanRepositoryImpl
import com.fap.diplomble.domain.repository.BleScanRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideBleScannerSettings() = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
        .setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE)
        .setNumOfMatches(ScanSettings.MATCH_NUM_MAX_ADVERTISEMENT)
        .setReportDelay(500L)
        .build()

    @Singleton
    @Provides
    fun provideBleScanner(scanSettings: ScanSettings) = BLEScanner(
        BluetoothAdapter.getDefaultAdapter().bluetoothLeScanner,
        scanSettings
    )
}
