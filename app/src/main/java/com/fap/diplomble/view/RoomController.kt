package com.fap.diplomble.view

import android.os.Build
import android.util.Log
import android.util.TypedValue
import androidx.annotation.RequiresApi
import com.fap.diplomble.BleDevice
import com.fap.diplomble.position.TrilaterationCalculator
import com.fap.diplomble.util.log
import com.fap.diplomble.view.error.BeaconPositionNotFound

class RoomController {

    private var roomView: RoomView? = null

    private val trilaterationCalculator = TrilaterationCalculator()

//    private val dG: HashMap<Int, List<Int>> = hashMapOf()
//    private val dD: HashMap<Int, List<Float>> = hashMapOf()

    @RequiresApi(Build.VERSION_CODES.N)
    fun pushBleDevices(devices: List<BleDevice>) {
        roomView?.let { v ->
            val positions: Array<DoubleArray> =
                devices.map { d ->
                    d.distance = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        d.distance!! * 100,
                        v.context.resources.displayMetrics
                    )
//                    val dataqwe = dG.getOrDefault(d.minor, mutableListOf())
//                    val datadistance = dD.getOrDefault(d.minor, mutableListOf())
//                    val newData = dataqwe.toMutableList().also { it.add(d.rssi) }
//                    val newDataDistance = datadistance.toMutableList().also { it.add(d.distance!!) }
//                    dG[d.minor] = newData
//                    dD[d.minor] = newDataDistance
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
//            dG.log("data") { k,v ->
//                "minorRssiData$k = $v"
//            }
//            dD.log("data") { k,v ->
//                "minorDistanceData$k = $v"
//            }
            val distances: DoubleArray = doubleArrayOf(
                *devices.map {
                    it.distance!!.toDouble()
                }.toDoubleArray()
            )
            devices.log("DEVICES") {
                it.toString()
            }
            v.setAreaCircles(distances.toList().map { it.toFloat() })
//            positions.zip(distances.toTypedArray()).forEach {
//                Log.e("data", "x = ${it.first[0]} y = ${it.first[1]} distance = ${it.second}")
//            }
            val centroid = trilaterationCalculator.getCentroid(positions, distances)
//            Log.e("centroid", "${centroid.x} ${centroid.y}")
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