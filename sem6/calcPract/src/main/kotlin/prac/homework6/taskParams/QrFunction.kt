package prac.homework6.taskParams

class QrFunction(private val taskType: Int, private val beta1: Double, private val beta2: Double) {

    private val pFunction = PFunction()

    fun getValue(y1: Double, z1: Double): Double {
        if (taskType == 1 || taskType == 2) {
            return 0.0
        }
        return beta1 / beta2 * pFunction.getValue(1.0) * y1 * z1
    }
}