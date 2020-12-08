package task6

import java.util.*
import kotlin.math.abs
import java.util.ArrayList

fun printSolutionTable() {
    println("\nТаблица значений точного решения:")
    print(String.format("%-6s", "| k"))
    print(String.format("%-25s", "| Xk"))
    println("| y(Xk)")
    for (k in -2..N) {
        val x = x0 + h * k
        print("| ${String.format("%-4s", k)}")
        print("| ${String.format("%-23s", x)}")
        println("| ${y(x)}")
    }
}

fun printTableValue(k: Int, x: Double, value: Double) {
    print("| ${String.format("%-4s", k)}")
    print("| ${String.format("%-23s", x)}")
    print("| ${String.format("%-23s", value)}")
    println("| ${abs(value - y(x))}")
}

fun printTailorDecomposition() {
    val result: LinkedList<Pair<Double, Double>> = LinkedList()
    println("\nЗначения решения приближенного через ряд Тейлора:")
    print(String.format("%-6s", "| k"))
    print(String.format("%-25s", "| Xk"))
    print(String.format("%-25s", "| T(Xk)"))
    println("|T(Xk) - y(Xk)|")
    for (k in -2..N) {
        val x = x0 + h * k
        val t = tailorDecompositionValue(x)
        result.add(Pair(x, t))
        printTableValue(k, x, t)
    }
    tailorValues = result
}

private fun getQ(firstPoints: List<Pair<Double, Double>>): MutableList<Double> {
    val points: MutableList<Double> = ArrayList()
    for (i in firstPoints.indices) {
        val x: Double = firstPoints[i].first
        val y: Double = firstPoints[i].second
        points.add(h * f(x, y))
    }
    return points
}

private fun updateQk(prevQ: List<Double>, curQ: List<Double>): MutableList<Double> {
    val newQ: MutableList<Double> = ArrayList(curQ)
    val size = prevQ.size
    val qK = prevQ[size - 1] - prevQ[size - 2]
    newQ.add(qK)
    return newQ
}

private fun getDifference(previous: List<Double>): MutableList<Double> {
    val difference: MutableList<Double> = ArrayList()
    var curElement: Double
    for (i in 1 until previous.size) {
        curElement = previous[i] - previous[i - 1]
        difference.add(curElement)
    }
    return difference
}

fun printAdams() {
    val points: MutableList<Pair<Double, Double>> = tailorValues.subList(0, 5)

    var y = points[4].second
    val q: MutableList<Double> = getQ(points)
    var dq: List<Double> = getDifference(q)
    var d2q: List<Double> = getDifference(dq)
    var d3q: List<Double> = getDifference(d2q)
    var d4q: List<Double> = getDifference(d3q)
    println("\nЗначения решения приближенного с помощью метода Адамса 4-го порядка:")
    print(String.format("%-6s", "| k"))
    print(String.format("%-25s", "| Xk"))
    print(String.format("%-25s", "| A(Xk)"))
    println("|A(Xk) - y(Xk)|")
    var qK: Double
    for (k in 5..(N + 2)) {
        val x = x0 + (k - 2) * h
        y += q[k - 1] + 1 / 2 * dq[k - 2] + 5 / 12 * d2q[k - 3] + 3 / 8 * d3q[k - 4] + 251 / 720 * d4q[k - 5]
        points.add(Pair(x, y))
        qK = h * f(x, y)
        q.add(qK)
        dq = updateQk(q, dq)
        d2q = updateQk(dq, d2q)
        d3q = updateQk(d2q, d3q)
        d4q = updateQk(d3q, d4q)
        printTableValue(k - 2, x, y)
    }
}

fun printRungeKutta() {
    var y = tailorValues[2].second
    var x = tailorValues[2].first
    val halfH = h /2
    println("\nЗначения решения приближенного с помощью метода Ренге-Кутта 4-го порядка:")
    print(String.format("%-6s", "| k"))
    print(String.format("%-25s", "| Xk"))
    print(String.format("%-25s", "| R(Xk)"))
    println("|R(Xk) - y(Xk)|")
    for (k in 1..N) {
        val k1 = h * f(x, y)
        val k2 = h * f(x + halfH, y + k1 / 2)
        val k3 = h * f(x + halfH, y + k2 / 2)
        val k4 = h * f(x + h, y + k3)
        y += (k1 + 2 * k2 + 2 * k3 + k4) / 6
        x = tailorValues[k + 2].first
        printTableValue(k, x, y)
    }
}

fun printEuler() {
    var y = tailorValues[2].second
    var x = tailorValues[2].first
    println("\nЗначения приближенного с помощью метода Эйлера решения:")
    print(String.format("%-6s", "| k"))
    print(String.format("%-25s", "| Xk"))
    print(String.format("%-25s", "| E(Xk)"))
    println("|E(Xk) - y(Xk)|")
    for (k in 1..N) {
        y += h * f(x, y)
        x = tailorValues[k + 2].first
        printTableValue(k, x, y)
    }
}

fun printEulerFirst() {
    var y = tailorValues[2].second
    var x = tailorValues[2].first
    val halfH = h /2
    println("\nЗначения приближенного с помощью I метода Эйлера решения:")
    print(String.format("%-6s", "| k"))
    print(String.format("%-25s", "| Xk"))
    print(String.format("%-25s", "| EI(Xk)"))
    println("|EI(Xk) - y(Xk)|")
    for (k in 1..N) {
        val y1 = y + halfH * f(x, y)
        y += h * f(x + halfH, y1)
        x = tailorValues[k + 2].first
        printTableValue(k, x, y)
    }
}

fun printEulerSecond() {
    var y = tailorValues[2].second
    var x = tailorValues[2].first
    val halfH = h /2
    println("\nЗначения приближенного с помощью II метода Эйлера решения:")
    print(String.format("%-6s", "| k"))
    print(String.format("%-25s", "| Xk"))
    print(String.format("%-25s", "| EII(Xk)"))
    println("|EII(Xk) - y(Xk)|")
    for (k in 1..N) {
        val y1 = y + h * f(x, y)
        val x1 = tailorValues[k + 2].first
        y += halfH * (f(x, y) + f(x1, y1))
        x = x1
        printTableValue(k, x, y)
    }
}