package com.fap.diplomble.data.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import com.fap.diplomble.domain.mapper.AbstractScanRecordMapper
import javax.inject.Inject

class BLEScanner constructor(
    private val systemScanner: BluetoothLeScanner,
    private val systemScannerSettings: ScanSettings,
) {

    private var mapper: AbstractScanRecordMapper? = null

    private val callBack = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            result?.let { scanResult ->
                mapper?.emit(scanResult)
            }
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            super.onBatchScanResults(results)
            results?.let {
                mapper?.emit(it)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startScan(
        adMapper: AbstractScanRecordMapper? = null
    ) {
        mapper = adMapper
        systemScanner.startScan(null, systemScannerSettings, callBack)
    }

    @SuppressLint("MissingPermission")
    fun stopScan() {
        systemScanner.stopScan(callBack)
    }
}