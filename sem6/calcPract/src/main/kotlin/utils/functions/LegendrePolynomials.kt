package utils.functions

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