Задача: Разработать интерфейс, представляющий структуру данных "стек". На его основе реализовать стек двумя разными способами. С его помощью реализовать стековый калькулятор для подсчета арифметических выражений.

Решение:
Создаем два класса, реализующих стркуктуру стек:
1. На основе списка
2. На основе массива

Создаем класс калькулятор, в котором реализуем метод calculate:
1. Разбиваем считанное выражение по пробелам на массив строк
2. Проходим по этому массиву:
	а. Если элемент - число, то добавляем его в стек
	б. Если элемент - знак операции, то выполняем эту операцию с двумя последними элементами стека и добавляем результат обратно в стек(обрабатываем исключение, если выражение некорректно)
3. Если выражение было корректно, в стеке останется один элемент, который и является ответом