package com.fap.diplomble.presentation.activity

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanSettings
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fap.diplomble.domain.predict_model.KNeighborsClassifier
import com.fap.diplomble.data.bluetooth.BLEScanner
import com.fap.diplomble.domain.mapper.BeaconMapper
import com.fap.diplomble.domain.emitter.AbstractEmitter
import com.fap.diplomble.domain.emitter.TestEmitter
import com.fap.diplomble.databinding.ActivityMainBinding
import com.fap.diplomble.domain.Config
import com.fap.diplomble.domain.model.BleDevice
import com.fap.diplomble.domain.predict_model.AbstractPredictedModel
import com.fap.diplomble.util.addTo
import com.fap.diplomble.presentation.view.BeaconCoordinates
import com.fap.diplomble.presentation.view.Fingerprint
import com.fap.diplomble.presentation.view.RoomController
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            val roomController = RoomController()
            roomController.attachView(
                binding.room,
                RoomController.Settings(
                    Config.beacons,
                    Config.fingerprints
                )
            )
            viewModel.setRoomController(roomController)
            viewModel.startScan()
        }
        else Toast.makeText(this, "What?", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.start.setOnClickListener {
            permission.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopScan()
    }
}
