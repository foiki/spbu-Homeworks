package lectures.lectureTask4

import utils.functions.SimpleFunction
import kotlin.math.sin

class SolutionFunction: SimpleFunction {
    override fun getValue(t: Double): Double {
        return (sin(1.0) - sin(1 - t)) / t
    }

}