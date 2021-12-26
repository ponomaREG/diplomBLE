package com.fap.diplomble.distance

abstract class AbstractDistanceCalculator {

    abstract fun calculateDistance(rssi: Int, powerTx: Int): Float
}