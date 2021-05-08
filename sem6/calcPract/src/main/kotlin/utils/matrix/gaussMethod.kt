package utils.matrix

fun swapRows(matrix: Array<Array<Double>>, b: Array<Double>, row1: Int, row2: Int) {
    var temp: Double
    for (q in matrix[row1].indices) {
        temp = matrix[row1][q]
        matrix[row1][q] = matrix[row2][q]
        matrix[row2][q] = temp
    }
    temp = b[row1]
    b[row1] = b[row2]
    b[row2] = temp
}

fun sortByColumn(matrix: Array<Array<Double>>, b: Array<Double>, column: Int) {
    for (i in column until matrix.size - 1) {
        for (j in column until matrix.size - 1) {
            if (matrix[j][column] < matrix[j + 1][column]) {
                swapRows(matrix, b, j, j + 1)
            }
        }
    }
}

fun provideToDiagonal(matrix: Array<Array<Double>>, b: Array<Double>, column: Int) {
    val temp = matrix[column][column]
    for (i in column + 1 until matrix.size) {
        val u = matrix[i][column] / temp
        for (j in matrix[i].indices) {
            matrix[i][j] -= matrix[column][j] * u
        }
        b[i] -= b[column] * u
    }
}

fun straightMove(matrix: Array<Array<Double>>, b: Array<Double>) {
    for (column in 0 until matrix.size - 1) {
        sortByColumn(matrix, b, column)
        provideToDiagonal(matrix, b, column)
    }
}

fun reverseMove(matrix: Array<Array<Double>>, b: Array<Double>): Array<Double> {
    var solution: Array<Double> = arrayOf()
    val start = matrix.size - 1
    for (i in start downTo 0) {
        var x = b[i]
        var pos = solution.size - 1
        for (j in (i + 1) until matrix.size) {
            x -= matrix[i][j] * solution[pos]
            --pos
        }
        solution += x / matrix[i][i]
    }
    return solution.reversedArray()
}

fun gaussMethodSolutionByMainElement(matrix: Array<Array<Double>>, b: Array<Double>): Array<Double> {
    val matrixCopy = copyOf(matrix)
    val bCopy = b.copyOf()
    straightMove(matrixCopy, bCopy)
    return reverseMove(matrixCopy, bCopy)
}