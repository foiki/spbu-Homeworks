package prac.homework5.taskParams

import utils.functions.SimpleFunction

/**
 * Function b(t) = 1
 */
class TaskBeta2Function: SimpleFunction {
    override fun getValue(t: Double): Double {
        return 1.0
    }
}