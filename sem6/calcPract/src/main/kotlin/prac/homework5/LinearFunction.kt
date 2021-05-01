package prac.homework5

import utils.functions.Function

/**
 * u(x, t) = 5x - 3t
 */
class LinearFunction: Function {
    override fun getValue(x: Double, t: Double): Double {
        return 5 * x - 3 * t
    }

    override fun getDerivativeXValue(x: Double, t: Double): Double {
        return 5.0
    }

    override fun getDerivativeTValue(x: Double, t: Double): Double {
        return - 3.0
    }

    override fun getSecondDerivativeXValue(x: Double, t: Double): Double {
        return 0.0
    }

    override fun toString(): String {
        return "5x - 3t"
    }
}