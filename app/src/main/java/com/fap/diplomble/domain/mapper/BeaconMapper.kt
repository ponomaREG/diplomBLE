package com.fap.diplomble.domain.mapper

import android.bluetooth.le.ScanResult
import com.fap.diplomble.domain.model.BleDevice
import com.fap.diplomble.domain.emitter.AbstractEmitter
import com.neovisionaries.bluetooth.ble.advertising.ADPayloadParser
import com.neovisionaries.bluetooth.ble.advertising.IBeacon
import javax.inject.Inject

class BeaconMapper @Inject constructor(
    private val emitter: AbstractEmitter
) : AbstractScanRecordMapper() {

    override fun emit(scanResult: ScanResult) {
        parseAndMap(scanResult)?.let {
            emitter.collect(it)
        }
    }

    override fun emit(scanResults: List<ScanResult>) {
        val devices = mutableListOf<BleDevice>()
        scanResults.forEach {
            val result = parseAndMap(it)
            result?.let { res ->
                devices.addAll(res)
            }
        }
        emitter.collect(
            devices.distinctBy { it.minor }.sortedBy { it.minor }
        )
    }

    private fun parseAndMap(scanResult: ScanResult): List<BleDevice>? {
        if (scanResult.scanRecord == null) return null
        val listAd = ADPayloadParser.getInstance().parse(scanResult.scanRecord!!.bytes)
        val devices = mutableListOf<BleDevice>()
        listAd.forEach { adStructure ->
            if (adStructure is IBeacon) {
                val model =
                    BleDevice(
                        minor = adStructure.minor,
                        major = adStructure.major,
                        name = scanResult.scanRecord!!.deviceName ?: "NULL",
                        rssi = scanResult.rssi,
                        uuid = adStructure.uuid.toString(),
                    )
                devices.add(model)
            }
        }
        return devices
    }
}