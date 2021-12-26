package com.fap.diplomble.distance

import kotlin.math.pow

class PowDistanceCalculator : AbstractDistanceCalculator() {

    companion object {
        private const val N = 4
    }

    override fun calculateDistance(rssi: Int, powerTx: Int): Float {
        if (rssi == 0) {
            return (-1.0).toFloat()
        }
        val timesValue: Float = (powerTx - rssi) / (10 * N).toFloat()
        return 10.toDouble().pow(timesValue.toDouble()).toFloat()
    }
}