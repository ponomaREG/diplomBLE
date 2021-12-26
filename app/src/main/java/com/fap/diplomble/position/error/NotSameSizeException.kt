package com.fap.diplomble.position.error

class NotSameSizeException constructor(
    val beaconsSize: Int,
    val distanceSize: Int
) : RuntimeException() {


    override val message: String
        get() = "The number of beacons and the number of distances to them are not the same. " +
            "Size of beacon array - $beaconsSize, when size of distances array: $distanceSize"
}