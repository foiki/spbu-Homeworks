package lectures.lectureTask4

import utils.functions.SimpleFunction
import utils.matrix.*

class MomentMethodSolver(private val a: Double, private val b: Double,
                         private val n: Int, private val alpha: Double, private val functionZ: SimpleFunction) {

    private val h = (b - a) / n
    private val X = (a + b) / 2.0
    private var u: Array<Double> = arrayOf()
    private val solution: Array<Double>
    private val solutionFunction = SolutionFunction()

    init {
        val matrixB = getMatrixB()

        val leftPart = calculateLeftPart(matrixB)
        val rightPart = calculateRightPart(matrixB, u)

        solution = gaussMethodSolutionByMainElement(leftPart, rightPart)
    }

    private fun getMatrixB(): Array<Array<Double>> {
        val matrix: Array<Array<Double>> = Array(n) { Array(n) {0.0} }
        for (j in 0 until n) {
            val solver = AverageRectangleCompositeMethod(n, a, b, X)
            val coefficients = solver.getCoefficients(j)
            var sum = 0.0
            for (k in 0 until n) {
                var dJK: Double = if (j == k) 1.0 else 0.0
                val aK: Double = coefficients[k]
                val xK: Double = X + k * h
                val hJK: Double = solutionFunction.getValue(xK)
                dJK -= aK * hJK
                matrix[j][k] = dJK * functionZ.getValue(xK)
                sum += dJK
            }
            u += sum
        }
        return matrix
    }

    private fun calculateLeftPart(matrixB: Array<Array<Double>>): Array<Array<Double>> {
        val bTranspose = getTransposedMatrix(matrixB)
        val bMultiply = multiplyMatrix(bTranspose, matrixB)
        val e = getUnitMatrix(n)
        return matrixSum(bMultiply!!, multiplyMatrixByConst(alpha, e))!!
    }

    private fun calculateRightPart(matrixB: Array<Array<Double>>, u: Array<Double>): Array<Double> {
        val cTranspose = getTransposedMatrix(matrixB)
        return multiplyMatrixByVector(cTranspose, u)!!
    }

    fun getSolution(): Array<Double> {
        return solution
    }
}