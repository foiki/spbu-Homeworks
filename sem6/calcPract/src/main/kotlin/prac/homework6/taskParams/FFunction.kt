package prac.homework6.taskParams

import utils.functions.SimpleFunctionWithDerivative

class FFunction: SimpleFunctionWithDerivative {
    override fun getValue(x: Double): Double {
        return 1 + x
    }

    override fun getDerivativeValue(x: Double): Double {
        return 1.0
    }

    override fun toString(): String {
        return "1 + x"
    }
}