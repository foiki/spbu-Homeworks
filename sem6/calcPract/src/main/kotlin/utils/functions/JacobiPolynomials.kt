package utils.functions

fun getJacobiPolynomials(n: Int, k: Int, x: Double): Array<Double> {
    var result: Array<Double> = arrayOf()
    result += 1.0
    result += (k + 1) * x

    for (i in 2 until n) {
        val prev = result[i - 1]
        val prevPrev = result[i - 2]
        val new = ((n + k + 2) * (2 * n + 2 * k + 3) * x * prev
                - (n + k + 2) * (n + k + 1) * prevPrev) /
                ((n + 2 * k + 2) * (n + 2))
        result += new
    }
    return result
}