package com.fap.diplomble.emitter.kalman

abstract class AbstractFilter {

    abstract fun filter(minor: Int, rssi: Int): Int
}