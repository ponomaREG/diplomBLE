package com.fap.diplomble

import android.util.TypedValue

data class BleDevice(
    val name: String,
    val rssi: Int,
    val uuid: String,
    val power: Int,
    val major: Int,
    val minor: Int,
    var distance: Float?
)
