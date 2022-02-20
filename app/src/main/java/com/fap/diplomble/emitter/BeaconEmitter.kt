package com.fap.diplomble.emitter

import android.bluetooth.le.ScanResult
import com.fap.diplomble.BleDevice
import com.fap.diplomble.collector.AbstractCollector
import com.fap.diplomble.util.log
import com.neovisionaries.bluetooth.ble.advertising.ADPayloadParser
import com.neovisionaries.bluetooth.ble.advertising.IBeacon

class BeaconEmitter(
    private val supportedMinors: List<Int>,
    private val collector: AbstractCollector
) : AbstractScanRecordEmitter() {

    override fun emit(scanResult: ScanResult) {
        parseAndMap(scanResult)?.let {
            collector.collect(it)
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
        devices.log("parsedDevices") {
            it.toString()
        }
        collector.collect(
            devices.distinctBy { it.minor }.sortedBy { it.minor }
        )
    }

    private fun parseAndMap(scanResult: ScanResult): List<BleDevice>? {
        if (scanResult.scanRecord == null) return null
        val listAd = ADPayloadParser.getInstance().parse(scanResult.scanRecord!!.bytes)
        val devices = mutableListOf<BleDevice>()
        listAd.forEach { adStructure ->
            if (adStructure is IBeacon) {
                if (supportedMinors.contains(adStructure.minor)) {
                    val model =
                        BleDevice(
                            minor = adStructure.minor,
                            major = adStructure.major,
                            name = scanResult.scanRecord!!.deviceName ?: "NULL",
                            power = adStructure.power,
                            rssi = scanResult.rssi,
                            uuid = adStructure.uuid.toString(),
                            distance = null
                        )
                    devices.add(model)
                }
            }
        }
        return devices
    }
}