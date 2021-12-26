package com.fap.diplomble

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanSettings
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.fap.diplomble.bluetooth.BLEScanner
import com.fap.diplomble.bluetooth.filter.BeaconEmitter
import com.fap.diplomble.collector.BeaconCollector
import com.fap.diplomble.databinding.ActivityMainBinding
import com.fap.diplomble.distance.AbstractDistanceCalculator
import com.fap.diplomble.distance.PowDistanceCalculator
import com.fap.diplomble.view.BeaconCoordinates
import com.fap.diplomble.view.RoomController


class MainActivity : AppCompatActivity(), BeaconCollector.CollectorListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var scanner: BLEScanner

    private val beaconCollector: BeaconCollector = BeaconCollector().also {
        it.setListener(this)
    }

    private val roomController = RoomController()

    private val beacons = listOf(
        BeaconCoordinates(
            minor = 4032,
            xOffset = 0.80f,
            yOffset = 0.90f
        ),
        BeaconCoordinates(
            minor = 4037,
            xOffset = 0.05f,
            yOffset = 0f
        ),
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
        beaconCollector.onClear()
    }

    override fun onCollect(list: List<BleDevice>) {
        updateMap(list)
    }

    private fun setBeaconsAtMap() {
        binding.room.setBeacons(beacons)
    }

    private fun startBleScan() {
        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
            .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
            .setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE)
            .build()
        scanner = BLEScanner(
            BluetoothAdapter.getDefaultAdapter().bluetoothLeScanner,
            settings
        )
        val distanceFromRssiCalculator: AbstractDistanceCalculator = PowDistanceCalculator()
        scanner.startScan(
            adEmitter = BeaconEmitter(
                distanceCalculator = distanceFromRssiCalculator,
                supportedMinors = beacons.map { it.minor },
                collector = beaconCollector
            )
        )
    }

    private fun updateMap(bleDevices: List<BleDevice>) {
        roomController.pushBleDevices(bleDevices)
    }
}
