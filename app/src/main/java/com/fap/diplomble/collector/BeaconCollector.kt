package com.fap.diplomble.collector

import android.util.Log
import com.fap.diplomble.BleDevice
import com.fap.diplomble.util.addTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class BeaconCollector : AbstractCollector() {

    private val subject: PublishSubject<BleDevice> = PublishSubject.create()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        subject
            .doOnNext { Log.e("device", it.toString()) }
            .buffer(1000L, TimeUnit.MILLISECONDS)
            .filter { devices ->
                devices.isNotEmpty()
            }
            .map { devices ->
                devices.reversed().distinctBy { it.minor }
            }
            .filter { devices ->
                devices.size > 1
            }
            .subscribe(
                { devices ->
                    listener?.onCollect(devices)
                },
                { e ->
                    Log.e("error", e.stackTraceToString())
                }
            ).addTo(compositeDisposable)
    }

    private var listener: CollectorListener? = null

    override fun collect(bleDevice: BleDevice) {
        subject.onNext(bleDevice)
    }

    fun onClear() {
        compositeDisposable.clear()
    }

    fun setListener(collectorListener: CollectorListener) {
        listener = collectorListener
    }

    interface CollectorListener {
        fun onCollect(list: List<BleDevice>)
    }
}