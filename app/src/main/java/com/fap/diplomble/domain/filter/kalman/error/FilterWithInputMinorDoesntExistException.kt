package com.fap.diplomble.domain.filter.kalman.error

class FilterWithInputMinorDoesntExistException constructor(val minor: Int) : RuntimeException() {

    override val message: String
        get() = "A filter with this minor ($minor) does not exist "
}