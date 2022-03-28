package com.fap.diplomble.data.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.le.*
import android.os.ParcelUuid
import com.fap.diplomble.domain.mapper.AbstractScanRecordMapper
import java.util.*
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
        val uuid = UUID.fromString("b9407f30-f5f8-466e-aff9-25556b57fe6d")
        val filters = ScanFilter.Builder()
            .setServiceUuid(ParcelUuid(uuid))
            .build()
        systemScanner.startScan(null, systemScannerSettings, callBack)
    }

    @SuppressLint("MissingPermission")
    fun stopScan() {
        systemScanner.stopScan(callBack)
    }
}
