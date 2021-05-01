package prac.homework1

import utils.matrix.*
import utils.*

private val matrix: Array<Array<Double>> = arrayOf(
    arrayOf(8.673134, 1.041039, -2.677712),
    arrayOf(1.041039, 6.586211, 0.623016),
    arrayOf(-2.677712, 0.623016, 5.225935))
private val b: Array<Double> = arrayOf(-1.289879, 4.020225, 5.269671)

fun printSecondStartInfo() {
    println("${GREEN}Task №2 (10.6). Variant 5")
}

fun task2() {
    printSecondStartInfo()
    println("\nThe initial system:")
    printSystem(matrix, b)
    val solution = gaussMethodSolutionByMainElement(copyOf(matrix), b.copyOf())
    println("\n${GREEN}Solution found using Gauss method with choosing main element (column):")
    printVector(solution)
    println("\n${GREEN}Inverse matrix: ")
    val inverseMatrix = getInverseMatrix(copyOf(matrix))
    printMatrix(inverseMatrix)
    println("\n${GREEN}Result of multiplying origin matrix on inverse matrix:")
    printMatrix(multiplyMatrix(matrix, inverseMatrix)!!)
    val det = luDecomposition(matrix)
    println("\n${GREEN}LU method: det(A) = $CYAN$det")
}