package task3p2

import java.util.*
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.pow

var m: Int = 10
var a: Double = 0.0
var h: Double = 10.0.pow(-6)
var tableOfValues: List<Pair<Double, Double>> = LinkedList()
val scanner = Scanner(System.`in`)

fun printStartInfo() {
    println("\nЛР 3.2. Нахождение производных таблично-заданной функции по формулам численного дифференцирования")
    println("Вариант 4")
    println("f(x) = e^(7.5 * x)")
}

fun readStartData() {
    val scanner = Scanner(System.`in`)
    print("\nВведите число значений в таблице функции: ")
    m = scanner.nextInt() - 1
    print("Введите начало отрезка (a): ")
    a = scanner.nextDouble();
    print("Введите шаг (h): ")
    h = scanner.nextDouble();
}

fun f(x: Double): Double {
    return exp(7.5 * x)
}

fun firstDerivative(x: Double): Double {
    return 7.5 * f(x)
}

fun getFirstDerivative(n: Int): Double {
    return when (n) {
        0 -> (-3 * tableOfValues[n].second + 4 * tableOfValues[n + 1].second - tableOfValues[n + 2].second) / (2 * h)
        tableOfValues.size - 1 -> (3 * tableOfValues[n].second - 4 * tableOfValues[n - 1].second + tableOfValues[n - 2].second) / (2 * h)
        else -> (tableOfValues[n + 1].second - tableOfValues[n - 1].second) / (2 * h)
    }
}

fun secondDerivative(x: Double): Double {
    return 7.5 * 7.5 * f(x)
}

fun getSecondDerivative(n: Int): Double {
    return when (n) {
        0, tableOfValues.size - 1 -> 0.0
        else -> (tableOfValues[n + 1].second - 2 * tableOfValues[n].second + tableOfValues[n - 1].second) / (h * h)
    }
}

fun getDerivativeTable() {
    println("\nТаблица с применением формул частного дифференцирования:")
    print(String.format("%-13s", "| x"))
    print(String.format("%-24s", "| f(x)"))
    print(String.format("%-25s", "| f(x)'чд"))
    print(String.format("%-25s", "| f(x)'чд - f(x)'т"))
    print(String.format("%-25s", "| f(x)''чд"))
    println(String.format("%-24s", "| f(x)''чд - f(x)''т") + "|")
    var counter = 0
    tableOfValues.forEach {
        val firstDerivative = getFirstDerivative(counter)
        val secondDerivative = getSecondDerivative(counter)
        val firstDif = abs(firstDerivative - firstDerivative(it.first))
        val secondDif = abs(secondDerivative - secondDerivative(it.first))

        val x = String.format("%.7f", it.first)
        print("| ${String.format("%-10s", x)}")
        print(" | ${String.format("%-21s", it.second.toString())}")
        print(" | ${String.format("%-22s", firstDerivative.toString())}")
        print(" | ${String.format("%-22s", firstDif.toString())}")
        if (counter == 0 || counter == tableOfValues.size - 1) {
            print(String.format("%-25s", " |"))
            println(String.format("%-25s", " |") + "|")
        } else {
            print(" | ${String.format("%-22s", secondDerivative.toString())}")
            println(" | ${String.format("%-22s", secondDif.toString())}" + "|")
        }
        ++counter
    }
}

fun main() {
    var userAnswer = "Y"
    while (userAnswer == "Y" || userAnswer == "y") {
        printStartInfo()
        readStartData()
        tableOfValues = getTableOfValues()
        printTableInfo(tableOfValues)
        getDerivativeTable()
        userAnswer = readUserAnswer()
    }
}


