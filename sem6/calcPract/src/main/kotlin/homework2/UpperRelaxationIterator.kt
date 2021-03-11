package homework2;

import utils.matrix.*

class UpperRelaxationIterator(
    private val a: Array<Array<Double>>, private val b: Array<Double>,
    private val solution: Array<Double>,
    eigenValues: Array<Double>? = null): IterationMethod(a, b, solution, eigenValues) {

    override var methodName = "Upper relaxation"
    override var makeLyusternikCorrection = false
    override var makeEstimations = false

    override fun makeIterations() {
        numberOfIterations = 0
        while (holderNorm(vectorDifference(resultX, solution)) >= eps) {
            val sum2 = getZeroVector(a.size)
            for (i in a.indices) {
                for (j in i until a[i].size) {
                    sum2[i] += a[i][j] * resultX[j]
                }
                prevResultX[i] = resultX[i]
            }
            for (i in a.indices) {
                var sum = 0.0
                for (j in 0 until i) {
                    sum += a[i][j] * resultX[j]
                }
                sum = q * (b[i] - (sum + sum2[i])) / a[i][i]
                resultX[i] = prevResultX[i] + sum
            }
            ++numberOfIterations
        }
        wasIterationMade = true
    }
}

