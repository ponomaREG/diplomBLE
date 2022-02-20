package com.fap.diplomble.emitter.kalman

import com.fap.diplomble.emitter.kalman.base.KalmanFilter
import com.fap.diplomble.emitter.kalman.error.FilterWithInputMinorDoesntExistException

class BeaconKalmanFilter constructor(
    minors: List<Int>
): AbstractFilter(){

    private val filters: HashMap<Int, KalmanFilter> = hashMapOf()

    init {
        minors.forEach { minor ->
            filters[minor] = KalmanFilter(
                KalmanFilter.KALMAN_R,
                KalmanFilter.KALMAN_Q
            )
        }
    }

    /**
     * @param minor - Beacon Minor
     * @return Kalman RSSI
     */
    override fun filter(minor: Int, rssi: Int): Int {
        return filters[minor]?.applyFilter(rssi.toDouble())?.toInt()
            ?: throw FilterWithInputMinorDoesntExistException(minor)
    }
}