package task5

import org.apache.commons.math3.analysis.UnivariateFunction

class TestFunction: UnivariateFunction {

    override fun value(x: Double): Double {
        return f1(x) * p1(x)
    }
}