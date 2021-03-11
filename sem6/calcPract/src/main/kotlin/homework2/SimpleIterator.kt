package homework2

import utils.matrix.*

/**
 * Class makes simple iteration method to find solution of a system in 'x = Hx + g' representation
 * @param h - h matrix
 * @param g - g vector
 * @param solution - accurate solution
 * @param eigenValues - eigen values of matrix h
 */
class SimpleIterator(
    private val h: Array<Array<Double>>, private val g: Array<Double>,
    private val solution: Array<Double>,
    private val eigenValues: Array<Double>? = null): IterationMethod(h, g, solution, eigenValues) {

    override var methodName = "simple iteration"

    override fun makeIterations() {
        numberOfIterations = 0
        while (holderNorm(vectorDifference(resultX, solution)) >= eps) {
            prevResultX = resultX
            resultX = vectorSum(multiplyMatrixByVector(h, resultX)!!, g)
            ++numberOfIterations
        }
        wasIterationMade = true
    }
}