package prac.homework6.taskParams

import utils.functions.SimpleFunctionWithDerivative
import kotlin.math.pow

class PFunction: SimpleFunctionWithDerivative {
    override fun getValue(x: Double): Double {
        return 1 / (2 + x)
    }

    override fun getDerivativeValue(x: Double): Double {
        return - 1 / (2 + x).pow(2)
    }

    override fun toString(): String {
        return "1 / (2 + x)"
    }
}