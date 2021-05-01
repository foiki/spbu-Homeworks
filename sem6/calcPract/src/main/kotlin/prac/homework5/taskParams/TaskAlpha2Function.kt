package prac.homework5.taskParams

import utils.functions.SimpleFunction

/**
 * Function a(t) = -1
 */
class TaskAlpha2Function: SimpleFunction {
    override fun getValue(t: Double): Double {
        return - 1.0
    }
}