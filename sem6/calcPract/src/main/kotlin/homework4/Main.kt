package homework4

import utils.CYAN
import utils.MAGENTA
import kotlin.math.pow
import kotlin.math.sinh

const val c: Double = 0.5
const val a: Double = 0.0
const val b: Double = 1.0
val eps: Double = 10.0.pow(-2)

fun printStartInfo() {
    println("${MAGENTA}Homework 4 (4.2.2). Variant 4")
    println("Start parameters: ")
    println("${MAGENTA}a = ${CYAN}$a ${MAGENTA}b = ${CYAN}$b ${MAGENTA}eps = ${CYAN}$eps")
    println("${MAGENTA}Equation: ${CYAN}u(x) + 0.5 * integral($a, $b, sh(xy) * u(y)dy) = x + 0.5")
}

fun H(x: Double, y: Double): Double {
    return sinh(x * y)
}

fun f(x: Double): Double {
    return x + c
}

fun main() {
    printStartInfo()
    val mechanicalQuadraturesSolver = MechanicalQuadraturesSolver(a, b, c, eps)
    mechanicalQuadraturesSolver.printSolutionsTable()
}