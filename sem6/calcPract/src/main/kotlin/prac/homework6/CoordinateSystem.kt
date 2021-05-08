package prac.homework6

fun getCoordinateSystemValue(x: Double, i: Int, polynomials: Array<Double>): Double {
    return (1 - x * x) * polynomials[i]
}

fun getCoordinateSystemFirstDerivativeValue(x: Double, i: Int, polynomials: Array<Double>): Double {
    val polynomialValue = polynomials[i]
    if (i == 0) {
        return - 2.0 * x * polynomialValue
    }
    val k = 1.0
    val prevPolynomial = ((i + 2 * k + 1) / 2.0) * polynomials[i - 1]
    return (1 - x * x) * prevPolynomial - 2 * x * polynomialValue
}

fun getCoordinateSystemSecondDerivativeValue(x: Double, i: Int, polynomials: Array<Double>): Double {
    val k = 1.0
    val polynomialValue: Double = (i + 2 * k + 1) / 2 * polynomials[i]
    if (i == 0) {
        return 2 * polynomialValue
    }
    val prevPolynomial: Double = (i + 2 * k + 1) / 2 * polynomials[i - 1]
    return (1 - x * x) * prevPolynomial - 2 * polynomialValue
}