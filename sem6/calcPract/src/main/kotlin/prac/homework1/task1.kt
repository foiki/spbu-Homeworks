package prac.homework1

import utils.matrix.*
import utils.*

private val matrix: Array<Array<Double>> = arrayOf(arrayOf(-401.46, 200.18), arrayOf(1201.08, -601.64))
private val b: Array<Double> = arrayOf(200.0, -600.0)
private val b1: Array<Double> = arrayOf(199.0, -601.0)

fun printFirstStartInfo() {
    println("${GREEN}Task №1 (10.4, 10.5). Variant 5")
}

fun getConditionNumber(matrix: Array<Array<Double>>, reversedMatrix: Array<Array<Double>>): Double {
    return holderMatrixNorm(matrix) * holderMatrixNorm(reversedMatrix)
}

fun printActualError(x: Array<Double>, x1: Array<Double>) {
    if (x.size != x1.size) {
        println(RED + "Error! Vectors have different dimensions!")
        return
    }
    val actualError = holderNorm(vectorDifference(x, x1)) / holderNorm(x)
    println("\n${GREEN}Actual error: ${CYAN}$actualError")
}

fun printErrorEstimation(x: Array<Double>, x1: Array<Double>, condNumber: Double) {
    val deltaB = vectorDifference(x, x1)
    val estimation = condNumber * (holderNorm(deltaB) / holderNorm(x))
    println("\n${GREEN}Error estimation: ${CYAN}$estimation")
}

fun task1() {
    printFirstStartInfo()
    println("\nThe initial system:")
    printSystem(matrix, b)
    val x = getSolutionOfSquareMatrix(matrix, b)
    println("\n${GREEN}Solution of initial system:")
    printVector(x)
    println("\n${GREEN}System with changed right side:")
    printSystem(matrix, b1)
    val x1 = getSolutionOfSquareMatrix(matrix, b1)
    println("\n${GREEN}Solution of system with changed right side:")
    printVector(x1)
    val inverseMatrix = getInverseMatrix(copyOf(matrix))
    println("\n${GREEN}Inverse matrix (A^-1):")
    printMatrix(inverseMatrix)
    println("\n${GREEN}Result of multiplying origin matrix on inverse matrix:")
    printMatrix(multiplyMatrix(matrix, inverseMatrix)!!)
    val conditionNumber = getConditionNumber(matrix, inverseMatrix)
    println("\n${GREEN}Condition number of matrix (holder norm): ${CYAN}${conditionNumber}")
    printActualError(x, x1)
    printErrorEstimation(x, x1, conditionNumber)
}