package com.fap.diplomble.collector

import com.fap.diplomble.BleDevice

abstract class AbstractCollector {

    abstract fun collect(bleDevice: List<BleDevice>)


    interface CollectorListener {
        fun onCollect(list: List<BleDevice>)
    }
}