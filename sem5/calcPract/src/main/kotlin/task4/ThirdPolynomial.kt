package task4

import java.lang.Double.max

class ThirdPolynomial: Function {

    override fun value(x: Double): Double {
        return 4 * x * x * x + 7 * x * x - 3 * x + 6
    }

    override fun maxOfFirstDerivative(): Double {
        return max(12 * A * A + 14 * A - 3, 12 * B * B + 14 * B - 3)
    }

    override fun maxOfSecondDerivative(): Double {
        return 24 * B + 14
    }

    override fun maxOfThirdDerivative(): Double {
        return 24.0
    }

    override fun maxOfFourthDerivative(): Double {
        return 0.0
    }

    override fun toString(): String {
        return "4x^3+7x^2-3x+6"
    }
}