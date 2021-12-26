package com.fap.diplomble.bluetooth

import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.util.Log
import com.fap.diplomble.bluetooth.filter.AbstractScanRecordEmitter

class BLEScanner constructor(
    private val systemScanner: BluetoothLeScanner,
    private val systemScannerSettings: ScanSettings,
) {

    private var emitter: AbstractScanRecordEmitter? = null

    private val callBack = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            result?.let { scanResult ->
                emitter?.emit(scanResult)
            }
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            super.onBatchScanResults(results)
            Log.e("results", results.toString())
        }
    }

    fun startScan(
        adEmitter: AbstractScanRecordEmitter? = null
    ) {
        emitter = adEmitter
        systemScanner.startScan(null, systemScannerSettings, callBack)
    }

    fun stopScan() {
        systemScanner.stopScan(callBack)
    }
}