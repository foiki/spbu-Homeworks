package homework5

import homework5.taskParams.*

class ImplicitMethod(private val N: Int, private val hM: Double,
                     private val sigma: Double, private val function: Function,
                     private val u: Array<Array<Double>>, private val k: Int) {

    private val hN = 1.0 / N
    private val tK = hM * k
    private val functionA = TaskFunction1()
    private val functionB = TaskFunction2()
    private val functionC = TaskFunction3()

    private val alpha1 = TaskAlpha1Function()
    private val alpha2 = TaskAlpha2Function()
    private val beta1 = TaskBeta1Function()
    private val beta2 = TaskBeta2Function()

    private val resultTable: Array<Array<Double>> = Array(N + 1) { Array(8) {0.0} }

    init {
        initTable()
        straightSweep()
        reverseSweep()
    }

    private fun initTable() {
        for (i in 0..N ) {
            resultTable[i][0] = i * hN
        }
    }

    private fun straightSweep() {
        for (i in 0..N) {
            resultTable[i][1] = A(i)
            resultTable[i][2] = B(i)
            resultTable[i][3] = C(i)
            resultTable[i][4] = G(i)
            resultTable[i][5] = s(i)
            resultTable[i][6] = t(i)
        }
    }

    private fun reverseSweep() {
        resultTable[N][7] = resultTable[N][6]
        for (i in N - 1 downTo 0) {
            resultTable[i][7] = resultTable[i][5] * resultTable[i + 1][7] + resultTable[i][6]
        }
    }

    fun A(i: Int): Double {
        if (i == 0) {
            return 0.0
        }
        if (i == N) {
            return beta1.getValue(tK) * hN + beta2.getValue(tK)
        }
        return sigma * functionA.getValue(i * hN, tK) / (hN * hN) -
                functionB.getValue(i * hN, tK) / (2 * hN)
    }

    private fun B(i: Int): Double {
        if (i == 0) {
            return - alpha1.getValue(tK) * hN - alpha2.getValue(tK)
        }
        if (i == N) {
            return - beta2.getValue(tK)
        }
        return 2 * sigma * functionA.getValue(i * hN, tK) / (hN * hN) + 1 / hM
    }

    private fun C(i: Int): Double {
        if (i == 0) {
            return - alpha2.getValue(tK)
        }
        if (i == N) {
            return 0.0
        }
        return sigma * functionA.getValue(i * hN, tK) / (hN * hN) +
                functionB.getValue(i * hN, tK) / (2 * hN)
    }

    fun G(i: Int): Double {
        if (i == 0) {
            return function.getDerivativeXValue(0.0, tK) * hN
        }
        if (i == N) {
            return function.getDerivativeXValue(1.0, tK) * hN
        }

        val x = i * hN

        val prevUIPlusOne = u[k - 1][i + 1]
        val prevUI = u[k - 1][i]
        val prevUIMinusOne = u[k - 1][i - 1]
        val taskFValue = taskEquation(x, tK, function)

        val lH = functionA.getValue(x, tK) * (prevUIPlusOne - 2 * prevUI + prevUIMinusOne) / (hN * hN) +
                functionB.getValue(x, tK) * (prevUIPlusOne - prevUIMinusOne) / (2 * hN) +
                functionC.getValue(x, tK) * prevUI

        return - (1 / hM) * prevUI - (1 - sigma) * lH * prevUI - taskFValue
    }

    fun s(i: Int): Double {
        if (i == 0) {
            return resultTable[i][3] / resultTable[i][2]
        }
        return resultTable[i][3] / (resultTable[i][2] - resultTable[i][1] * resultTable[i - 1][5])
    }

    fun t(i: Int): Double {
        if (i == 0) {
            return - resultTable[i][4] / resultTable[i][2]
        }
        return (resultTable[i][1] * resultTable[i - 1][6] - resultTable[i][4]) /
                (resultTable[i][2] - resultTable[i][1] * resultTable[i - 1][5])
    }

    fun getSolution(): Array<Double> {
        var result: Array<Double> = arrayOf()
        for (i in resultTable.indices) {
            result += resultTable[i][7]
        }
        return result
    }
}