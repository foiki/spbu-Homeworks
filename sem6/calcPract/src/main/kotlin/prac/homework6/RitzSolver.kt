package prac.homework6

import org.apache.commons.math3.analysis.UnivariateFunction
import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator
import prac.homework6.taskParams.*
import utils.functions.getJacobiPolynomials
import utils.matrix.gaussMethodSolutionByMainElement
import utils.matrix.getInverseMatrix
import utils.matrix.infinityPMatrixNorm

class RitzSolver {

    private val maxEval = 500000
    private val pFunction = PFunction()
    private val rFunction = RFunction()
    private val fFunction = FFunction()
    private val qlFunction = QlFunction(leftType, alpha1, alpha2)
    private val qrFunction = QrFunction(rightType, beta1, beta2)

    private lateinit var matrixA: Array<Array<Double>>
    private lateinit var vectorFi: Array<Double>
    private lateinit var polynomials: Array<Double>

    fun getValue(x: Double, n: Int): Double {
        polynomials = getJacobiPolynomials(n, 1, x)

        matrixA = getMatrixA(n)
        vectorFi = getVectorFi(n)
        val vectorC = gaussMethodSolutionByMainElement(matrixA, vectorFi)
        return getSolution(vectorC, x, n)
    }

    private fun getMatrixA(n: Int): Array<Array<Double>> {
        val matrixA: Array<Array<Double>> = Array(n) { Array(n) {0.0} }
        for (i in 0 until n) {
            for (j in 0 until n) {
                val function = UnivariateFunction { value: Double ->
                    val y = getCoordinateSystemValue(value, j, polynomials)
                    val z = getCoordinateSystemValue(value, i, polynomials)
                    val yDerivative = getCoordinateSystemFirstDerivativeValue(value, j, polynomials)
                    val zDerivative = getCoordinateSystemFirstDerivativeValue(value, i, polynomials)
                    pFunction.getValue(value) * yDerivative * zDerivative + rFunction.getValue(value) * y * z
                }
                val integralValue = TrapezoidIntegrator().integrate(maxEval, function, -1.0, 1.0)

                val y1 = getCoordinateSystemValue(1.0, j, polynomials)
                val z1 = getCoordinateSystemValue(1.0, i, polynomials)
                val qL = qlFunction.getValue(y1, z1)
                val qr = qrFunction.getValue(y1, z1)
                matrixA[i][j] = integralValue + qL + qr
            }
        }
        return matrixA
    }

    private fun getVectorFi(n: Int): Array<Double> {
        var result: Array<Double> = arrayOf()
        for (i in 0 until n) {
            val function = UnivariateFunction { v: Double ->
                val wI: Double = getCoordinateSystemValue(v, i, polynomials)
                val fValue: Double = fFunction.getValue(v)
                fValue * wI
            }
            val integralValue = TrapezoidIntegrator().integrate(maxEval, function, -1.0, 1.0)
            result += integralValue
        }
        return result
    }

    private fun getSolution(c: Array<Double>, x: Double, n: Int): Double {
        var result = 0.0
        for (i in 0 until n) {
            val cI = c[i]
            val wI = getCoordinateSystemValue(x, i, polynomials)
            result += cI * wI
        }
        return result
    }

    fun getCondA(): Double {
        val invA = getInverseMatrix(matrixA)
        val normA = infinityPMatrixNorm(matrixA)
        val normInvA = infinityPMatrixNorm(invA)
        return normA * normInvA
    }

    fun getA(): Array<Array<Double>> {
        return matrixA
    }
}