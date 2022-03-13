package com.fap.diplomble.domain.mapper

import android.bluetooth.le.ScanResult

abstract class AbstractScanRecordMapper {

    abstract fun emit(scanResult: ScanResult)

    abstract fun emit(scanResults: List<ScanResult>)
}