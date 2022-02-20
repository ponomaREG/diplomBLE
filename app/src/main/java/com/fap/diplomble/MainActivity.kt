package com.fap.diplomble

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanSettings
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.fap.diplomble.bluetooth.BLEScanner
import com.fap.diplomble.emitter.BeaconEmitter
import com.fap.diplomble.collector.AbstractCollector
import com.fap.diplomble.collector.DeviceCollector
import com.fap.diplomble.databinding.ActivityMainBinding
import com.fap.diplomble.distance.PowDistanceCalculator
import com.fap.diplomble.emitter.kalman.BeaconKalmanFilter
import com.fap.diplomble.preproccesor.DefaultPreProccesor
import com.fap.diplomble.view.BeaconCoordinates
import com.fap.diplomble.view.RoomController


class MainActivity : AppCompatActivity(), AbstractCollector.CollectorListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var scanner: BLEScanner

    private val roomController = RoomController()

    private val beacons = listOf(
        BeaconCoordinates(
            minor = 4032,
            xOffset = 0.02f,
            yOffset = 0.02f
        ),
        BeaconCoordinates(
            minor = 4034,
            xOffset = 0.95f,
            yOffset = 0.05f
        ),
        BeaconCoordinates(
            minor = 4037,
            xOffset = 0.05f,
            yOffset = 0.98f
        ),
        BeaconCoordinates(
            minor = 4040,
            xOffset = 0.95f,
            yOffset = 0.98f
        ),
    )

    private val beaconCollector: DeviceCollector = DeviceCollector(
        preProcessing = DefaultPreProccesor(
            filter = BeaconKalmanFilter(
                minors = beacons.map { it.minor }
            ),
            distanceCalculator = PowDistanceCalculator()
        )
    )

    private val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) startBleScan()
        else Toast.makeText(this, "What?", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.start.setOnClickListener {
            roomController.attachView(binding.room)
            setBeaconsAtMap()
            permission.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    override fun onStop() {
        super.onStop()
        scanner.stopScan()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCollect(list: List<BleDevice>) {
        updateMap(list)
    }

    private fun setBeaconsAtMap() {
        binding.room.setBeacons(beacons)
    }

    private fun startBleScan() {
        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
            .setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE)
            .setNumOfMatches(ScanSettings.MATCH_NUM_ONE_ADVERTISEMENT)
            .setReportDelay(100L)
            .build()
        scanner = BLEScanner(
            BluetoothAdapter.getDefaultAdapter().bluetoothLeScanner,
            settings
        )
        scanner.startScan(
            adEmitter = BeaconEmitter(
                supportedMinors = beacons.map { it.minor },
                collector = beaconCollector.also { it.setListener(this) }
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateMap(bleDevices: List<BleDevice>) {
        roomController.pushBleDevices(bleDevices)
    }
}
