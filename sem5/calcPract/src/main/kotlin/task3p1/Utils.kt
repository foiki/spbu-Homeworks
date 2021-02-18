package task3p1

import java.util.*
import kotlin.math.abs

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

fun invertedTable(list: List<Pair<Double, Double>>): List<Pair<Double, Double>> {
    val result = LinkedList<Pair<Double, Double>>()
    list.forEach {
        result.add(Pair(it.second, it.first))
    }
    return result
}

fun sortTableOfValues(list: List<Pair<Double, Double>>): List<Pair<Double, Double>> {
    return list.sortedWith(kotlin.Comparator { a, b ->
        when {
            abs(a.first - F) > abs(b.first - F) -> 1
            abs(a.first - F) < abs(b.first - F) -> -1
            else -> 0
        }})
}
