package com.fap.diplomble.collector

//
//class BeaconCollector(
//    private val preProcessor: AbstractRssiPreProcessing
//) : AbstractCollector() {
//
//    private val subject: PublishSubject<BleDevice> = PublishSubject.create()
//    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
//
//    init {
//        subject
////            .doOnNext { Log.e("device", it.toString()) }
//            .buffer(1000L, TimeUnit.MILLISECONDS)
//            .filter { devices ->
//                devices.isNotEmpty()
//            }
//            .map { devices ->
//                devices.reversed().distinctBy { it.minor }
//            }
//            .filter { devices ->
//                devices.size > 1
//            }
//            .map { devices ->
//                devices.map { device ->
//                    device.copy(
//                        rssi = preProcessor.preProcess(
//                            minor = device.minor,
//                            rssi = device.rssi
//                        )
//                    )
//                }
//            }
//            .subscribe(
//                { devices ->
//                    listener?.onCollect(devices)
//                },
//                { e ->
//                    Log.e("error", e.stackTraceToString())
//                }
//            ).addTo(compositeDisposable)
//    }
//
//    private var listener: CollectorListener? = null
//
//    override fun collect(bleDevice: BleDevice) {
//        subject.onNext(bleDevice)
//    }
//
//    fun onClear() {
//        compositeDisposable.clear()
//    }
//
//    fun setListener(collectorListener: CollectorListener) {
//        listener = collectorListener
//    }
//
//    interface CollectorListener {
//        fun onCollect(list: List<BleDevice>)
//    }
//}