package prac.homework7

import utils.CYAN
import utils.MAGENTA
import utils.TablePrinter
import kotlin.math.ln

fun printStartInfo() {
    println("${MAGENTA}Homework 7* (1.5). Variant 5")
    println("${MAGENTA}Equation: ${CYAN}p(x) = 1 / (x + 3), q(x) = - x, r(x) = ln(2 + x), f(x) = 1 - x / 2")
}

private const val a: Double = -1.0
private const val b: Double = 1.0
private const val alpha1: Double = 0.0
private const val alpha2: Double = -1.0
private const val beta1: Double = 0.5
private const val beta2: Double = 1.0
private const val alpha: Double = 0.0
private const val beta: Double = 0.0

private val N: Int = 20
private val h: Double = (b - a) / N
private val resultTable: Array<Array<Double>> = Array(N + 2) { Array(8) {0.0} }

fun p(x: Double): Double {
    return 1.0 / (x + 3)
}

fun q(x: Double): Double {
    return - x
}

fun r(x: Double): Double {
    return ln(x + 2)
}

fun f(x: Double): Double {
    return 1 - x / 2.0
}

fun A(i: Int): Double {
    if (i == 0) {
        return 0.0
    }
    if (i == N + 1) {
        return beta1 / 2 - beta2 / h
    }
    return - p(resultTable[i][0]) / (h * h) - q(resultTable[i][0]) / (2 * h)
}

fun B(i: Int): Double {
    if (i == 0) {
        return alpha1 / 2 + alpha2 / h
    }
    if (i == N + 1) {
        return beta1 / 2 + beta2 / h
    }
    return - (2 * p(resultTable[i][0]) / (h * h) + r(resultTable[i][0]))
}

fun C(i: Int): Double {
    if (i == 0) {
        return alpha1 / 2 - alpha2 / h
    }
    if (i == N + 1) {
        return 0.0
    }
    return - p(resultTable[i][0]) / (h * h) + q(resultTable[i][0]) / (2 * h)
}

fun G(i: Int): Double {
    if (i == 0) {
        return alpha
    }
    if (i == N + 1) {
        return beta
    }
    return f(resultTable[i][0])
}

fun s(i: Int): Double {
    if (i == 0) {
        return resultTable[i][3] / resultTable[i][2]
    }
    return resultTable[i][3] / (resultTable[i][2] - resultTable[i][1] * resultTable[i - 1][5])
}

fun t(i: Int): Double {
    if (i == 0) {
        return - resultTable[i][4] / resultTable[i][2]
    }
    return (resultTable[i][1] * resultTable[i - 1][6] - resultTable[i][4]) /
            (resultTable[i][2] - resultTable[i][1] * resultTable[i - 1][5])
}

fun initTable() {
    for (i in 0..N + 1) {
        resultTable[i][0] = a - h / 2 + i * h
    }
}

fun straightSweep() {
    for (i in 0..N + 1) {
        resultTable[i][1] = A(i)
        resultTable[i][2] = B(i)
        resultTable[i][3] = C(i)
        resultTable[i][4] = G(i)
        resultTable[i][5] = s(i)
        resultTable[i][6] = t(i)
    }
}

fun reverseSweep() {
    resultTable[N + 1][7] = resultTable[N + 1][6]
    for (i in N downTo 0) {
        resultTable[i][7] = resultTable[i][5] * resultTable[i + 1][7] + resultTable[i][6]
    }
}

fun makeSweep() {
    initTable()
    straightSweep()
    reverseSweep()
}

fun main() {
    printStartInfo()
    makeSweep()
    val tablePrinter = TablePrinter(resultTable)
    tablePrinter.addLineHeader(arrayOf("xi", "Ai", "Bi", "Ci", "Gi", "si", "ti", "yi"))
    tablePrinter.print()

}