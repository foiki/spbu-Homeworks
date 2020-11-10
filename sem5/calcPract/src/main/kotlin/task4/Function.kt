package task4

import org.apache.commons.math3.analysis.UnivariateFunction

interface Function: UnivariateFunction {
    fun maxOfFirstDerivative(): Double
    fun maxOfSecondDerivative(): Double
    fun maxOfThirdDerivative(): Double
    fun maxOfFourthDerivative(): Double
}