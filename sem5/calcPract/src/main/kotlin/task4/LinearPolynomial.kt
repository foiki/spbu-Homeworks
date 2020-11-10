package task4

class LinearPolynomial: Function {

    override fun value(x: Double): Double {
        return 4 * x
    }

    override fun maxOfFirstDerivative(): Double {
        return 4.0
    }

    override fun maxOfSecondDerivative(): Double {
        return 0.0
    }

    override fun maxOfThirdDerivative(): Double {
        return 0.0
    }

    override fun maxOfFourthDerivative(): Double {
        return 0.0
    }

    override fun toString(): String {
        return "4x"
    }
}