package lectures.lecturetask3

import utils.functions.Function
import utils.functions.SimpleFunction

class AverageRectangleCompositeMethod(private val N: Int, private val a: Double,
                                      private val b: Double, private val x: Double,
                                      private val functionK: Function, private val functionZ: SimpleFunction) {

    private val h = (b - a) / N
    private var result: Array<Double> = arrayOf()

    init {
        initResultArray()
    }

    private fun initResultArray() {
        for (i in 1..N) {
            val prevT = a + (i - 1) * h
            val newT = a + i * h
            val t = (newT + prevT) / 2.0
            result += functionK.getValue(x, t) * functionZ.getValue(t) * h
        }
    }

    fun getCoefficients(): Array<Double> {
        return result
    }
}