package prac.homework3

import utils.matrix.*

class ScalarProductMethod(matrix: Array<Array<Double>>, eps: Double):
    MaxAbsEigenValueMethod(matrix, eps) {

    override var methodName = "scalar product"

    override fun updateMaxAbsEigenValue(newYK: Array<Double>, eigenVector: Array<Double>): Double {
        return scalarProduct(newYK, eigenVector)!! / scalarProduct(eigenVector, eigenVector)!!
    }
}