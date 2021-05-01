package lectures.lecturetask2

import utils.TablePrinter
import utils.matrix.*

class MechanicalQuadraturesSolver(private val a: Double, private val b: Double,
                                  private val x: Double, private val n: Int,
                                  private val alpha: Double) {

    private val h = (b - a) / n
    private val function = TaskFunctionK()
    private val solver = AverageRectangleCompositeMethod(n, a, b, x, function)
    private var u: Array<Double> = arrayOf()
    private val solution: Array<Double>

    init {
        val coefficients = solver.getCoefficients()
        val matrixC = initMatrixCAndU(coefficients)

        val leftPart = calculateLeftPart(matrixC)
        val rightPart = calculateRightPart(matrixC, u)
        solution = gaussMethodSolutionByMainElement(leftPart, rightPart)
    }

    private fun initMatrixCAndU(coefficients: Array<Double>): Array<Array<Double>> {
        val matrix: Array<Array<Double>> = Array(n) { Array(n) {0.0} }
        for (j in 0 until n) {
            val xJ = x + j * h
            var sum = 0.0
            for (k in 0 until n) {
                var dJK: Double = if (k == j) 1.0 else 0.0
                val aK = coefficients[k]
                val xK = x + k * h
                val hJK = function.getValue(xJ, xK)
                dJK -= aK * hJK
                matrix[j][k] = dJK
                sum += dJK
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