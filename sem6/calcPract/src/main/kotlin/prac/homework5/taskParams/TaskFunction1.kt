package prac.homework5.taskParams

import utils.functions.Function

/**
 * Function u(x, t) = 1
 */
class TaskFunction1: Function {
    override fun getValue(x: Double, t: Double): Double {
        return 1.0
    }

    override fun getDerivativeXValue(x: Double, t: Double): Double {
        return 0.0
    }

    override fun getDerivativeTValue(x: Double, t: Double): Double {
        return 0.0
    }

    override fun getSecondDerivativeXValue(x: Double, t: Double): Double {
        return 0.0
    }
}