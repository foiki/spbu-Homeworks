package prac.homework3

class PowerMethod(matrix: Array<Array<Double>>, eps: Double):
    MaxAbsEigenValueMethod(matrix, eps) {

    override var methodName = "power-law"

    override fun updateMaxAbsEigenValue(newYK: Array<Double>, eigenVector: Array<Double>): Double {
        return newYK[0] / eigenVector[0]
    }
}