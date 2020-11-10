package task4

import java.lang.Math.E
import kotlin.math.pow

class Exp: Function {

    override fun value(x: Double): Double {
        return 5 * E.pow(2 * x)
    }

    override fun maxOfFirstDerivative(): Double {
        return 10 * E.pow(2 * B)
    }

    override fun maxOfSecondDerivative(): Double {
        return 20 * E.pow(2 * B)
    }

    override fun maxOfThirdDerivative(): Double {
        return 40 * E.pow(2 * B)
    }

    override fun maxOfFourthDerivative(): Double {
        return 80 * E.pow(2 * B)
    }

    override fun toString(): String {
        return "5*e^(2x)"
    }
}