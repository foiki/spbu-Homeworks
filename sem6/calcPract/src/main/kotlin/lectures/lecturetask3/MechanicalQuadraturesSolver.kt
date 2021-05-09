package lectures.lecturetask3

import utils.functions.Function
import utils.functions.SimpleFunction
import utils.matrix.*

class MechanicalQuadraturesSolver(private val a: Double, private val b: Double,
                                  private val n: Int, private val alpha: Double,
                                  private val functionK: Function, private val functionZ: SimpleFunction) {

    private val h = (b - a) / n
    private var u: Array<Double> = arrayOf()
    private val solution: Array<Double>

    init {
        val matrixC = initMatrixCAndU()

        val leftPart = calculateLeftPart(matrixC)
        val rightPart = calculateRightPart(matrixC, u)
        solution = gaussMethodSolutionByMainElement(leftPart, rightPart)
    }

    private fun initMatrixCAndU(): Array<Array<Double>> {
        val matrix: Array<Array<Double>> = Array(n) { Array(n) {0.0} }
        for (j in 0 until n) {
            val xJ = a + (j + 1) * h
            val solver = AverageRectangleCompositeMethod(n, a, b, xJ, functionK, functionZ)
            val coefficients = solver.getCoefficients()
            var sum = 0.0
            for (k in 0 until n) {
                val aK = coefficients[k]
                val sK = a + (k + 1) * h
                matrix[j][k] = aK / functionZ.getValue(sK)
                sum += aK
            }
            u += sum
        }
        return matrix
    }

    private fun calculateLeftPart(matrixC: Array<Array<Double>>): Array<Array<Double>> {
        val cTranspose = getTransposedMatrix(matrixC)
        val cMultiply = multiplyMatrix(cTranspose, matrixC)
        val e = getUnitMatrix(n)
        return matrixSum(cMultiply!!, multiplyMatrixByConst(alpha, e))!!
    }

    private fun calculateRightPart(matrixC: Array<Array<Double>>, u: Array<Double>): Array<Double> {
        val cTranspose = getTransposedMatrix(matrixC)
        return multiplyMatrixByVector(cTranspose, u)!!
    }

    fun getSolution(): Array<Double> {
        return solution
    }
}