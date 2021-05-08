package prac.homework6

import prac.homework6.taskParams.FFunction
import prac.homework6.taskParams.PFunction
import prac.homework6.taskParams.RFunction
import utils.functions.getJacobiPolynomials
import utils.matrix.gaussMethodSolutionByMainElement
import java.lang.Math.PI
import kotlin.math.cos

class CollocationSolver {

    private val pFunction = PFunction()
    private val rFunction = RFunction()
    private val fFunction = FFunction()

    private lateinit var tValues: Array<Double>
    private lateinit var matrixA: Array<Array<Double>>
    private lateinit var rightPart: Array<Double>

    fun getValue(x: Double, n: Int): Double {
        tValues = getValuesT(n)
        matrixA = getMatrixA(n)
        rightPart = calculateRightPart(n)
        val coefficientsC = gaussMethodSolutionByMainElement(matrixA, rightPart)
        return getSolution(x, n, coefficientsC)
    }

    private fun getValuesT(n: Int): Array<Double> {
        var result: Array<Double> = arrayOf()
        for (k in 1..n) {
            val newValue = cos((2 * k - 1) * PI / (2 * n))
            result += newValue
        }
        return result
    }

    private fun getMatrixA(n: Int): Array<Array<Double>> {
        var result: Array<Array<Double>> = arrayOf()
        for (i in 0 until n) {
            var array: Array<Double> = arrayOf()
            for (j in 0 until n) {
                array += calculateLeftPartValue(n, j, tValues[i])
            }
            result += array
        }
        return result
    }

    private fun calculateLeftPartValue(n: Int, i: Int, tI: Double): Double {
        val jacobiPolynomials = getJacobiPolynomials(n, 1, tI)

        val pValue = pFunction.getValue(tI)
        val pDerValue = pFunction.getDerivativeValue(tI)

        val rValue = rFunction.getValue(tI)
        val rDerValue = rFunction.getDerivativeValue(tI)

        val wIValue = getCoordinateSystemValue(tI, i, jacobiPolynomials)
        val wIDerValue = getCoordinateSystemFirstDerivativeValue(tI, i, jacobiPolynomials)
        val wISecondDerValue = getCoordinateSystemSecondDerivativeValue(tI, i, jacobiPolynomials)

        return - (pDerValue * wIDerValue + pValue * wISecondDerValue) + rDerValue * wIValue + rValue * wIDerValue
    }

    private fun calculateRightPart(n: Int): Array<Double> {
        var result: Array<Double> = arrayOf()
        for (i in 0 until n) {
            result += fFunction.getValue(tValues[i])
        }
        return result
    }

    private fun getSolution(x: Double, n: Int, c: Array<Double>): Double {
        var result = 0.0
        val polynomials = getJacobiPolynomials(n, 1, x)
        for (i in 0 until n) {
            val wI = getCoordinateSystemValue(x, i, polynomials)
            result += c[i] * wI
        }
        return result
    }
}