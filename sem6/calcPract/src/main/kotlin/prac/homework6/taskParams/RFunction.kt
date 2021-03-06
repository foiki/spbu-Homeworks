package prac.homework6.taskParams

import utils.functions.SimpleFunctionWithDerivative
import kotlin.math.cos
import kotlin.math.sin

class RFunction: SimpleFunctionWithDerivative {
    override fun getValue(x: Double): Double {
        return 1 + sin(x)
    }

    override fun getDerivativeValue(x: Double): Double {
        return cos(x)
    }

    override fun toString(): String {
        return "(1 + sin(x))"
    }
}