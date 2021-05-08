package prac.homework6

import prac.homework6.taskParams.*
import utils.CYAN
import utils.MAGENTA
import utils.TablePrinter
import utils.matrix.printMatrix
import kotlin.math.abs

private val pFunction = PFunction()
private val rFunction = RFunction()
private val fFunction = FFunction()

const val alpha1 = 1.0
const val alpha2 = 0.0
const val beta1 = 1.0
const val beta2 = 0.0
const val leftType = 1
const val rightType = 1
private const val minN = 3
private const val maxN = 7
private const val x1 = - 0.5
private const val x2 = 0.0
private const val x3 = 0.5

private val ritzSolver = RitzSolver()
private val collocationSolver = CollocationSolver()

fun printStartInfo() {
    println("${MAGENTA}Homework 6 (2.8). Variant 1")
    println("${MAGENTA}Equation: ${CYAN}- (${pFunction}u')' + ${rFunction}u = $fFunction")
    println("u(-1) = u(1) = 0")
}

private fun getTableLine(n: Int): Array<Double> {
    val ritzX1 = ritzSolver.getValue(x1, n)
    val ritzX2 = ritzSolver.getValue(x2, n)
    val ritzX3 = ritzSolver.getValue(x3, n)

    val matrixA = ritzSolver.getA()
    println("Extended system (n = $n):")
    printMatrix(matrixA)
    println()

    val condA = ritzSolver.getCondA()

    val collX1 = collocationSolver.getValue(x1, n)
    val collX2 = collocationSolver.getValue(x2, n)
    val collX3 = collocationSolver.getValue(x3, n)

    return arrayOf(n.toDouble(), condA, ritzX1, ritzX2, ritzX3,
        abs(collX1 - ritzX1), abs(collX2 - ritzX2), abs(collX3 - ritzX3))
}

private fun performTask() {
    var resultTable: Array<Array<Double>> = arrayOf()
    for (i in minN..maxN) {
        resultTable += getTableLine(i)
    }
    val tablePrinter = TablePrinter(resultTable, 16)
    tablePrinter.addLineHeader(arrayOf("n", "cond(A)", "y^n(- 0.5)", "y^n(0)", "y^n(0.5)",
        "y*(- 0.5) - y^n(- 0.5)", "y*(0) - y^n(0)", "y*(0.5) - y^n(0.5)"))
    tablePrinter.print()
}

fun main() {
    printStartInfo()
    performTask()
}