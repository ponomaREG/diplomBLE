package com.fap.diplomble.domain.usecase

import com.fap.diplomble.domain.repository.BleScanRepository
import javax.inject.Inject

class StartScanUseCase @Inject constructor(
    private val bleScanRepository: BleScanRepository
) {

    operator fun invoke() {
        bleScanRepository.startScan()
    }
}