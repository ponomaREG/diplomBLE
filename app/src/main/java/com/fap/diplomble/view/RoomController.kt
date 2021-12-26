package com.fap.diplomble.view

import android.util.Log
import com.fap.diplomble.BleDevice
import com.fap.diplomble.position.TrilaterationCalculator
import com.fap.diplomble.view.error.BeaconPositionNotFound

class RoomController {

    private var roomView: RoomView? = null

    private val trilaterationCalculator = TrilaterationCalculator()

    fun pushBleDevices(devices: List<BleDevice>) {
        roomView?.let { v ->

            val positions: Array<DoubleArray> =
                devices.map { d ->
                    // FIXME: 23.12.2021 Another beacons??? Maybe just skip this exception
                    val bleCoordinate = v.beaconList.find { it.minor == d.minor }
                        ?: throw BeaconPositionNotFound(d.minor)
                    val xBleDevice = bleCoordinate.getX(v.width)
                    val yBleDevice = bleCoordinate.getY(v.height)
                    doubleArrayOf(
                        xBleDevice.toDouble(),
                        yBleDevice.toDouble()
                    )
                }.toTypedArray()

            val distances: DoubleArray = doubleArrayOf(
                *devices.map {
                    it.distance.toDouble()
                }.toDoubleArray()
            )
            positions.zip(distances.toTypedArray()).forEach {
                Log.e("data", "x = ${it.first[0]} y = ${it.first[1]} distance = ${it.second}")
            }
            val centroid = trilaterationCalculator.getCentroid(positions, distances)
            Log.e("centroid", "${centroid.x} ${centroid.y}")
            v.setUserMark(
                centroid.x.toFloat(),
                centroid.y.toFloat()
            )
        }
    }

    fun attachView(view: RoomView) {
        roomView = view
    }
}