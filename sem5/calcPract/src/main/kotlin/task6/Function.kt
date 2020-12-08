package task6

import kotlin.math.exp
import kotlin.math.pow

fun getConstFromSolution(): Double {
    return (1 - y0) / (y0 * exp(x0))
}

fun y(x: Double): Double {
    return 1 / (C * exp(x) + 1)
}

fun tailorDecompositionValue(x: Double): Double {
    return y(x0) + y1(x0) * (x - x0) +
            1 / 2 * y2(x0) * (x - x0).pow(2) +
            1 / 6 * y3(x0) * (x - x0).pow(3) +
            1 / 24 * y4(x0) * (x - x0).pow(4) +
            1 / 120 * y5(x0) * (x - x0).pow(5)
}

fun term(x: Double, n: Int): Double {
    return (C.pow(n) * exp((n) * x)) / (C * exp(x) + 1).pow(n + 1)
}

fun y1(x: Double): Double {
    return - term(x, 1)
}

fun y2(x: Double): Double {
    return 2 * term(x, 2) - term(x, 1)
}

fun y3(x: Double): Double {
    return - 6 * term(x, 3) + 6 * term(x, 2) - term(x, 1)
}

fun y4(x: Double): Double {
    return 24 * term(x, 4) - 36 * term(x, 3) + 14 * term(x, 2) - term(x, 1)
}

fun y5(x: Double): Double {
    return -120 * term(x, 5) + 240 * term(x, 4) - 150 * term(x, 3) +
            30 * term(x, 2) - term(x, 1)
}