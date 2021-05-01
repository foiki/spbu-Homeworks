package prac.homework3

import org.apache.commons.math3.linear.Array2DRowRealMatrix
import org.apache.commons.math3.linear.EigenDecomposition
import utils.GREEN
import utils.matrix.*

private val matrix: Array<Array<Double>> = arrayOf(
    arrayOf(-1.14896, -0.53716, 0.78959),
    arrayOf(-0.53716, 0.88917, 0.19536),
    arrayOf(0.78959, 0.19536, -1.28186)
)
private const val eps = 0.000001

private val matrix1: Array<Array<Double>> = arrayOf(
    arrayOf(2.2, 1.0, 0.5, 2.0),
    arrayOf(1.0, 1.3, 2.0, 1.0),
    arrayOf(0.5, 2.0, 0.5, 1.6),
    arrayOf(2.0, 1.0, 1.6, 2.0)
)

fun printStartInfo() {
    println("${GREEN}Homework 3 (13.8). Variant 5")
}

fun printJacobiMethod() {
    val jacobiMethod = JacobiMethod(matrix, eps)
    println("\n${GREEN}Eigenvalues of matrix found by Jacobi method:")
    printVector(jacobiMethod.eigenValues)
    println("\n${GREEN}Eigenvectors of matrix found by Jacobi method:")
    printMatrix(jacobiMethod.eigenVectors)
}

fun printPowerMethod() {
    val powerMethod = PowerMethod(matrix, eps)
    powerMethod.printResults()
}

fun printScalarProductMethod() {
    val scalarProductMethod = ScalarProductMethod(matrix, eps)
    scalarProductMethod.printResults()
}

fun main() {
    printStartInfo()
    println("\nThe initial matrix:")
    printMatrix(matrix)
    val matrixDecomposition = EigenDecomposition(Array2DRowRealMatrix(getDoubleArray(matrix)))
    val eigenValues = doubleArrayToArray(matrixDecomposition.realEigenvalues)
    println("\n${GREEN}Eigenvalues of matrix:")
    printVector(eigenValues)
    printJacobiMethod()

    println("\n${GREEN}The exact value of the eigenvector:")
    printVector(doubleArrayToArray(matrixDecomposition.getEigenvector(matrix.size - 1).toArray()))

    printPowerMethod()
    printScalarProductMethod()
}