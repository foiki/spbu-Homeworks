package lectures.lecturetask2

import utils.functions.Function

class AverageRectangleCompositeMethod(private val N: Int, private val a: Double,
                                      private val b: Double, private val x: Double,
                                      private val function: Function) {

    private val h = (b - a) / N
    private var result: Array<Double> = arrayOf()

    init {
        initResultArray()
    }

    private fun initResultArray() {
        val t = a + h / 2
        for (i in 1..N) {
            result += function.getValue(x, t + (i - 1) * h)
        }
    }

    public fun getCoefficients(): Array<Double> {
        return result
    }
}