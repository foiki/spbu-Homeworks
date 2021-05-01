package prac.homework5

import utils.CYAN
import utils.MAGENTA
import utils.TablePrinter
import utils.functions.Function
import java.util.*
import kotlin.math.abs
import kotlin.math.max

private val functionsArray = arrayOf(LinearFunction(), CubeFunction())
private val T = 0.1
private val N = 5
private val M = 5

private val listN = listOf(10, 20, 40)
private val listM = listOf(10, 20, 40)

fun taskEquation(x: Double, t: Double, function: Function): Double {
    return function.getDerivativeTValue(x, t) - function.getSecondDerivativeXValue(x, t) -
            (x * x + 1) * function.getDerivativeXValue(x, t)
}

fun printStartInfo() {
    println("${MAGENTA}Homework 5 (5.1). Variant 5")
    println("${MAGENTA}Equation: ${CYAN}du/dt = d^2u/dx^2 + (x^2 + 1)du/dx + f(x, t)")
    println("u(x, 0) = fi(x), 0 <= x <= 1")
    println("du/dx|x=0 = a(t), du/dx|x=1 = b(t), 0 <= t <= 0.1\n")
}

fun printAvailableFunctions() {
    println("${MAGENTA}Available functions:${CYAN}")
    for (i in functionsArray.indices) {
        println("$i: u(x, t) = ${functionsArray[i]}")
    }
}

fun chooseFunction(): Function {
    val scanner = Scanner(System.`in`)
    var userAnswer = -1
    while (userAnswer < 0 || userAnswer >= functionsArray.size) {
        println("Choose the function: ")
        userAnswer = scanner.nextInt()
        if (userAnswer < 0 || userAnswer >= functionsArray.size) {
            println("Wrong index! Choose from 0 to ${functionsArray.size - 1}")
        }
    }
    return functionsArray[userAnswer]
}

fun getHeader(): Array<String> {
    var result: Array<String> = arrayOf()
    for (i in 0..N) {
        result += (i * 1.0 / N).toString()
    }
    return result
}

fun getFirstColumn(): Array<String> {
    var result: Array<String> = arrayOf()
    result += "t/x"
    for (i in 0..N) {
        result += (i * T / M).toString()
    }
    return result
}

fun getAccurateTable(n: Int, m: Int, function: Function): Array<Array<Double>> {
    var result: Array<Array<Double>> = arrayOf()
    for (i in 0..m) {
        var array: Array<Double> = arrayOf()
        for (j in 0..n) {
            array += function.getValue(j * 1.0 / n, i * T / m, )
        }
        result += array
    }
    return result
}

fun findNorm(accurate: Array<Array<Double>>, grid: Array<Array<Double>>): Double {
    var maxValue = -1.0
    for (i in 0..5) {
        for (j in 0..5) {
            maxValue = max(maxValue, abs(abs(grid[i][j]) - abs(accurate[i][j])))
        }
    }
    return maxValue
}

fun printExplicitView(function: Function) {
    val explicitView = GridMethod(N, M, T, function)
    explicitView.performTask()
    val tablePrinter = TablePrinter(explicitView.getMatrixU())
    tablePrinter.addLineHeader(getHeader())
    tablePrinter.addColumnHeader(getFirstColumn())
    println("\n${MAGENTA}Explicit result for u(x, t) =$CYAN $function")
    tablePrinter.print()
}

fun printImplicitView(function: Function) {
    val implicitView = WeightMethod(N, M, T, function)
    implicitView.performTask()
    val tablePrinter = TablePrinter(implicitView.getMatrixU())
    tablePrinter.addLineHeader(getHeader())
    tablePrinter.addColumnHeader(getFirstColumn())
    println("\n${MAGENTA}Implicit result for u(x, t) =$CYAN $function")
    tablePrinter.print()
}

fun printEstimationTable(function: Function, method: String) {
    println("\n${MAGENTA}Result table for $method method:")
    var result: Array<Array<Double>> = arrayOf()
    for (i in listN.indices) {
        var array: Array<Double> = arrayOf()
        val n = listN[i]
        val m = listM[i]

        val h = 1.0 / n
        val tau = T / m

        array += h
        array += tau

        val accurateTable = getAccurateTable(n, m, function)

        val solverN = if (method == "explicit") {
            GridMethod(n, m, T, function)
        } else {
            WeightMethod(n, m, T, function)
        }
        solverN.performTask()
        val firstMatrix = solverN.getMatrixU()
        array += findNorm(accurateTable, firstMatrix)

        val solverN2 = if (method == "explicit") {
            GridMethod(n / 2, m / 2, T, function)
        } else {
            WeightMethod(n / 2, m / 2, T, function)
        }
        solverN2.performTask()
        val secondMatrix = solverN2.getMatrixU()
        array += findNorm(firstMatrix, secondMatrix)
        result += array
    }
    val tablePrinter = TablePrinter(result)
    tablePrinter.addLineHeader(arrayOf("h", "tau", "||J_ex - u^(h, tau)||", "||u^(h, tau) - u^(2h, tau1)||"))
    tablePrinter.print()
}

fun performTask() {
    val function = chooseFunction()
    printExplicitView(function)
    printImplicitView(function)

    printEstimationTable(function, "explicit")
    printEstimationTable(function, "implicit")
}

fun main() {
    printStartInfo()
    printAvailableFunctions()
    performTask()
}