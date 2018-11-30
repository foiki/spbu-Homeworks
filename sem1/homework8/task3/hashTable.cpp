#include <iostream>
#include "string.hpp"
#include "hashTable.hpp"

using namespace std;

const int size = 512;

hashTable *createHashTable()
{
    hashTable *newTable = new hashTable{nullptr, 0};
    newTable->bucket = new hashTableArray*[size]{nullptr};
    return newTable;
}

void deleteTable(hashTable *table)
{
    if (table)
    {
        if (table->bucket)
        {
            for (int i = 0; i < size; ++i)
            {
                if (table->bucket[i])
                {
                    deleteString(table->bucket[i]->word);
                }
            }
            delete[] table->bucket;
        }
        delete table;
    }
}

int hashFunction(String *string)
{
    int const prime = 13;
    int result = 0;
    for (int i = 0; i <= string->length; ++i)
    {
        result = ((result + string->elements[i]) * prime) % size;
    }
    return result;
}

void addNewWord(hashTable *table, String *newWord, int position, int countOfTests)
{
    hashTableArray *newElement = new hashTableArray{clone(newWord), 1, countOfTests};
    table->bucket[position] = newElement;
}

void newWordProcessing(hashTable *table, char *newWord)
{
    String *newString = charToString(newWord);
    int position = hashFunction(newString);
    if (!table->bucket[position])
    {
        addNewWord(table, newString, position, 1);
        ++table->countOfWords;
    }
    else
    {
        if (!compare(table->bucket[position]->word, newString))
        {
            ++table->bucket[position]->countOfSameWords;
            deleteString(newString);
        }
        else
        {
            int attemtp = 1;
            int shift = attemtp * attemtp;
            int countOfTests = 2;
            int newPosition = (position + shift) % size;
            hashTableArray *current = table->bucket[newPosition];
            while (current && compare(current->word, newString))
            {
                ++attemtp;
                ++countOfTests;
                shift = attemtp * attemtp;
                newPosition = (position + shift) % size;
                current = table->bucket[newPosition];
            }
            if (!current)
            {
                addNewWord(table, newString, newPosition, countOfTests);
            }
            else
            {
                ++current->countOfSameWords;
            }
        }
    }
}

int maximumTestes(hashTable *table)
{
    int result = 0;
    if (table && table->bucket)
    {
        for (int i = 0; i < size; ++i)
        {
            if (table->bucket[i])
            {
                result = max(result, table->bucket[i]->countOfTests);
            }
        }
        return result;
    }
    return 0;
}

void wordsWithMaxTests(hashTable *table, int maxTestes)
{
    for (int i = 0; i < size; ++i)
    {
        if (table->bucket[i] && table->bucket[i]->countOfTests == maxTestes)
        {
            printString(table->bucket[i]->word);
        }
    }
}

double averageValueOfTests(hashTable *table)
{
    int sum = 0;
    if (table && table->bucket)
    {
        for (int i = 0; i < size; ++i)
        {
            if (table->bucket[i])
            {
                sum += table->bucket[i]->countOfTests;
            }
        }
        return (double)sum / table->countOfWords;
    }
    return 0.0;
}

void printResults(hashTable *table)
{
    for (int i = 0; i < size; ++i)
    {
        if (table->bucket[i])
        {
            cout << table->bucket[i]->countOfSameWords << " ";
            printString(table->bucket[i]->word);
        }
    }
    cout << "Load Factor: " << (double)table->countOfWords / size << endl;
    int maxTestes = maximumTestes(table);
    cout << "Maximum of testes: " << maxTestes << endl;
    cout << "Words with maximum tests: " << endl;
    wordsWithMaxTests(table, maxTestes);
    cout << "Average value of tests" << " " << averageValueOfTests(table) << endl;
    cout << "Total added " << table->countOfWords << " words" << endl;
    cout << "Сells remained free: " << size - table->countOfWords << endl;
}
