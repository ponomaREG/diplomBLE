package com.fap.diplomble.presentation.view

class RoomController {

    private var roomView: RoomView? = null
    private var settings: Settings? = null

    fun pushCurrentRP(fingerprintId: Int) {
        roomView?.setUserMark(
            fingerprintId
        )
    }

    fun attachView(
        view: RoomView,
        settings: Settings,
    ) {
        this.roomView = view
        this.settings = settings
        view.setFingerprints(settings.fingerprints)
        view.setBeacons(settings.beacons)
    }

    data class Settings(
        val beacons: List<BeaconCoordinates>,
        val fingerprints: List<Fingerprint>
    )
}

data class Fingerprint(
    val id: Int,
    val xOffset: Float,
    val yOffset: Float
)

data class CurrentRP(
    val fingerprintId: Int
)