package com.fap.diplomble.filter.kalman.base

class KalmanFilter {

    companion object {
        const val KALMAN_R = 0.125
        const val KALMAN_Q = 0.5
    }

    private var R //  Process Noise
        : Double
    private var Q //  Measurement Noise
        : Double
    private var A //  State Vector
        : Double
    private var B //  Control Vector
        : Double
    private var C //  Measurement Vector
        : Double
    private var x //  Filtered Measurement Value (No Noise)
        : Double? = null
    private var cov //  Covariance
        = 0.0

    constructor(r: Double, q: Double, a: Double, b: Double, c: Double) {
        R = r
        Q = q
        A = a
        B = b
        C = c
    }

    constructor(r: Double, q: Double) {
        R = r
        Q = q
        A = 1.0
        B = 0.0
        C = 1.0
    }

    /** Public Methods  */
    fun applyFilter(rssi: Double): Double {
        return applyFilter(rssi, 0.0)
    }

    /**
     * Filters a measurement
     *
     * @param measurement The measurement value to be filtered
     * @param u The controlled input value
     * @return The filtered value
     */
    fun applyFilter(measurement: Double, u: Double): Double {
        val predX: Double //  Predicted Measurement Value
        val K: Double //  Kalman Gain
        val predCov: Double //  Predicted Covariance
        if (x == null) {
            x = 1 / C * measurement
            cov = 1 / C * Q * (1 / C)
        } else {
            predX = predictValue(u)
            predCov = uncertainty
            K = predCov * C * (1 / (C * predCov * C + Q))
            x = predX + K * (measurement - C * predX)
            cov = predCov - K * C * predCov
        }
        return x as Double
    }

    /** Private Methods  */
    private fun predictValue(control: Double): Double {
        return A * x!! + B * control
    }

    private val uncertainty: Double
        get() = A * cov * A + R

    override fun toString(): String {
        return "KalmanFilter{" +
            "R=" + R +
            ", Q=" + Q +
            ", A=" + A +
            ", B=" + B +
            ", C=" + C +
            ", x=" + x +
            ", cov=" + cov +
            '}'
    }
}