package homework5.taskParams

import homework5.SimpleFunction

/**
 * Function b(t) = 1
 */
class TaskBeta2Function: SimpleFunction {
    override fun getValue(t: Double): Double {
        return 1.0
    }
}