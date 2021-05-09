package utils.functions

import kotlin.math.pow

fun getLegendrePolynomials(N: Int, x: Double): Array<Double> {
    var result: Array<Double> = arrayOf()
    if (N >= 0) {
        result += 1.0
    }
    if (N >= 1) {
        result += x
    }
    for (n in 2 until N) {
        val prev = result[n - 1]
        val prevPrev = result[n - 2]
        val newValue = (2 * n + 1) / (n + 1) * x * prev -
                n / (n + 1) * prevPrev
        result += newValue
    }
    return result
}

fun getLegendrePolynomialsValue(x: Double, n: Int): Double {
    var value = 0.0

    for (k in 0..n) {
        var combination = factorial(n) / (factorial(k) * factorial(n - k))
        combination *= combination
        val xComb = (x - 1).pow((n - k).toDouble()) * (x + 1).pow(k.toDouble())
        value += combination * xComb
    }

    return value / 2.0.pow(n.toDouble())
}

private fun factorial(k: Int): Double {
    return if (k <= 1) {
        1.0
    } else k * factorial(k - 1)
}