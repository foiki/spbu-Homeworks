package prac.homework6.taskParams

import utils.functions.SimpleFunctionWithDerivative

class QFunction: SimpleFunctionWithDerivative {
    override fun getValue(x: Double): Double {
        return 0.0
    }

    override fun getDerivativeValue(x: Double): Double {
        return 0.0
    }

    override fun toString(): String {
        return "0"
    }
}