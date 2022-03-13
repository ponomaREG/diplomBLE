package com.fap.diplomble.domain

import com.fap.diplomble.presentation.view.BeaconCoordinates
import com.fap.diplomble.presentation.view.Fingerprint

object Config {


    val beacons = listOf(
        BeaconCoordinates(
            minor = 4032,
            xOffset = 0.02f,
            yOffset = 0.02f
        ),
        BeaconCoordinates(
            minor = 4034,
            xOffset = 0.95f,
            yOffset = 0.05f
        ),
        BeaconCoordinates(
            minor = 4037,
            xOffset = 0.95f,
            yOffset = 0.98f
        ),
        BeaconCoordinates(
            minor = 4040,
            xOffset = 0.05f,
            yOffset = 0.98f
        ),
    )

    val fingerprints: List<Fingerprint> = listOf(
        Fingerprint(
            0,
            0.25f,
            0.25f
        ),
        Fingerprint(
            1,
            0.5f,
            0.25f
        ),
        Fingerprint(
            2,
            0.75f,
            0.25f
        ),
        Fingerprint(
            3,
            0.25f,
            0.5f
        ),
        Fingerprint(
            4,
            0.5f,
            0.5f
        ),
        Fingerprint(
            5,
            0.75f,
            0.5f
        ),
        Fingerprint(
            6,
            0.25f,
            0.75f
        ),
        Fingerprint(
            7,
            0.5f,
            0.75f
        ),
        Fingerprint(
            8,
            0.75f,
            0.75f
        ),
    )
}