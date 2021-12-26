package com.fap.diplomble

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fap.diplomble.databinding.ActivityTrilationBinding


class TrilationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrilationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrilationBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
//        binding.start.setOnClickListener {
//            start()
//        }
    }

//    private fun updateMap(bleDevice: BleDevice) {
//        val coordinates = binding.room.beaconList
//        val positions: MutableList<DoubleArray> = mutableListOf()
//        val distances = DoubleArray(coordinates.size)
//        var counter = 0
//        coordinates.forEach {
//            val doubleArray = DoubleArray(2)
//            doubleArray[0] = it.widthPos.toDouble()
//            doubleArray[1] = it.heightPos.toDouble()
//            distances[counter] = it.distance.toDouble()
//            positions.add(doubleArray)
//            counter++
//        }
//        counter = 0
//        val solver = NonLinearLeastSquaresSolver(
//            TrilaterationFunction(positions.toTypedArray(), distances),
//            LevenbergMarquardtOptimizer()
//        )
//        val optimum = solver.solve()
//        val centroid = optimum.point.toArray()
//        binding.room.setUserMark(centroid[0].toFloat(), centroid[1].toFloat())
//    }
//
//    private fun start() {
//        binding.room.setBeacons(
//            listOf(
//                BeaconCoordinates(
//                    minor = 4032,
//                    side = SIDE.BOTTOM,
//                    offset = 0.95f,
//                    distance = -1f,
//                    widthPos = -1f,
//                    heightPos = -1f
//                ),
//                BeaconCoordinates(
//                    minor = 4037,
//                    side = SIDE.TOP,
//                    offset = 0.05f,
//                    distance = -1f,
//                    widthPos = -1f,
//                    heightPos = -1f
//                ),
//            )
//        )
//    }
}