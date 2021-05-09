package lectures.lecturetask3

import utils.functions.SimpleFunction

class TestZFunction: SimpleFunction {

    override fun getValue(t: Double): Double {
        return 5 * t + 3
    }

    override fun toString(): String {
        return "5s + 3"
    }
}