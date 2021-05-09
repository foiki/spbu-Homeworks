package lectures.lecturetask3

import utils.functions.Function
import kotlin.math.cos
import kotlin.math.sin

/**
 * Function K(x, s) = cos(1 - xs)
 */
class TaskFunctionK: Function {
    override fun getValue(x: Double, t: Double): Double {
        return cos(1 - x * t)
    }

    override fun getDerivativeXValue(x: Double, t: Double): Double {
        return t * sin(1 - x * t)
    }

    override fun getDerivativeTValue(x: Double, t: Double): Double {
        return x * sin(1 - x * t)
    }

    override fun getSecondDerivativeXValue(x: Double, t: Double): Double {
        return - t * t * cos(1 - x * t)
    }

    override fun toString(): String {
        return "cos(1 - xs)"
    }
}