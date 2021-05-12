package prac.homework6.taskParams

class QlFunction(private val taskType: Int, private val alpha1: Double, private val alpha2: Double) {

    private val pFunction = PFunction()

    fun getValue(y1: Double, z1: Double): Double {
        if (taskType == 1 || taskType == 2) {
            return 0.0
        }
        return alpha1 / alpha2 * pFunction.getValue(- 1.0) * y1 * z1
    }
}