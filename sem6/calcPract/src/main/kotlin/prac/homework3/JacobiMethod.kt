package prac.homework3

import utils.RED
import utils.matrix.*
import kotlin.math.*

/**
 * Rotation method(Jacobi), calculates eigenvalues and eigenvectors of matrix
 * @param matrix - matrix to process
 * @param eps - estimation
 */
class JacobiMethod(private val matrix: Array<Array<Double>>, private val eps: Double) {

    lateinit var eigenValues: Array<Double>
    var eigenVectors: Array<Array<Double>>
    private var iK: Int = 0
    private var jK: Int = 0
    private var c: Double = 0.0
    private var s: Double = 0.0

    private fun getAIJ(a: Array<Array<Double>>): Double {
        if (a.size < 2 || a[0].size < 2) {
            return 0.0
        }
        var maximum = a[0][1]
        iK = 0
        jK = 1
        for (i in a.indices) {
            for (j in i + 1 until a[i].size) {
                if (abs(a[i][j]) > abs(maximum)) {
                    maximum = a[i][j]
                    iK = i
                    jK = j
                }
            }
        }
        return maximum
    }

    private fun getFiK(aK: Array<Array<Double>>): Double {
        if (aK[iK][iK] == aK[jK][jK]) {
            return PI / 4
        }
        return atan(2 * aK[iK][jK] / (aK[iK][iK] - aK[jK][jK])) / 2
    }

    private fun calculateC(aK: Array<Array<Double>>, d: Double): Double {
        return sqrt((1 + abs(aK[iK][iK] - aK[jK][jK]) / d) / 2.0)
    }

    private fun calculateS(aK: Array<Array<Double>>, d: Double): Double {
        return sign(aK[iK][jK] * (aK[iK][iK] - aK[jK][jK])) *
                sqrt((1 - abs(aK[iK][iK] - aK[jK][jK]) / d) / 2.0)
    }

    private fun updatedAK(matrix: Array<Array<Double>>, c: Double, s: Double): Array<Array<Double>> {
        val result = copyOf(matrix)
        for (i in matrix.indices) {
            if (i != iK && i != jK) {
                result[i][iK] = c * matrix[i][iK] + s * matrix[i][jK]
                result[iK][i] = result[i][iK]
                result[i][jK] = -s * matrix[i][iK] + c * matrix[i][jK]
                result[jK][i] = result[i][jK]
            }
        }
        result[iK][iK] = c * c * matrix[iK][iK] + 2 * c * s * matrix[iK][jK] + s * s * matrix[jK][jK]
        result[jK][jK] = s * s * matrix[iK][iK] - 2 * c * s * matrix[iK][jK] + c * c * matrix[jK][jK]
        result[iK][jK] = 0.0
        result[jK][iK] = 0.0
        return result
    }

    private fun getUpdatedV(c: Double, s: Double): Array<Array<Double>> {
        val v = getUnitMatrix(matrix.size)
        v[iK][iK] = c
        v[jK][jK] = c
        v[iK][jK] = -s
        v[jK][iK] = s
        return v
    }

    private fun calculateEigenValues(aK: Array<Array<Double>>) {
        var result: Array<Double> = arrayOf()
        for (q in aK.indices) {
            var sum = aK[q][q]
            for (i in aK.indices) {
                for (j in aK[i].indices) {
                    if (i != j) {
                        sum += aK[i][j] / (aK[i][i] - aK[j][j])
                    }
                }
            }
            result += sum
        }
        result.sortDescending()
        eigenValues = result
    }

    init {
        eigenVectors = getUnitMatrix(matrix.size)
        var aK = copyOf(matrix)
        var aij = getAIJ(aK)
        var k = 0
        while (abs(aij) >= eps && k < 50) {
            val d = sqrt((aK[iK][iK] - aK[jK][jK]).pow(2) + 4 * (aK[iK][jK]).pow(2))
            c = calculateC(aK, d)
            s = calculateS(aK, d)
            aK = updatedAK(aK, c, s)
            aij = getAIJ(aK)
            eigenVectors = multiplyMatrix(eigenVectors, getUpdatedV(c, s))!!
            ++k
        }
        calculateEigenValues(aK)
        if (k == 50) {
            println("\n${RED}The method does not converge, the number of iterations has reached 50!")
        }
    }
}