package com.fap.diplomble.domain.repository

import com.fap.diplomble.domain.model.BleDevice

interface BleScanRepository {

    fun startScan()
    fun stopScan()
}