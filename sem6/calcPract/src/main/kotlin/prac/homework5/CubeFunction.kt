package prac.homework5

import utils.functions.Function

/**
 * Function u(x, t) = x^3 + t^3
 */
class CubeFunction: Function {
    override fun getValue(x: Double, t: Double): Double {
        return x * x * x + t * t * t
    }

    override fun getDerivativeXValue(x: Double, t: Double): Double {
        return 3 * x * x
    }

    override fun getDerivativeTValue(x: Double, t: Double): Double {
        return 3 * t * t
    }

    override fun getSecondDerivativeXValue(x: Double, t: Double): Double {
        return 6 * x
    }

    override fun toString(): String {
        return "x^3 + t^3"
    }
}