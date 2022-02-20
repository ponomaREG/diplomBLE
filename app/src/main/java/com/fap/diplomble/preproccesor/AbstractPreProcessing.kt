package com.fap.diplomble.preproccesor

import com.fap.diplomble.BleDevice

abstract class AbstractPreProcessing {

    abstract fun preProcess(bleDevice: BleDevice): BleDevice
}