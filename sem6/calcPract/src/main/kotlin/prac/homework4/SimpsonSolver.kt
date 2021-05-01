package prac.homework4

import kotlin.math.abs

class SimpsonSolver(val a: Double, val b: Double) {

    private var h: Double = 1.0
    private var points: Array<Pair<Double, Double>> = arrayOf()

    fun solve(x: Double, N: Int): Array<Double> {
        h = abs(b - a) / N
        initPoints(x, 2 * N)
        return calculateCoefficients(N)
    }

    private fun initPoints(x: Double, N: Int) {
        points = arrayOf()
        for (i in 0..N) {
            val xI = a + i * h
            val fI = H(x, xI)
            points += Pair(xI, fI)
        }
    }

    private fun calculateCoefficients(N: Int): Array<Double> {
        var coefficients: Array<Double> = arrayOf()
        val div = (b - a) / (6 * N)
        coefficients += div * points[0].second
        for (i in 1 until 2 * N) {
            val multi = if (i % 2 == 0) {
                2
            } else {
                4
            }
            coefficients += div * multi * points[i].second
        }
        coefficients += div * points[2 * N].second
        return coefficients
    }
}