package homework2;

import utils.matrix.printVector

class ChebyshevParametersSetIterator(
        private val a: Array<Array<Double>>, private val b: Array<Double>,
        private val solution: Array<Double>,
        private val eigenValues: Array<Double>? = null): IterationMethod(a, b, solution, eigenValues) {

        override var methodName = "Chebyshev parameters set"
        override var makeLyusternikCorrection = false
        override var makeEstimations = false

        override fun makeIterations() {
                TODO()
        }
}
