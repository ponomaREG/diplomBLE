package com.fap.diplomble.bluetooth.filter

import android.bluetooth.le.ScanResult
import com.fap.diplomble.BleDevice
import com.fap.diplomble.collector.AbstractCollector
import com.fap.diplomble.distance.AbstractDistanceCalculator
import com.neovisionaries.bluetooth.ble.advertising.ADPayloadParser
import com.neovisionaries.bluetooth.ble.advertising.IBeacon

class BeaconEmitter(
    private val supportedMinors: List<Int>,
    private val distanceCalculator: AbstractDistanceCalculator,
    private val collector: AbstractCollector
) : AbstractScanRecordEmitter() {

    override fun emit(scanResult: ScanResult) {
        if (scanResult.scanRecord == null) return
        val listAd = ADPayloadParser.getInstance().parse(scanResult.scanRecord!!.bytes)
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
                            distance = distanceCalculator.calculateDistance(scanResult.rssi, adStructure.power)
                        )
                    collector.collect(model)
                }
            }
        }
    }


}