2.3
Дан массив размерностью N x N, N - нечетное число. Вывести элементы массива при обходе его по спирали, начиная с центра. Для решения задачи разработать интерфейс Выводилка с методом вывести(), реализовать на его основе два класса, осуществляющих либо вывод на консоль либо в файл. Написать программу, которая по желанию пользователя выбирает реализацию Выводилки и выводит массив.

Решение:
-Считываем размер массива и его элементы с консоли

-Считываем с консоли куда нужно вывести массив: в файл или в консоль

-Проверяем корректность введенного массива, если он задан неправильно, выдаем исключение, иначе записываем в строку все элементы массива, обходя его по спирали

-Выводим получившуюся строку в файл или в консоль

-Если запрошен вывод в файл, обрабатываем искючение, что файл не найден