package utils

import java.text.DecimalFormat
import kotlin.math.max

class TablePrinter(table: Array<Array<Double>>, digitsAfterDot: Int) {

    private var resultTable: Array<Array<String>> = arrayOf()
    private var headers: Array<String>? = null
    private var columnHeaders: Array<String>? = null
    private var maxGapLengths: Array<Int> = Array(table[0].size) {0}
    private var columnHeadersMaxGapSize = 0
    private var decimalFormat = DecimalFormat("#.##########")

    constructor(table: Array<Array<Double>>) : this(table, 10)

    init {
        initFormat(digitsAfterDot)
        for (i in table.indices) {
            var array: Array<String> = arrayOf()
            for (j in table[i].indices) {
                val value = decimalFormat.format(table[i][j])
                maxGapLengths[j] = max(maxGapLengths[j], value.length)
                array += value
            }
            resultTable += array
        }
    }

    fun initFormat(digitsAfterDot: Int) {
        var format = "#."
        for (i in 0 until digitsAfterDot) {
            format += "#"
        }
        decimalFormat = DecimalFormat(format)
    }
    
    fun addLineHeader(headers: Array<String>) {
        this.headers = headers
        for (i in headers.indices) {
            maxGapLengths[i] = max(maxGapLengths[i], headers[i].length)
        }
    }

    fun addColumnHeader(headers: Array<String>) {
        this.columnHeaders = headers
        for (i in columnHeaders!!.indices) {
            columnHeadersMaxGapSize = max(columnHeadersMaxGapSize, columnHeaders!![i].length)
        }
    }

    fun print() {
        if (headers != null) {
            if (columnHeaders != null) {
                print(MAGENTA + "|${String.format("%-${columnHeadersMaxGapSize + CYAN.length + 1}s", "$CYAN ${columnHeaders!![0]}")} ")
            }
            for (i in headers!!.indices) {
                print(MAGENTA + "|${String.format("%-${maxGapLengths[i] + CYAN.length + 1}s", "$CYAN ${headers!![i]}")} ")
            }
            println("$MAGENTA |")
        }
        for (i in resultTable.indices) {
            if (columnHeaders != null) {
                print(MAGENTA + "|${String.format("%-${columnHeadersMaxGapSize + CYAN.length + 1}s", 
                    "$CYAN ${columnHeaders!![i + 1]}")} ")
            }
            for (j in resultTable[i].indices) {
                print(MAGENTA + "|${String.format("%-${maxGapLengths[j] + CYAN.length + 1}s", "$CYAN ${resultTable[i][j]}")} ")
            }
            println("$MAGENTA |")
        }
    }
}