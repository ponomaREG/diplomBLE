package com.fap.diplomble.domain.filter.kalman

abstract class AbstractFilter {

    abstract fun filter(minor: Int, rssi: Int): Int
}