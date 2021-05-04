package lectures.lecturetask2

import utils.CYAN
import utils.MAGENTA
import utils.TablePrinter
import utils.matrix.secondNorm
import utils.matrix.vectorDifference
import kotlin.math.pow

private val functionK = TaskFunctionK()
private val functionZ = TestZFunction()
private const val a: Double = 0.0
private const val b: Double = 1.0
private const val nSteps = 10
private const val alphaSteps = 9

private fun printStartInfo() {
    println("${MAGENTA}Task 2. Variant 15")
    println("${MAGENTA}Equation: ${CYAN}integral(0, 1, K(x, s)z(s)ds) = u(x)")
    println("K(x, s) = $functionK, z(s) = $functionZ")
}

private fun getHeader(): Array<String> {
    var result: Array<String> = arrayOf()
    for (i in 1..alphaSteps) {
        result += "10^-$i"
    }
    return result
}

private fun getColumnHeader(): Array<String> {
    var result: Array<String> = arrayOf()
    result += "N \\ alpha"
    for (i in 1..nSteps) {
        result += (10 * i).toString()
    }
    return result
}

private fun findSolution() {
    var result: Array<Array<Double>> = arrayOf()
    for (n in 1..nSteps) {
        val N = 10 * n
        var array: Array<Double> = arrayOf()
        for (k in 1..alphaSteps) {
            val alpha = 10.0.pow(-k)
            val solver = MechanicalQuadraturesSolver(a, b, N, alpha, functionK, functionZ)
            val solution = solver.getSolution()
            val accurateSolution = accurateSolution(N)
            array += secondNorm(vectorDifference(solution, accurateSolution))
        }
        result += array
    }
    val tablePrinter = TablePrinter(result)
    tablePrinter.addLineHeader(getHeader())
    tablePrinter.addColumnHeader(getColumnHeader())
    tablePrinter.print()
}

private fun accurateSolution(n: Int): Array<Double> {
    var result: Array<Double> = arrayOf()
    val h = (b - a) / n
    for (i in 1..n) {
        result += functionZ.getValue(a + i * h)
    }
    return result
}

fun main() {
    printStartInfo()
    findSolution()
}