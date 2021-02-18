package task3p1

import task2.getTableOfValues
import java.util.*
import kotlin.math.*

var m = 10
var a: Double = 0.0
var b: Double = 0.7
var n1 = 9
var n2 = 7
var F: Double = 1.146
var eps: Double = 10.0.pow(-9)
var tableOfValues: List<Pair<Double, Double>> = getTableOfValues()
val reader = Scanner(System.`in`)

fun f(x: Double): Double {
    return sqrt(1 + x * x)
}

fun printStartInfo() {
    println("\nЛР 3.1. Задача обратного интерполирования")
    println("Вариант 4")
    println("task3p1.f(x) = sqrt(1 + x^2)")
}

fun readStartData() {
    print("\nВведите число значений в таблице функции (task3p1.getM + 1): ")
    m = reader.nextInt() - 1
    print("Введите начало отрезка (a): ")
    a = reader.nextDouble();
    print("Введите конец отрезка (b): ")
    b = reader.nextDouble();
    printTableInfo(tableOfValues)
}

fun readPower(maxPower: Int):Int {
    var n = reader.nextInt()
    while (n > maxPower || n < 0) {
        println("Введено недопустимое значение n!")
        print("Введите n еще раз: ")
        n = reader.nextInt()
    }
    return n
}

fun readData() {
    print("\nВведите значение функции в точке интерполяции (task3p1.getF): ")
    F = reader.nextDouble()
    print("Введите степень интерполяционного многочлена для task3p1.f^-1 (<= $m): ")
    n1 = readPower(m)
    print("Введите степень интерполяционного многочлена для task3p1.f (<= $m): ")
    n2 = readPower(m)
    print("Введите точность решения (epsilon): ")
    eps = reader.nextDouble()
}

fun calculateLagrangePolynomialValue(list: List<Pair<Double, Double>>): Double {
    var result = 0.0
    list.forEach { i ->
        var polynomial = 1.0
        list.forEach { j ->
            if (i != j) {
                polynomial *= (F - j.first) / (i.first - j.first)
            }
        }
        result += i.second * polynomial
    }
    return result
}

fun bisection(left: Double, right: Double): Double {
    var l = left
    var r = right
    while (abs(l - r) > 2 * eps) {
        val mid = (l + r) / 2
        if (sign(f(l)) != sign(f(mid))) {
            r = mid
        } else {
            l = mid
        }
    }
    return (l + r) / 2
}

fun main() {
    printStartInfo()
    //readStartData()
    printTableInfo(tableOfValues)
    //readData()
    var userAnswer = "Y"
    while (userAnswer == "Y" || userAnswer == "y") {
        val sortedTable = sortTableOfValues(invertedTable(tableOfValues)).take(n1 + 1)
        println("\nОтсортированная превернутая таблица с ${n1 + 1} значениями: ")
        printTableOfValues(sortedTable)
        userAnswer = "n"

        val polynomialValue = calculateLagrangePolynomialValue(sortedTable)
        println("\nРезультат обратной интерполяции способом переворота таблицы: $polynomialValue")
        println("Модуль невязки: ${abs(f(polynomialValue) - F)}")

        if (sortedTable.size < 2) {
            println("Слишком мало значений для поиска методом биссекции! (меньше 2)")
        } else {
            val bisectionValue = bisection(sortedTable[0].second, sortedTable[1].second)
            println("\nРезультат обратной интерполяции способом бисекции: $polynomialValue")
            println("Модуль невязки: ${abs(f(bisectionValue) - F)}")
        }
    }
}