package homework2

import utils.matrix.*
import kotlin.math.abs
import kotlin.math.log
import kotlin.math.max

const val eps: Double = 0.00001

fun getHD(matrix: Array<Array<Double>>): Array<Array<Double>>? {
    return matrixDifference(getUnitMatrix(matrix.size), multiplyMatrix(getInverseMatrix(getD(matrix)), matrix)!!)
}

fun getGD(inverseD: Array<Array<Double>>, b: Array<Double>): Array<Double>? {
    return multiplyMatrixByVector(inverseD, b)
}

fun getP(eigenValues: Array<Double>): Double {
    var result = 0.0
    eigenValues.forEach {
        result = max(result, abs(it))
    }
    return result
}

fun getHl(h: Array<Array<Double>>): Array<Array<Double>> {
    var result = arrayOf<Array<Double>>()
    for (i in h.indices) {
        var array = arrayOf<Double>()
        for (j in h[i].indices) {
            array += if (i < j) {
                h[i][j]
            } else {
                0.0
            }
        }
        result += array
    }
    return result
}

fun getHr(h: Array<Array<Double>>): Array<Array<Double>> {
    var result = arrayOf<Array<Double>>()
    for (i in h.indices) {
        var array = arrayOf<Double>()
        for (j in h[i].indices) {
            array += if (i >= j) {
                h[i][j]
            } else {
                0.0
            }
        }
        result += array
    }
    return result
}

fun calculatePrioriEstimation(h: Array<Array<Double>>, g: Array<Double>, x0: Array<Double>): Double {
    val hNorm = holderMatrixNorm(h)
    val hk = eps / (holderNorm(x0) + holderNorm(g) / (1 - hNorm))
    return log(hk, 10.0) / log(hNorm, 10.0)
}