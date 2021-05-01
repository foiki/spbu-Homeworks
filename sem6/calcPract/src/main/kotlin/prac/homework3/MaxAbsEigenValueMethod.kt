package prac.homework3

import utils.CYAN
import utils.GREEN
import utils.RED
import utils.matrix.*

abstract class MaxAbsEigenValueMethod(private val matrix: Array<Array<Double>>, private val eps: Double) {

    private var maxAbsEigenValue: Double = 0.0
    private var eigenVector: Array<Double> = arrayOf()
    private var isResultAvailable = false
    private var k = 0
    protected open var methodName: String = ""

    private fun getPosteriorEstimation(lambda: Double, y: Array<Double>): Double {
        val ay = multiplyMatrixByVector(matrix, y)!!
        val lambdaY = multiplyVectorByConst(lambda, y)
        return secondNorm(vectorDifference(ay, lambdaY)) / secondNorm(y)
    }

    abstract fun updateMaxAbsEigenValue(newYK: Array<Double>, eigenVector: Array<Double>): Double

    init {
        eigenVector = getUnitVector(matrix.size)
        while (getPosteriorEstimation(maxAbsEigenValue, eigenVector) > eps && k < 50) {
            val newYK = multiplyMatrixByVector(matrix, eigenVector)!!
            maxAbsEigenValue = updateMaxAbsEigenValue(newYK, eigenVector)
            eigenVector = newYK
            ++k
        }
        if (k < 50) {
            isResultAvailable = true
        }
        eigenVector = getNormVector(eigenVector)
    }

    fun printResults() {
        if (!isResultAvailable) {
            println("${RED}The method does not converge, the number of iterations has reached 50!")
            return
        }
        println("\n${GREEN}Max abs eigenvalue found by $methodName method: $CYAN$maxAbsEigenValue")
        println("${GREEN}Number of iterations: $CYAN$k")
        println("${GREEN}The eigenvector corresponding to the resulting eigenvalue:")
        printVector(eigenVector)
    }
}