package task5

import kotlin.math.*

fun compositeGauss() {
    var result = 0.0
    val h = (B1 - A1) / m
    val halfOfH = h / 2
    for (i in 0 until m) {
        val z = A1 + i * h + halfOfH
        val x1 = halfOfH * 1 / sqrt(3.0) + z
        val x2 = halfOfH * (-1) / sqrt(3.0) + z
        result += function.value(x1) + function.value(x2)
    }
    result *= halfOfH
    println("\nСоставная КФ Гаусса с 2-мя узлами: $result")
    println("Абсолютная погрешность: ${abs(result - J)}")
}

private fun f(x: Double, k: Int): Double {
    return p1(x) * x.pow(k)
}

fun averageRectangles(K : Int): Double {
    var value = 0.0
    val number = 10000
    val diff = (B1 - A1) / number
    val a = A1 + diff / 2
    for (k in 1..number) {
        value += f(a + (k - 1) * diff, K)
    }
    value *= diff
    return value
}

fun printOrthogonal(a1: Double, a2: Double) {
    print("Ортогональный многочлен: x^2 ")
    if (a1 >= 0) {
        print("+ $a1 * x ")
    } else {
        print("$a1 * x ")
    }
    if (a2 >= 0) {
        println("+ $a2")
    } else {
        println("$a2")
    }
}

fun gauss() {
    println("\nКФ типа Гаусса с 2-мя узлами:")
    val u0 = averageRectangles(0)
    val u1 = averageRectangles(1)
    val u2 = averageRectangles(2)
    val u3 = averageRectangles(3)
    println("Моменты весовой функции: \n0) $u0\n1) $u1\n2) $u2\n3) $u3")
    val a1 = (u0 * u3 - u2 * u1) / (u1 * u1 - u2 * u0)
    val a2 = (u2 * u2 - u3 * u1) / (u1 * u1 - u2 * u0)
    printOrthogonal(a1, a2)
    val d = sqrt((a1).pow(2) - 4 * a2)
    val x1 = - (a1 + d) / 2
    val x2 = - (a1 - d) / 2
    println("Узлы: \nx1 = $x1\nx2 = $x2")
    val node1 = (u1 - x2 * u0) / (x1 - x2)
    val node2 = (u1 - x1 * u0) / (x2 - x1)
    println("Коэффициенты КФ: \nA1 = $node1\nA2 = $node2")
    val sum = node1 + node2
    println("Проверка на коэффиценты(u0 - A1 - A2): ${abs(sum - u0)}")
    val result = node1 * f1(x1) + node2 * f1(x2)
    println("Вычисление с помощьюе КФ типа Гаусса с 2-мя узлами: $result")
    println("Абсолютная погрешность: ${abs(result - J)}")
}

fun meller() {
    println("\nКФ Мелера с $N узлами:")
    println("Функция: f(x) = 1 / (1 + x^2)")
    println("Точнное значение: $J1")
    var result = 0.0
    val aK = PI / N
    println("Коээфиценты(PI/$N): $aK")
    for (k in 1..N) {
        val x: Double = cos(PI * (2 * k - 1) / (2 * N))
        val value = f2(x)
        println(java.lang.String.format("x%d = %16.12f\tf(x%d) = %16.12f", k, x, k, value))
        result += value
    }
    result *= aK
    println("Результат: $result")
    println("Абсолютная погрешность: ${abs(result - J1)}")
}