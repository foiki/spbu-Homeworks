package prac.homework6

fun getCoordinateSystemValue(x: Double, i: Int, polynomials: Array<Double>): Double {
    if (i == 0) {
        return 1.0
    }

    if (i == 1) {
        return x
    }

    val n = i - 2
    return (1 - x * x) * polynomials[n]
}

fun getCoordinateSystemFirstDerivativeValue(x: Double, i: Int, polynomials: Array<Double>): Double {
    if (i == 0) {
        return 0.0
    }

    if (i == 1) {
        return 1.0
    }

    val n = i - 2
    val polynomNval: Double = polynomials[n]

    if (i == 2) {
        return -2 * x * polynomNval
    }

    val k = 1.0
    val polynomN_1val: Double = (n + 2 * k + 1) / 2 * polynomials[n - 1]
    return (1 - x * x) * polynomN_1val - 2 * x * polynomNval
}

fun getCoordinateSystemSecondDerivativeValue(x: Double, i: Int, polynomials: Array<Double>): Double {
    if (i == 0) {
        return 0.0
    }

    if (i == 1) {
        return 0.0
    }

    val k = 1.0
    val n = i - 2
    val polynomNval: Double = (n + 2 * k + 1) / 2 * polynomials[n]

    if (i == 2) {
        return 2 * polynomNval
    }

    val polynomN_1val: Double = (n + 2 * k + 1) / 2 * polynomials[n - 1]
    return (1 - x * x) * polynomN_1val - 2 * polynomNval
}