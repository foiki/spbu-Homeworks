package task4

import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator.DEFAULT_ABSOLUTE_ACCURACY
import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator.DEFAULT_RELATIVE_ACCURACY
import java.util.*
import org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator

var A: Double = -1.0
var B: Double = 5.0
var m: Int = 10000
var h: Double = (B - A) / m
var J: Double = 0.0
val scanner: Scanner = Scanner(System.`in`)

val functions: List<Function> = listOf(ThirdPolynomial(),
        LinearPolynomial(),
        Exp())
var function: Function = ThirdPolynomial()

fun w(x: Double): Double {
    return 1.0
}

fun readStartInfo() {
    print("Введите левый предел(A): ")
    A = scanner.nextDouble()
    print("Введите правый предел(B): ")
    B = scanner.nextDouble()
    print("Введите число промежутков деления [A,B]: ")
    m = scanner.nextInt()
    h = (B - A) / m
}

fun printStartInfo() {
    println("\nЛабораторная работа №4")
    println("Приближённое вычисление интеграла по составным квадратурным формулам")
    chooseFunction()
}

fun chooseFunction() {
    println("\nДоступные функции для вычисления игнтеграла: ")
    var counter = 0
    functions.forEach {
        println("$counter) $it")
        ++counter
    }
    var functionNumber = -1
    while(functionNumber < 0 || functionNumber > functions.size) {
        print("Выберите номер функции из списка: ")
        functionNumber = scanner.nextInt()
    }
    function = functions[functionNumber]
}

fun calculateIntegralAccurately() {
    val integrator = IterativeLegendreGaussIntegrator(15, DEFAULT_RELATIVE_ACCURACY, DEFAULT_ABSOLUTE_ACCURACY)
    J = integrator.integrate(10000, function, A, B)
    println("J = $J")
}

fun main() {
    var userAnswer = "Y"
    while (userAnswer == "Y" || userAnswer == "y") {
        printStartInfo()
        readStartInfo()
        calculateIntegralAccurately()
        println("Результаты вычислений интеграла через составные квадратурные формулы:")
        leftRectangles()
        rightRectangles()
        averageRectangles()
        trapezium()
        simpson()
        userAnswer = "n"
    }
}