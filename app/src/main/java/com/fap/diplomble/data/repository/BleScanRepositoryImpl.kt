package com.fap.diplomble.data.repository

import com.fap.diplomble.data.bluetooth.BLEScanner
import com.fap.diplomble.domain.repository.BleScanRepository
import com.fap.diplomble.domain.mapper.AbstractScanRecordMapper
import javax.inject.Inject

class BleScanRepositoryImpl @Inject constructor(
    private val mapper: AbstractScanRecordMapper,
    private val scanner: BLEScanner
): BleScanRepository {

    override fun startScan() {
        scanner.startScan(mapper)
    }

    override fun stopScan() {
        scanner.stopScan()
    }
}
