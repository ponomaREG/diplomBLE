package com.fap.diplomble.domain.usecase

import com.fap.diplomble.domain.repository.BleScanRepository
import javax.inject.Inject

class StopScanUseCase @Inject constructor(
    private val bleScanRepository: BleScanRepository
) {

    operator fun invoke() = bleScanRepository.stopScan()
}