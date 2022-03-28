package com.fap.diplomble.domain.emitter

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.fap.diplomble.domain.model.BleDevice
import io.reactivex.Observable

class TestEmitter: AbstractEmitter() {

    private val rssi4032: MutableList<Int> = mutableListOf()
    private val rssi4034: MutableList<Int> = mutableListOf()
    private val rssi4037: MutableList<Int> = mutableListOf()
    private val rssi4040: MutableList<Int> = mutableListOf()

    private val rssiMap: HashMap<Int, MutableList<Int>> = hashMapOf(
        4032 to rssi4032,
        4034 to rssi4034,
        4037 to rssi4037,
        4040 to rssi4040
    )

    @RequiresApi(Build.VERSION_CODES.N)
    override fun collect(bleDevice: List<BleDevice>) {
        rssiMap.keys.forEach { k ->
            val bleDev = bleDevice.find { it.minor == k }
            if (bleDev == null) rssiMap[k]?.add(-200)
            else rssiMap[k]?.add(bleDev.rssi)
        }
        for(key in rssiMap.keys) {
            Log.e("$key", rssiMap[key].toString())
        }
        Log.e("N", "\n")
    }

    override fun source(): Observable<List<BleDevice>> = Observable.empty()
}
