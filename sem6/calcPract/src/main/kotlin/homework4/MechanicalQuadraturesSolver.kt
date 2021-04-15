package homework4

import utils.CYAN
import utils.MAGENTA
import utils.matrix.gaussMethodSolutionByMainElement
import kotlin.math.abs
import kotlin.math.max

class MechanicalQuadraturesSolver(private val a: Double, private val b: Double, private val c: Double, private val eps: Double) {

    private var n: Int = 2
    private var h: Double = abs(b - a) / n
    private val solver: SimpsonSolver = SimpsonSolver(a, b)
    private var solutionTable: Array<Array<Double>> = arrayOf()
    private var estimation = 0.0

    init {
        findSolutions()
    }

    private fun findSolutions() {
        var uNa = getSolution(a)
        var uNab = getSolution((a + b) / 2.0)
        var uNb = getSolution(b)
        solutionTable += arrayOf(n.toDouble(), uNa, uNab, uNb)
        var newUNa = uNa
        var newUNab = uNab
        var newUNb = uNb
        do {

            uNa = newUNa
            uNab = newUNab
            uNb = newUNb

            n *= 2
            h = abs(b - a) / n
            newUNa = getSolution(a)
            newUNab = getSolution((a + b) / 2.0)
            newUNb = getSolution(b)

            solutionTable += arrayOf(n.toDouble(), newUNa, newUNab, newUNb)

            estimation = updateAccuracy(arrayOf(uNa, uNab, uNb), arrayOf(newUNa, newUNab, newUNb))

        } while (estimation >= eps)
    }

    private fun updateAccuracy(prev: Array<Double>, new: Array<Double>): Double {
        var result = -1.0
        for (i in prev.indices) {
            val prevX = prev[i]
            val newX = new[i]
            result = max(result, abs(newX - prevX))
        }
        return result
    }

    private fun getSolution(x: Double): Double {
        val coefficients = solver.solve(x, n)
        val d = getD(coefficients, x)
        val g = getG()
        val systemSolution = gaussMethodSolutionByMainElement(d, g)
        return getSolution(coefficients, systemSolution, x)
    }

    private fun getSolution(coefficients: Array<Double>, systemSolution: Array<Double>, x: Double): Double {
        var result = 0.0
        for (i in 0 until n) {
            val aK = coefficients[i]
            val hK = H(x, x + i * h)
            val cK = systemSolution[i]
            result += aK * hK * cK
        }
        return result * (- c) + f(x)
    }

    private fun getG(): Array<Double> {
        var result: Array<Double> = arrayOf()
        for (i in 0 until n) {
            val xI = a + i * h
            result += f(xI)
        }
        return result
    }

    private fun getD(coefficients: Array<Double>, x: Double): Array<Array<Double>> {
        var d: Array<Array<Double>> = arrayOf()
        for (j in 0 until n) {
            val xJ = x + j * h
            var array: Array<Double> = arrayOf()
            for (k in 0 until n) {
                val delta: Int = if (j == k) { 1 } else { 0 }
                val aK = coefficients[k]
                val hJK = H(xJ, x + k * h)
                val dValue = delta - aK * hJK
                array += dValue
            }
            d += array
        }
        return d
    }

    private val firstGapSize = 40
    private val intGapSize = 3
    private val doubleGapSize = 30

    private fun printTableLine(line: Array<Double>) {
        print("${MAGENTA}| ${CYAN}${String.format("%-${firstGapSize}s", "u^(${line[0]})")}${MAGENTA}|")
        for (i in 1 until line.size) {
            print("${String.format("%-${doubleGapSize}s", " ${CYAN + line[i]}")} ${MAGENTA}|")
        }
        println()
    }

    private fun printFinalAndPreviousSolutionEstimation() {
        if (solutionTable.size < 2) {
            return
        }
        val last = solutionTable[solutionTable.size - 1]
        val prev = solutionTable[solutionTable.size - 2]
        print("| $CYAN${String.format("%-${firstGapSize}s", "u^(${last[0]}) - u^(${prev[0]})")}$MAGENTA|")
        for (i in 1 until last.size) {
            print("${String.format("%-${doubleGapSize}s", "$CYAN ${abs(last[i] - prev[i])}")} $MAGENTA|")
        }
        println()
    }

    private fun printFinalSolution() {
        val last = solutionTable[solutionTable.size - 1]
        print("${MAGENTA}| $CYAN${String.format("%-${firstGapSize}s", "Solution found using 1-st method")}$MAGENTA|")
        for (i in 1 until last.size) {
            print("${String.format("%-${doubleGapSize}s", "$CYAN ${last[i]}")} $MAGENTA|")
        }
        println()
    }

    private fun printFinalEstimation() {
        print("${MAGENTA}| ${CYAN}${String.format("%-${firstGapSize}s", "Estimation found using 1-st method")}$MAGENTA|")
        print("${String.format("%-${doubleGapSize}s", "$CYAN $estimation")} $MAGENTA|\n")
    }

    fun printSolutionsTable() {
        println("${MAGENTA}Solutions table:")
        solutionTable.forEach {
            printTableLine(it)
        }
        printFinalAndPreviousSolutionEstimation()
        printFinalSolution()
        printFinalEstimation()
    }
}
