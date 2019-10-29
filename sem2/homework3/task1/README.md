* Задача: Реализовать класс для работы с хеш-таблицей (на списках). Общение с пользователем должно происходит в интерактивном режиме: добавить значение в хеш-таблицу, удалить значение из хеш-таблицы, поиск значения в хеш-таблице, показать статистику по хеш-таблице (общее число ячеек, load factor, число конфликтов, максимальная длина списка в конфликтных ячейках и т.п.), заполнить хеш-таблицу содержимым файла, выбрать хеш-функцию для подсчета хеша (из заранее заданных в коде). Смена хэш-функции должна происходить во время работы программы, в класс используемая хеш-функция должна передаваться из клиентского кода.

* Ход решения:
1) Создаем интерфейс HashFunction для вычисления хеша строки
    *  Реализуем его в двух классах: PolynomialHash и SumHash
2) Создаем класс, реализующий хеш-таблицу
3) Метод remove бросает исключение ElementDoesNotExist, если пользователь пытается удалить из таблицы несуществующий элемент
4) Реализуем main и тесты к хеш-таблице