package com.fap.diplomble.emitter

import android.bluetooth.le.ScanResult

abstract class AbstractScanRecordEmitter {

    abstract fun emit(scanResult: ScanResult)

    abstract fun emit(scanResults: List<ScanResult>)
}