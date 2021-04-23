package homework5.taskParams

import homework5.Function

/**
 * Function u(x, t) = 0
 */
class TaskFunction3: Function {
    override fun getValue(x: Double, t: Double): Double {
        return 0.0
    }

    override fun getDerivativeXValue(x: Double, t: Double): Double {
        return 0.0
    }

    override fun getDerivativeTValue(x: Double, t: Double): Double {
        return 0.0
    }

    override fun getSecondDerivativeXValue(x: Double, t: Double): Double {
        TODO("Not yet implemented")
    }
}