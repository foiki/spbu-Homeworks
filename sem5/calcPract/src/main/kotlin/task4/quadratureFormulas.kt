package task4

import kotlin.math.abs
import kotlin.math.pow

fun theoreticalInaccuracy(const: Double, d: Int): Double {
    return const * when (d) {
        0 -> function.maxOfFirstDerivative()
        1 -> function.maxOfSecondDerivative()
        2 -> function.maxOfThirdDerivative()
        else -> function.maxOfFourthDerivative()
    } * (B - A) * h.pow(d + 1)
}

fun leftRectangles() {
    var value = 0.0
    for (k in 1..m) {
        value += function.value(A + (k - 1) * h)
    }
    value *= h
    println("\nСоставная квадратурная формула левых прямоугольников: $value")
    println("Абсолютная фактическая погрешность: ${abs(J - value)}")
    println("Теоретическая погрешность: ${theoreticalInaccuracy(1.0/2, 0)}")
}

fun rightRectangles() {
    var value = 0.0
    val a = A + h
    for (k in 1..m) {
        value += function.value(a + (k - 1) * h)
    }
    value *= h
    println("\nСоставная квадратурная формула правых прямоугольников: $value")
    println("Абсолютная фактическая погрешность: ${abs(J - value)}")
    println("Теоретическая погрешность: ${theoreticalInaccuracy(1.0/2, 0)}")
}

fun averageRectangles() {
    var value = 0.0
    val a = A + h / 2
    for (k in 1..m) {
        value += function.value(a + (k - 1) * h)
    }
    value *= h
    println("\nСоставная квадратурная формула средних прямоугольников: $value")
    println("Абсолютная фактическая погрешность: ${abs(J - value)}")
    println("Теоретическая погрешность: ${theoreticalInaccuracy(1.0/24, 1)}")
}

fun trapezium() {
    var value = 0.0
    for (k in 1 until m) {
        value += function.value(A + k * h)
    }
    value *= 2
    value += function.value(A) + function.value(A + m * h)
    value *= (B - A) / (2 * m)
    println("\nСоставная квадратурная формула трапеций: $value")
    println("Абсолютная фактическая погрешность: ${abs(J - value)}")
    println("Теоретическая погрешность: ${theoreticalInaccuracy(1.0/12, 1)}")
}

fun simpson() {
    var value = 0.0
    val h1 = h / 2
    for (k in 1 until 2 * m) {
        if (k % 2 == 1) {
            value += 4 * function.value(A + k * h1)
        } else {
            value += 2 * function.value(A + k * h1)
        }
    }
    value += function.value(A) + function.value(A + 2 * m * h1)
    value *= (B - A) / (6 * m)
    println("\nСоставная квадратурная формула Симпсона: $value")
    println("Абсолютная фактическая погрешность: ${abs(J - value)}")
    println("Теоретическая погрешность: ${theoreticalInaccuracy(1.0/2880, 3)}")
}


