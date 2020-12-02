package task5

import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator.DEFAULT_ABSOLUTE_ACCURACY
import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator.DEFAULT_RELATIVE_ACCURACY
import java.util.*
import org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

var A1: Double = 0.0
var B1: Double = 1.0
var A2: Double = -1.0
var B2: Double = 1.0
var m: Int = 100
var J: Double = 0.0
var J1: Double = 0.0
var N = 5
val scanner: Scanner = Scanner(System.`in`)
val function = TestFunction()
val secondFunction = SecondFunction()

/*
Вариант4 тестовой задачи для пп. 3)+4)
[a, b]= [0,1], f(x)=sin(x), ρ(x)=1/x1⁄4, getM=100.
Вариант4 тестовой задачи для п. 5)
[a, b]= [-1,1], f(x)=1/(1+x2), ρ(x)=1/√(1-x2), N=5
 */

fun f1(x: Double): Double {
    return sin(x)
}

fun f2(x: Double): Double {
    return 1 / (1 + x.pow(2))
}

fun p1(x: Double): Double {
    return 1 / x.pow(1 / 4.0)
}

fun readStartInfo() {
    print("Введите левый предел(A): ")
    A1 = scanner.nextDouble()
    print("Введите правый предел(B): ")
    B1 = scanner.nextDouble()
    print("Введите число промежутков деления [A,B]: ")
    m = scanner.nextInt()
    println("Введите число узлов для квадратурной формулы Меллера: ")
    N = scanner.nextInt()
}

fun printStartInfo() {
    println("\nЛабораторная работа №5, Вариант 4")
    println("Приближённое вычисление интегралов при помощи квадратурных формул\n" +
            "Наивысшей Алгебраической Степени Точности (КФ НАСТ)\n")
}

fun calculateIntegralAccurately() {
    val integrator = IterativeLegendreGaussIntegrator(15, DEFAULT_RELATIVE_ACCURACY, DEFAULT_ABSOLUTE_ACCURACY)
    J = integrator.integrate(100000000, function, A1, B1)
    println("J = $J")
    J1 = integrator.integrate(10000000, secondFunction, A2, B2)
}

fun main() {
    printStartInfo()
    readStartInfo()
    calculateIntegralAccurately()
    println("Результаты вычислений интеграла через КФ НАСТ:")
    println("Функция: f(x) = sin(x), p(x) = 1 / x^(1/4)")
    compositeGauss()
    gauss()
    meller()
}