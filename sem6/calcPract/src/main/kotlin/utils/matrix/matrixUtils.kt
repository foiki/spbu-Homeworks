package utils.matrix

import utils.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sign

fun printSigned(value: Double) {
    var x = value
    if (x < 0) {
        x *= -1
        print("- ")
    } else {
        print("+ ")
    }
    print(x)
}

fun printSystem(matrix: Array<Array<Double>>, b: Array<Double>) {
    print(CYAN)
    for (i in matrix.indices) {
        if (matrix[i].isEmpty()) {
            break
        }
        print("${matrix[i][0]} * x0 ")
        for (j in 1 until matrix[i].size) {
            printSigned(matrix[i][j])
            print(" * x${j} ")
        }
        println("= ${b[i]}")
    }
}

fun initMatrix(n: Int, m: Int): Array<Array<Double>> {
    var matrix = arrayOf<Array<Double>>()
    for (i in 0 until n) {
        var array = arrayOf<Double>()
        for (j in 0 until m) {
            array += 0.0
        }
        matrix += array
    }
    return matrix
}

fun printMatrix(matrix: Array<Array<Double>>) {
    print(CYAN)
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            print("%8f ".format(matrix[i][j]))
        }
        println()
    }
}

fun printVector(x: Array<Double>) {
    print(CYAN)
    x.forEachIndexed { i, it ->
        println("x$i = $it")
    }
}

fun holderNorm(x: Array<Double>) : Double {
    var sum = 0.0
    x.forEach {
        sum += abs(it).pow(x.size)
    }
    return sum.pow(1.0 / x.size)
}

fun holderMatrixNorm(x: Array<Array<Double>>): Double {
    var sum = 0.0
    x.forEach { array ->
        array.forEach {
            sum += abs(it).pow(x.size)
        }
    }
    return sum.pow(1.0 / x.size)
}

fun infinityPMatrixNorm(matrix: Array<Array<Double>>): Double {
    var max: Double = - 1.0
    for (i in matrix.indices) {
        var sum = 0.0
        for (j in matrix[i].indices) {
            sum += abs(matrix[i][j])
        }
        max = max(max, sum)
    }
    return max
}

fun getSolutionOfSquareMatrix(startMatrix: Array<Array<Double>>, startB: Array<Double>): Array<Double> {
    val matrix: Array<Array<Double>> =
        arrayOf(arrayOf(startMatrix[0][0], startMatrix[0][1]), arrayOf(startMatrix[1][0], startMatrix[1][1]))
    val b: Array<Double> = arrayOf(startB[0], startB[1])
    var sign = 1
    if (sign(matrix[0][0]) != sign(matrix[1][0])) {
        sign = -1
    }
    val coefficient: Double = sign * matrix[1][0] / matrix[0][0]
    matrix[1][1] += matrix[0][1] * coefficient
    b[1] += b[0] * coefficient
    val x1: Double = b[1] / matrix[1][1]
    val x0: Double = (b[0] - matrix[0][1] * x1) / matrix[0][0]
    return arrayOf(x0, x1)
}

/**
 * Method that obtains the inverse matrix to a given one by the Jordan-Gauss method
 * @param originMatrix - original matrix
 * @return inverse matrix
 */
fun getInverseMatrix(originMatrix: Array<Array<Double>>): Array<Array<Double>> {
    var reversedMatrix = arrayOf<Array<Double>>()
    var matrix = arrayOf<Array<Double>>()
    for (i in originMatrix.indices) {
        var array = arrayOf<Double>()
        var copyArray = arrayOf<Double>()
        for (j in originMatrix[i].indices) {
            copyArray += originMatrix[i][j]
            array += if (i == j) {
                1.0
            } else {
                0.0
            }
        }
        reversedMatrix += array
        matrix += copyArray
    }

    for (k in matrix.indices) {
        val temp = matrix[k][k]
        for (i in matrix.indices) {
            matrix[k][i] /= temp
            reversedMatrix[k][i] /= temp
        }

        for (i in matrix.indices) {
            if (i != k) {
                val temp2: Double = matrix[i][k] / matrix[k][k]
                for (j in matrix.indices) {
                    matrix[i][j] = matrix[i][j] - temp2 * matrix[k][j]
                    reversedMatrix[i][j] = reversedMatrix[i][j] - temp2 * reversedMatrix[k][j]
                }
            }
        }
    }
    return reversedMatrix
}

