package homework5

class WeightMethod(private val N: Int, private val M: Int, T: Double, private val function: Function):
    GridMethod(N, M, T, function) {

    private val sigma = 1.0

    override fun performTask() {
        initZeroU()
        initU()
    }

    @Override
    private fun initU() {
        for (k in 1..M) {
            val implicitMethod = ImplicitMethod(N, hM, sigma, function, u, k)
            u += implicitMethod.getSolution()
        }
    }
}