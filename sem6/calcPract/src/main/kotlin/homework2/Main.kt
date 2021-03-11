package homework2

import org.apache.commons.math3.linear.Array2DRowRealMatrix
import org.apache.commons.math3.linear.EigenDecomposition
import utils.matrix.*
import utils.*

private val matrix: Array<Array<Double>> = arrayOf(
    arrayOf(8.673134, 1.041039, -2.677712),
    arrayOf(1.041039, 6.586211, 0.623016),
    arrayOf(-2.677712, 0.623016, 5.225935))
private val b: Array<Double> = arrayOf(-1.289879, 4.020225, 5.269671)

fun printStartInfo() {
    println("${GREEN}Homework 2 (12.7). Variant 5")
}

fun makeIterationsMethods(hd: Array<Array<Double>>, gd: Array<Double>, gaussSolution: Array<Double>) {
    println("\n${GREEN}||Hd||infinity = ${CYAN}${infinityPMatrixNorm(hd)}")
    println("\n${GREEN}Priori estimation k = ${CYAN}${calculatePrioriEstimation(hd, gd, getZeroVector(hd.size))}")
    val hdDec = EigenDecomposition(Array2DRowRealMatrix(getDoubleArray(hd)))
    val hdEigenValues = doubleArrayToArray(hdDec.realEigenvalues)
    println("\n${GREEN}Eigen values of matrix H:")
    printVector(hdEigenValues)

    val simpleIteration = SimpleIterator(hd, gd, gaussSolution, hdEigenValues)
    simpleIteration.makeIterations()
    simpleIteration.printResults()

    val seidelIteration = SeidelIterator(hd, gd, gaussSolution, hdEigenValues)
    seidelIteration.makeIterations()
    seidelIteration.printResults()

    val upperRelaxationIterator = UpperRelaxationIterator(matrix, b, gaussSolution, hdEigenValues)
    upperRelaxationIterator.makeIterations()
    upperRelaxationIterator.printResults()

    val aDec = EigenDecomposition(Array2DRowRealMatrix(getDoubleArray(matrix)))
    val aEigenValues = doubleArrayToArray(aDec.realEigenvalues)

    //val chebyshevParametersSetIterator = ChebyshevParametersSetIterator(matrix, b, gaussSolution, aEigenValues)
    //chebyshevParametersSetIterator.makeIterations()
}

fun main() {
    printStartInfo()
    println("\nThe initial system:")
    printSystem(matrix, b)
    val gaussSolution = gaussMethodSolutionByMainElement(copyOf(matrix), b.copyOf())
    println("\n${GREEN}Solution found using Gauss method with choosing main element (column):")
    printVector(gaussSolution)
    val inverseD = getInverseMatrix(getD(matrix))
    println("\n${GREEN}D^-1 matrix:")
    printMatrix(inverseD)
    val hd = getHD(matrix)
    println("\n${GREEN}Hd matrix:")
    printMatrix(hd!!)
    val gd = getGD(inverseD, b)
    println("\n${GREEN}Gd vector:")
    printVector(gd!!)
    makeIterationsMethods(hd, gd, gaussSolution)
}