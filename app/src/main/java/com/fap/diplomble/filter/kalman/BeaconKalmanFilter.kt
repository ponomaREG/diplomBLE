package com.fap.diplomble.filter.kalman

import com.fap.diplomble.BleDevice
import com.fap.diplomble.filter.kalman.base.KalmanFilter
import com.fap.diplomble.filter.kalman.error.FilterWithInputMinorDoesntExistException

class BeaconKalmanFilter constructor(
    beacons: List<BleDevice>
) {

    private val filters: HashMap<Int, KalmanFilter> = hashMapOf()

    init {
        beacons.forEach { beacon ->
            filters[beacon.minor] = KalmanFilter(
                KalmanFilter.KALMAN_R,
                KalmanFilter.KALMAN_Q
            )
        }
    }

    /**
     * @param minor - Beacon Minor
     * @return Kalman RSSI
     */
    fun applyFilter(minor: Int, rssi: Double): Int {
        return filters[minor]?.applyFilter(rssi)?.toInt()
            ?: throw FilterWithInputMinorDoesntExistException(minor)
    }
}