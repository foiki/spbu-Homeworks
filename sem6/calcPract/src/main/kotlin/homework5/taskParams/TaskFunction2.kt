package homework5.taskParams

import homework5.Function

/**
 * Function u(x, t) = x^2 + 1
 */
class TaskFunction2: Function {
    override fun getValue(x: Double, t: Double): Double {
        return x * x + 1
    }

    override fun getDerivativeXValue(x: Double, t: Double): Double {
        return 2 * x
    }

    override fun getDerivativeTValue(x: Double, t: Double): Double {
        return 0.0
    }

    override fun getSecondDerivativeXValue(x: Double, t: Double): Double {
        return 2.0
    }
}