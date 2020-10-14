package task3p2

import java.util.*

fun getTableOfValues(): LinkedList<Pair<Double, Double>> {
    val result = LinkedList<Pair<Double, Double>>()
    for (i in 0..m) {
        val newValue = a + i * h
        result.add(Pair(newValue, f(newValue)))
    }
    return result
}

fun printTableInfo(tableOfValues: List<Pair<Double, Double>>) {
    println("\nТаблица значений: ")
    printTableOfValues(tableOfValues)
}

fun printTableOfValues(list: List<Pair<Double, Double>>) {
    var counter = 0
    list.forEach {
        println("$counter) ${it.first} ${it.second}")
        ++counter
    }
}

fun readUserAnswer(): String {
    print("Чтобы ввести новые данные ответьте 'Y' или 'y': ")
    return scanner.next()
}