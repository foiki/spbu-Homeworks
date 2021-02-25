package homework1

fun luDecomposition(matrix: Array<Array<Double>>): Double {
    val l = initMatrix(matrix.size, matrix[0].size)
    val u = initMatrix(matrix.size, matrix[0].size)

    for (i in matrix.indices) {
        for (j in matrix.indices) {
            var sum = 0.0
            for (k in 0 until i) {
                sum += l[j][k] * u[k][i]
            }
            l[j][i] = matrix[j][i] - sum
        }
        for (j in matrix.indices) {
            var sum = 0.0
            for (k in 0 until i) {
                sum += l[i][k] * u[k][j]
            }
            u[i][j] = (matrix[i][j] - sum) / l[i][i]
        }
    }

    var det = 1.0
    for (i in matrix.indices) {
        det *= l[i][i]
    }
    return det
}