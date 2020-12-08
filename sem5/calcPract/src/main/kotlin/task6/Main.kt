package task6

import java.util.*

var x0: Double = 1.0
var y0: Double = 0.5
var C: Double = 1.0
var h: Double = 0.1
var N: Int = 10
val scanner: Scanner = Scanner(System.`in`)
lateinit var tailorValues: MutableList<Pair<Double, Double>>

fun printStartInfo() {
    println("\nЛабораторная работа №6, Вариант 4")
    println("Численное решение Задачи Коши для обыкновенного дифференциального\n" +
            "уравнения первого порядка")
}

fun readStartInfo() {
    println("Введите условие задачи Коши:")
    print("Введите x0: ")
    x0 = scanner.nextDouble()
    print("Введите y0: ")
    y0 = scanner.nextDouble()
    print("Введите шаг h: ")
    h = scanner.nextDouble()
    println("Введите число узлов N: ")
    N = scanner.nextInt()
}

fun f(x: Double, y: Double): Double {
    return - y + y * y
}

//y = 1 / (C * e^x + 1)

fun main() {
    printStartInfo()
    //readStartInfo()
    C = getConstFromSolution()
    printSolutionTable()
    printTailorDecomposition()
    printAdams()
    printRungeKutta()
    printEuler()
    printEulerFirst()
    printEulerSecond()
}