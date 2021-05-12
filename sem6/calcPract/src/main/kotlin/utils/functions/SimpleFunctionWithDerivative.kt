package utils.functions

interface SimpleFunctionWithDerivative {
    fun getValue(x: Double): Double
    fun getDerivativeValue(x: Double): Double
}