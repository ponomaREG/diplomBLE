package com.fap.diplomble.domain.emitter

import com.fap.diplomble.domain.model.BleDevice
import io.reactivex.Observable

abstract class AbstractEmitter {

    abstract fun collect(bleDevice: List<BleDevice>)

    abstract fun source(): Observable<List<BleDevice>>
}