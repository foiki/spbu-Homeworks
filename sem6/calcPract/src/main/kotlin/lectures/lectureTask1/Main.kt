package lectures.lectureTask1

import org.apache.commons.math3.linear.Array2DRowRealMatrix
import org.apache.commons.math3.linear.EigenDecomposition
import utils.matrix.*
import kotlin.math.*

fun a(n: Int, k: Int): Double {
    return (n.toDouble() + 1 - k).pow(-4)
}

fun b(k: Int): Double {
    return k / 2.0
}

fun getAN(n: Int): Array<Array<Double>> {
    var result: Array<Array<Double>> = arrayOf()
    for (i in 1..n) {
        var array: Array<Double> = arrayOf()
        val a = a(n, i)
        for (k in 1..n) {
            array += a.pow(b(k))
        }
        result += array
    }
    return result
}

fun sqrtEigen(matrixA: Array<Array<Double>>): Array<Array<Double>> {
    val matrixDecomposition = EigenDecomposition(Array2DRowRealMatrix(getDoubleArray(matrixA)))
    val diagonalSqrtEigenValues = getDiagonalMatrixFromVector(getSqrtVector(getEigenValues(matrixDecomposition)))
    val eigenVectors = getEigenVectorsMatrix(matrixDecomposition)
    return multiplyMatrix(multiplyMatrix(eigenVectors, diagonalSqrtEigenValues)!!, getInverseMatrix(eigenVectors))!!
}

fun newtonMethod(matrixA: Array<Array<Double>>): Array<Array<Double>> {
    var matrixB = copyOf(matrixA)
    var prevDerivation: Double? = null
    var derivation: Double? = null
    var prevPrevB: Array<Array<Double>>? = null
    var prevB: Array<Array<Double>>? = null
    while (prevDerivation == null || derivation == null || derivation < prevDerivation) {
        if (derivation != null) {
            prevDerivation = derivation
        }
        if (luDecomposition(matrixB) == 0.0) {
            break
        }
        prevPrevB = prevB
        prevB = matrixB
        prevDerivation = derivation
        matrixB = multiplyMatrixByConst(0.5, matrixSum(prevB, multiplyMatrix(getInverseMatrix(prevB), matrixA)!!)!!)
        derivation = getAverageValue(abs(matrixDifference(matrixB, prevB)!!))
    }
    if (prevPrevB != null) {
        return prevPrevB
    }
    if (prevB != null) {
        return prevB
    }
    return matrixB
}

private const val intGapSize = 3
private const val doubleGapSize = 25

fun printHeaderResultsTable() {
    print("| ${String.format("%-${intGapSize}s", "n")}|")
    print("${String.format("%-${doubleGapSize}s", " cond(A)")} |")
    print("${String.format("%-${doubleGapSize}s", " cond(B)eigen")} |")
    print("${String.format("%-${doubleGapSize}s", " cond(B)newton")} |")
    print("${String.format("%-${doubleGapSize}s", " B(eigen)^2 - A")} |")
    print("${String.format("%-${doubleGapSize}s", " B(newton)^2 - A")} |\n")
}

fun printResultsInTable(n: Int, condA: Double, condBEigen: Double, condBNewton: Double, normEigen: Double, normNewton: Double) {
    print("| ${String.format("%-${intGapSize}s", n)}")
    print("| ${String.format("%-${doubleGapSize}s", condA)}")
    print("| ${String.format("%-${doubleGapSize}s", condBEigen)}")
    print("| ${String.format("%-${doubleGapSize}s", condBNewton)}")
    print("| ${String.format("%-${doubleGapSize}s", normEigen)}")
    print("| ${String.format("%-${doubleGapSize}s", normNewton)}|\n")
}

fun main() {
    printHeaderResultsTable()
    for (n in 2..10) {
        val matrixA = getAN(n)
        val condA = getConditionNumber(matrixA)

        val eigenMatrixB = sqrtEigen(matrixA)
        val condBEigen = getConditionNumber(eigenMatrixB)

        val newtonMatrixB = newtonMethod(matrixA)
        val condBNewton = getConditionNumber(newtonMatrixB)

        val normEigen = getAverageValue(abs(matrixDifference(multiplyMatrix(eigenMatrixB, eigenMatrixB)!!, matrixA)!!))
        val normNewton = getAverageValue(abs(matrixDifference(multiplyMatrix(newtonMatrixB, newtonMatrixB)!!, matrixA)!!))
        printResultsInTable(n, condA, condBEigen, condBNewton, normEigen, normNewton)
    }
}