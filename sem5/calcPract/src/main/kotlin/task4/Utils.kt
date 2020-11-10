package task4

import task3p2.scanner

fun readUserAnswer(): String {
    print("Чтобы ввести новые данные ответьте 'Y' или 'y': ")
    return scanner.next()
}