package com.fap.diplomble.domain.emitter

import com.fap.diplomble.domain.model.BleDevice
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StubEmitter @Inject constructor(): AbstractEmitter() {

    private val publishSubject: PublishSubject<List<BleDevice>> = PublishSubject.create()

    override fun collect(bleDevice: List<BleDevice>) {
        publishSubject.onNext(bleDevice)
    }

    override fun source(): Observable<List<BleDevice>> =
        mockBleDevicesSource().flatMapIterable {
            Iterable { it.iterator() }
        }.concatMap {
            Observable.just(it).delay(1000, TimeUnit.MILLISECONDS)
        }

    private fun mockBleDevicesSource(): Observable<List<List<BleDevice>>> =
        Observable.fromArray(mockBleDevices())
            .map {
                it.map {
                    it.map {
                        BleDevice(
                            name = "Device",
                            rssi = it.second,
                            uuid = "UUID",
                            major = 1,
                            minor = it.first
                        )
                    }
                }
            }


    private fun mockBleDevices():List<List<Pair<Int, Int>>> = listOf(
        listOf(
            4032 to -90,
            4034 to -50,
            4037 to -80,
            4040 to -80,
        ),
        listOf(
            4032 to -89,
            4034 to -60,
            4037 to -80,
            4040 to -80,
        ),
        listOf(
            4032 to -60,
            4034 to -70,
            4037 to -80,
            4040 to -80,
        ),
        listOf(
            4032 to -89,
            4034 to -60,
            4037 to -80,
            4040 to -80,
        ),
        listOf(
            4032 to -90,
            4034 to -50,
            4037 to -80,
            4040 to -80,
        ),listOf(
            4032 to -89,
            4034 to -60,
            4037 to -80,
            4040 to -80,
        ),
        listOf(
            4032 to -60,
            4034 to -70,
            4037 to -80,
            4040 to -80,
        ),
        listOf(
            4032 to -89,
            4034 to -60,
            4037 to -80,
            4040 to -80,
        ),
        listOf(
            4032 to -90,
            4034 to -50,
            4037 to -80,
            4040 to -80,
        ),
        listOf(
            4032 to -89,
            4034 to -60,
            4037 to -80,
            4040 to -80,
        ),
    )
}
