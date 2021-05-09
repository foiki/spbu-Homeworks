package lectures.lecturetask3

import utils.functions.SimpleFunction

class ConstantFunction: SimpleFunction {

    override fun getValue(t: Double): Double {
        return 1.0
    }

    override fun toString(): String {
        return "1"
    }
}