fun getUnitMatrix(size: Int): Array<Array<Double>> {
    var result = arrayOf<Array<Double>>()
    for (i in 0 until size) {
        var array = arrayOf<Double>()
        for (j in 0 until size) {
            array += if (i == j) {
                1.0
            } else {
                0.0
            }
        }
        result += array
    }
    return result
}

fun multiplyMatrix(matrix1: Array<Array<Double>>, matrix2: Array<Array<Double>>): Array<Array<Double>>? {
    if (matrix1.isEmpty() || matrix2.isEmpty() || matrix1[0].size != matrix2.size) {
        return null
    }
    var result = arrayOf<Array<Double>>()
    for (i in matrix1.indices) {
        var array = arrayOf<Double>()
        for (j in matrix2[0].indices) {
            var value = 0.0
            for (r in matrix2.indices) {
                value += matrix1[i][r] * matrix2[r][j]
            }
            array += value
        }
        result += array
    }
    return result
}

fun multiplyMatrixByVector(matrix: Array<Array<Double>>, b: Array<Double>): Array<Double>? {
    if (matrix.isEmpty() || matrix[0].size != b.size) {
        return null
    }
    var result = arrayOf<Double>()
    for (i in matrix.indices) {
        var sum = 0.0
        for (j in matrix.indices) {
            sum += matrix[i][j] * b[j]
        }
        result += sum
    }
    return result
}

fun getZeroVector(dimension: Int): Array<Double> {
    var result = arrayOf<Double>()
    for (i in 0 until dimension) {
        result += 0.0
    }
    return result
}

fun vectorDifference(x: Array<Double>, x1: Array<Double>): Array<Double> {
    var differenceVector = arrayOf<Double>()
    for (i in x.indices) {
        val value = x[i] - x1[i]
        differenceVector += value
    }
    return differenceVector
}

fun vectorSum(x: Array<Double>, x1: Array<Double>): Array<Double> {
    var differenceVector = arrayOf<Double>()
    for (i in x.indices) {
        differenceVector += x[i] + x1[i]
    }
    return differenceVector
}

fun multiplyVectorByConst(a: Double, x: Array<Double>): Array<Double> {
    var result = arrayOf<Double>()
    x.forEach {
        result += it * a
    }
    return result
}

fun matrixDifference(matrix1: Array<Array<Double>>, matrix2: Array<Array<Double>>): Array<Array<Double>>? {
    if (matrix1.isEmpty() || matrix2.isEmpty() || matrix1.size != matrix2.size ||
        matrix1[0].size != matrix2[0].size) {
        return null
    }
    var result = arrayOf<Array<Double>>()
    for (i in matrix1.indices) {
        var array = arrayOf<Double>()
        for (j in matrix1[i].indices) {
            array+= matrix1[i][j] - matrix2[i][j]
        }
        result += array
    }
    return result
}

fun copyOf(matrix: Array<Array<Double>>): Array<Array<Double>> {
    var result = arrayOf<Array<Double>>()
    for (i in matrix.indices) {
        result += matrix[i].copyOf()
    }
    return result
}

fun getD(matrix: Array<Array<Double>>): Array<Array<Double>> {
    var result = arrayOf<Array<Double>>()
    for (i in matrix.indices) {
        var array = arrayOf<Double>()
        for (j in matrix[i].indices) {
            array += if (i == j) {
                matrix[i][j]
            } else {
                0.0
            }
        }
        result += array
    }
    return result
}

fun getDoubleArray(matrix: Array<Array<Double>>): Array<DoubleArray> {
    var result: Array<DoubleArray> = arrayOf()
    for (i in matrix.indices) {
        val array = DoubleArray(matrix[i].size)
        for (j in matrix[i].indices) {
            array[j] = matrix[i][j]
        }
        result += array
    }
    return result
}

fun doubleArrayToArray(array: DoubleArray): Array<Double> {
    var result: Array<Double> = arrayOf()
    array.forEach { result += it }
    return result
}