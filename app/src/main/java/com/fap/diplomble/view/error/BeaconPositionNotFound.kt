package com.fap.diplomble.view.error

class BeaconPositionNotFound constructor(
    private val minor: Int
) : RuntimeException() {

    override val message: String
        get() = "Beacon position at Room view with minor ($minor) not found. Before pushing BLE devices you should specify beacon positions."
//        get() = "Beacon state class with minor ($minor) doesn't exists. You should native add this state."
}