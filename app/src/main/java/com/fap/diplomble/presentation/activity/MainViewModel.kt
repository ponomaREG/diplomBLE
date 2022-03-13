package com.fap.diplomble.presentation.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.fap.diplomble.domain.emitter.AbstractEmitter
import com.fap.diplomble.domain.model.BleDevice
import com.fap.diplomble.domain.predict_model.AbstractPredictedModel
import com.fap.diplomble.domain.predict_model.KNeighborsClassifier
import com.fap.diplomble.domain.repository.BleScanRepository
import com.fap.diplomble.domain.usecase.StartScanUseCase
import com.fap.diplomble.presentation.view.RoomController
import com.fap.diplomble.util.addTo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val startScanUseCase: StartScanUseCase,
    private val stopScanUseCase: StartScanUseCase,
    private val emitter: AbstractEmitter
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var model: AbstractPredictedModel<Int> = KNeighborsClassifier.get()

    private var roomController: RoomController? = null

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun setRoomController(roomController: RoomController) {
        this.roomController = roomController
    }

    fun startScan(){
        startScanUseCase()
        startListening()
    }

    fun stopScan() = stopScanUseCase()

    private fun startListening() {
        emitter.source()
            .subscribeOn(Schedulers.io())
            .map { list ->
                model.predict(
                    list.sortedBy {
                        it.minor
                    }.map {
                        it.rssi.toDouble()
                    }.toDoubleArray()
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { predictedId ->
                updateMap(predictedId)
            }
            .addTo(compositeDisposable)
    }

    private fun updateMap(fingerprintId: Int) {
        roomController?.pushCurrentRP(fingerprintId)
    }
}