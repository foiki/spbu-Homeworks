package homework5

import homework5.taskParams.*

open class GridMethod(private val N: Int, private val M: Int, T: Double, private val function: Function) {

    private val hN = 1.0 / N
    protected val hM = T / M
    protected var u: Array<Array<Double>> = arrayOf()

    private val functionA = TaskFunction1()
    private val functionB = TaskFunction2()
    private val functionC = TaskFunction3()

    private val alpha1 = TaskAlpha1Function()
    private val alpha2 = TaskAlpha2Function()
    private val beta1 = TaskBeta1Function()
    private val beta2 = TaskBeta2Function()

    open fun performTask() {
        initZeroU()
        initU()
    }

    /**
     * Initialize u^0i
     */
    protected fun initZeroU() {
        var array: Array<Double> = arrayOf()
        for (i in 0..N) {
            val x = i * hN
            array += function.getValue(x, 0.0)
        }
        u += array
    }

    /**
     * Initialize u^ki
     */
    private fun initU() {
        for (k in 1..M) {
            val tKPrev = (k - 1) * hM

            val uIK: Array<Double> = Array(N + 1) {0.0}
            for (i in 1 until N) {
                val x = i * hN

                val prevUIPlusOne = u[k - 1][i + 1]
                val prevUI = u[k - 1][i]
                val prevUIMinusOne = u[k - 1][i - 1]
                val taskFValue = taskEquation(x, tKPrev, function)

                val lH = functionA.getValue(x, tKPrev) * (prevUIPlusOne - 2 * prevUI + prevUIMinusOne) / (hN * hN) +
                        functionB.getValue(x, tKPrev) * (prevUIPlusOne - prevUIMinusOne) / (2 * hN) +
                        functionC.getValue(x, tKPrev) * prevUI

                val newU = prevUI + hM * (lH * prevUI + taskFValue)
                uIK[i] = newU
            }
            uIK[0] = initUK0(k, uIK[1], uIK[2])
            uIK[N] = initUKN(k, uIK[N - 1], uIK[N - 2])
            u += uIK
        }
    }

    private fun initUK0(k: Int, uk1: Double, uk2: Double): Double {
        val tK = k * hM

        val alpha1Value = alpha1.getValue(tK)
        val alpha2Value = alpha2.getValue(tK)

        val alpha = alpha1Value * function.getValue(0.0, tK) -
                alpha2Value * function.getDerivativeXValue(0.0, tK)

        return (2 * hN * alpha + 4 * alpha2Value * uk1 - alpha2Value * uk2) /
                (2 * hN * alpha1Value + 3 * alpha2Value)
    }

    private fun initUKN(k: Int, ukNMinus1: Double, ukNMinus2: Double): Double {
        val tK = k * hM

        val beta1Value = beta1.getValue(tK)
        val beta2Value = beta2.getValue(tK)

        val beta = beta1Value * function.getValue(1.0, tK) +
                beta2Value * function.getDerivativeXValue(1.0, tK)

        return (2 * hN * beta + 4 * beta2Value * ukNMinus1 - beta2Value * ukNMinus2) /
                (2 * hN * beta1Value + 3 * beta2Value)
    }

    fun getMatrixU(): Array<Array<Double>> {
        return u
    }
}