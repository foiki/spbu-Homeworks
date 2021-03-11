package homework2

import utils.matrix.*

/**
 * Class makes simple iteration method to find solution of a system in 'x = Hx + g' representation
 * @param h - h matrix
 * @param g - g vector
 * @param solution - accurate solution
 * @param eigenValues - eigen values of matrix h
 */
class SeidelIterator (
    private val h: Array<Array<Double>>, private val g: Array<Double>,
    private val solution: Array<Double>,
    private val eigenValues: Array<Double>? = null): IterationMethod(h, g, solution, eigenValues) {

    override var methodName = "Seidel"

    override fun makeIterations() {
        numberOfIterations = 0
        val inverseMatrix = getInverseMatrix(matrixDifference(getUnitMatrix(h.size), getHl(h))!!)
        val left = multiplyMatrix(inverseMatrix, getHr(h))!!
        val right = multiplyMatrixByVector(inverseMatrix, g)!!
        while (holderNorm(vectorDifference(resultX, solution)) >= eps) {
            prevResultX = resultX
            resultX = vectorSum(multiplyMatrixByVector(left, resultX)!!, right)
            ++numberOfIterations
        }
        wasIterationMade = true
    }
}