package com.fap.diplomble.collector

import android.util.Log
import com.fap.diplomble.BleDevice
import com.fap.diplomble.preproccesor.AbstractPreProcessing
import com.fap.diplomble.util.log

class DeviceCollector(
    private val preProcessing: AbstractPreProcessing,
): AbstractCollector() {

    private var listener: CollectorListener? = null

    //TODO: Может перенести эту логику в эмиттер
    override fun collect(bleDevice: List<BleDevice>) {
        if (bleDevice.size < 2) return
        bleDevice.log("devices") {
            it.toString()
        }
        listener?.onCollect(
            bleDevice.map {
                preProcessing.preProcess(it)
            }
        )

    }

    fun setListener(collectorListener: CollectorListener) {
        listener = collectorListener
    }

    fun clearListener() {
        listener = null
    }

}