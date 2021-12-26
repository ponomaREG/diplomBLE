package com.fap.diplomble

data class BleDevice(
    val name: String,
    val rssi: Int,
    val uuid: String,
    val power: Int,
    val major: Int,
    val minor: Int,
    val distance: Float
)
