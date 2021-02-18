package task3

import de.vandermeer.asciitable.AsciiTable
import java.util.*

fun print(dfa: Dfa) {
    val states = dfa.states.toTypedArray()
    val symbols = dfa.symbols.toTypedArray()

    val table = AsciiTable()
    val row: MutableList<String> = LinkedList()
    symbols.forEach {
        row.add(it)
    }
    table.addRow(row)

    for (i in states.indices)
    {
        row.clear()
        symbols.forEach {
            val checkState = dfa.get(states[i], it)
            if (checkState == null) {
                row.add("".prependIndent(states[i].getName()))
            } else {
                row.add(checkState.getName().prependIndent(states[i].getName()))
            }
        }
        table.addRow(row)
    }

    println(table.render())
    println()
}

fun print(values: Array<BooleanArray> , headers: Array<State>) {
    val table = AsciiTable()
    val row: MutableList<String> = LinkedList()
    headers.forEach {
        row.add(it.getName())
    }
    table.addRow(row)

    for (i in headers.indices) {
        row.clear()
        for (j in values[0].indices) {
            if (values[i][j]) {
                row.add("+".prependIndent(headers[i].getName()))
            } else {
                row.add("-".prependIndent(headers[i].getName()))
            }
        }
        table.addRow(row)
    }

    println(table.render())
}