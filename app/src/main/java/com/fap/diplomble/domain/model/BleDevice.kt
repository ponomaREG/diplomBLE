package com.fap.diplomble.domain.model

data class BleDevice(
    val name: String,
    val rssi: Int,
    val uuid: String,
    val major: Int,
    val minor: Int,
)
