package com.fap.diplomble.preproccesor

import com.fap.diplomble.BleDevice
import com.fap.diplomble.distance.PowDistanceCalculator
import com.fap.diplomble.emitter.kalman.AbstractFilter

class DefaultPreProccesor constructor(
    private val filter: AbstractFilter,
    private val distanceCalculator: PowDistanceCalculator
): AbstractPreProcessing() {

    override fun preProcess(bleDevice: BleDevice): BleDevice {
        val kalmanRssi = filter.filter(bleDevice.minor, bleDevice.rssi)
        return bleDevice.copy(
            rssi = kalmanRssi,
            distance = distanceCalculator.calculateDistance(kalmanRssi, bleDevice.power)
        )
    }
}