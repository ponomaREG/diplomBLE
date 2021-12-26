package com.fap.diplomble.bluetooth.filter

import android.bluetooth.le.ScanResult

abstract class AbstractScanRecordEmitter {

    abstract fun emit(scanResult: ScanResult)
}