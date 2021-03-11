package homework2

import utils.CYAN
import utils.GREEN
import utils.MAGENTA
import utils.RED
import utils.matrix.*
import kotlin.math.pow
import kotlin.math.sqrt

abstract class IterationMethod(
    private val matrix: Array<Array<Double>>, private val vector: Array<Double>,
    private val solution: Array<Double>, private val eigenValues: Array<Double>? = null) {

    open var methodName = ""
    open var makeLyusternikCorrection = true
    open var makeEstimations = true
    private val x0 = getZeroVector(matrix.size)
    protected var resultX: Array<Double> = x0.copyOf()
    protected var prevResultX: Array<Double> = resultX.copyOf()
    protected var wasIterationMade: Boolean = false
    protected var numberOfIterations = -1
    protected val q = 2 / (1 + sqrt(1 - getP(eigenValues!!).pow(2)))

    private fun printPrioriEstimation(k: Int) {
        val hNorm = holderMatrixNorm(matrix)
        val hk = hNorm.pow(k)
        val estimation = hk * holderNorm(x0) + hk / (1 - hNorm) * holderNorm(vector)
        println("${MAGENTA}Priori estimation = ${CYAN}${estimation}")
    }

    private fun printPosterioriEstimation(k: Int) {
        val hNorm = holderMatrixNorm(matrix)
        val estimation = hNorm / (1 - hNorm) * holderNorm(vectorDifference(resultX, prevResultX))
        println("${MAGENTA}Posterior estimation = ${CYAN}${estimation}")
    }

    private fun lyusternikCorrection() {
        if (eigenValues == null) {
            println("${RED}Put the eigen values to iterator constructor!")
            return
        }
        val ph = getP(eigenValues)
        if (ph >= 1) {
            println("${RED}Lyusternik method can't be used! p(H) = $CYAN${ph}")
            return
        }
        println("${MAGENTA}p(h) = $CYAN$ph")
        val const: Double = 1.0 / (1 - ph)
        val x1 = multiplyVectorByConst(const, vectorDifference(resultX, prevResultX))
        val correction = vectorSum(prevResultX, x1)
        println("${MAGENTA}Lyusternik method corrected solution:")
        printVector(correction)
        println("${MAGENTA}Actual error ||x - xk|| = ${CYAN}${holderNorm(vectorDifference(solution, correction))}")
    }

    abstract fun makeIterations()

    fun printResults() {
        if (!wasIterationMade) {
            println("%${RED}Iteration has not been done yet!")
            return
        }
        println("\n${GREEN}Solution found using $methodName method:")
        printVector(resultX)
        println("\n${MAGENTA}Actual error ||x - xk|| = $CYAN${holderNorm(vectorDifference(solution, resultX))}")
        println("${MAGENTA}Number of iterations: $CYAN$numberOfIterations")
        if (makeEstimations) {
            printPrioriEstimation(numberOfIterations)
            printPosterioriEstimation(numberOfIterations)
        }
        if (makeLyusternikCorrection) {
            if (eigenValues == null) {
                println("%${RED}Pass the eigenvalues of the matrix for Lyusternik correction!")
            } else {
                lyusternikCorrection()
            }
        }
    }
}