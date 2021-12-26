package com.fap.diplomble.position

import com.fap.diplomble.position.error.NotSameSizeException
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver
import com.lemmingapex.trilateration.TrilaterationFunction
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer

class TrilaterationCalculator {

    fun getCentroid(positions: Array<DoubleArray>, distances: DoubleArray): Centroid {
        if (positions.size != distances.size)
            throw NotSameSizeException(positions.size, distances.size)
        val solver = NonLinearLeastSquaresSolver(
            TrilaterationFunction(positions, distances),
            LevenbergMarquardtOptimizer()
        )
        val optimum = solver.solve()
        val centroid = optimum.point.toArray()
        return Centroid(centroid[0], centroid[1])
    }
}