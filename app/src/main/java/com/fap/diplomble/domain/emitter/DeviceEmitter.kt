package com.fap.diplomble.domain.emitter

import android.util.Log
import com.fap.diplomble.domain.filter.kalman.AbstractFilter
import com.fap.diplomble.domain.model.BleDevice
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class DeviceEmitter @Inject constructor(
    private val filter: AbstractFilter
): AbstractEmitter() {

    private val publishSubject: PublishSubject<List<BleDevice>> = PublishSubject.create()

    override fun collect(bleDevice: List<BleDevice>) {
        publishSubject.onNext(bleDevice)
    }

    override fun source(): Observable<List<BleDevice>> = publishSubject
        .filter {
            it.size >= 4
        }
        .map { list ->
            list.map { device ->
                device.copy(
                    rssi = filter.filter(device.minor, device.rssi)
                )
            }
        }
}