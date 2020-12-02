package task5

import org.apache.commons.math3.analysis.UnivariateFunction
import kotlin.math.pow
import kotlin.math.sqrt

class SecondFunction: UnivariateFunction {
    override fun value(x: Double): Double {
        return f2(x) * 1 / sqrt(1-x.pow(2))
    }
}