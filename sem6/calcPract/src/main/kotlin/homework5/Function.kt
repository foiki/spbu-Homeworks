package homework5

interface Function {
    fun getValue(x: Double, t: Double): Double

    fun getDerivativeXValue(x: Double, t: Double): Double

    fun getDerivativeTValue(x: Double, t: Double): Double

    fun getSecondDerivativeXValue(x: Double, t: Double): Double
}