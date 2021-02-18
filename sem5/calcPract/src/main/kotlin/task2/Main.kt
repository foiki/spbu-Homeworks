package task2

import java.util.*
import kotlin.math.abs
import kotlin.math.exp

var m = 15
var a: Double = 0.0
var b: Double = 0.7
var n = 3
var x: Double = 0.65

fun printStartInfo() {
    println("ЛР 2. Задача алгебраического интерполирования")
    println("Вариант 4")
    println("Число значений в таблице: ${m + 1}")
}

fun readData() {
    val reader = Scanner(System.`in`)
    print("Введите количество узлов в таблице: ")
    m = reader.nextInt() - 1
    print("Введите начало промежутка: ")
    a = reader.nextDouble()
    print("Введите конец промежутка: ")
    b = reader.nextDouble()
    print("Введите степень интерполяционного многочлена: ")
    n = reader.nextInt()
    while (n > m) {
        println("Введено недопустимое значение n!")
        println("Введите n еще раз: ")
        n = reader.nextInt()
    }
    print("Введите точку интерполирования: ")
    x = reader.nextDouble()
}

fun f(x: Double): Double {
    return exp(-x) - x * x /2
    //return sqrt(1 + x * x)
}

fun getTableOfValues(): LinkedList<Pair<Double, Double>> {
    val result = LinkedList<Pair<Double, Double>>()
    val segment = b - a
    for (i in 0..m) {
        val newValue = a + i * segment / m
        result.add(Pair(newValue, f(newValue)))
    }
    return result
}

fun printTableOfValues(list: List<Pair<Double, Double>>) {
    var counter = 0
    list.forEach {
        println("$counter) ${it.first} ${it.second}")
        ++counter
    }
}

fun sortTableOfValues(list: List<Pair<Double, Double>>): List<Pair<Double, Double>> {
    return list.sortedWith(kotlin.Comparator { a, b ->
        when {
            abs(a.first - x) > abs(b.first - x) -> 1
            abs(a.first - x) < abs(b.first - x) -> -1
            else -> 0
        }
    })
}

fun calculateLagrangePolynomialValue(list: List<Pair<Double, Double>>): Double {
    var result = 0.0
    list.forEach { i ->
        var polynomial = 1.0
        list.forEach { j ->
            if (i != j) {
                polynomial *= (x - j.first) / (i.first - j.first)
            }
        }
        result += i.second * polynomial
    }
    return result
}

fun askForRestart(): String {
    print("Если хотите ввести собственные начальные данные ответьте 'Y' или 'y': ")
    val reader = Scanner(System.`in`)
    val answer = reader.next()
    if (answer == "Y" || answer == "y") {
        readData()
    }
    return answer
}

fun main() {
    var userAnswer = "Y"
    while (userAnswer == "Y" || userAnswer == "y") {
        printStartInfo()
        val tableOfValues = getTableOfValues()
        println("\nТаблица значений: ")
        printTableOfValues(tableOfValues)
        println("\nТочка интерполирования: $x")
        println("Степень многочлена: $n")
        val sortedTable = sortTableOfValues(tableOfValues).take(n + 1)
        println("\n${n + 1} ближайших узлов к точке интерполирования: ")
        printTableOfValues(sortedTable)
        val polynomialValue = calculateLagrangePolynomialValue(sortedTable)
        println("\nЗначение алгебраического интерполяционного многочлена в форме Лагранжа: $polynomialValue")
        println("Абсолютная фактическая погрешность: ${abs(polynomialValue - f(x))}")
        userAnswer = askForRestart()
    }
}