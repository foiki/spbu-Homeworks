package lectures.lectureTask4

import utils.functions.getLegendrePolynomialsValue

class AverageRectangleCompositeMethod(private val N: Int, private val a: Double,
                                      b: Double, private val x: Double) {

    private val h = (b - a) / N
    private var result: Array<Double> = arrayOf()
    private val solutionFunction = SolutionFunction()

    private fun initResultArray(j: Int) {
        val alpha = a + h / 2
        for (i in 1..N) {
            val x = alpha + (i - 1) * h
            result += solutionFunction.getValue(x) * getLegendrePolynomialsValue(x, j)
        }
    }

    fun getCoefficients(j: Int): Array<Double> {
        initResultArray(j)
        return result
    }
}