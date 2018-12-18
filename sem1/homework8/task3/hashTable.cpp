#include <iostream>
#include "string.hpp"
#include "hashTable.hpp"

using namespace std;

const int size = 512;

HashTable *createHashTable()
{
    HashTable *newTable = new HashTable;
    newTable->bucket = new HashTableArray*[size] {nullptr};
    newTable->countOfWords = 0;
    return newTable;
}

void deleteTable(HashTable *table)
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
                    delete table->bucket[i];
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

void addNewWord(HashTable *table, String *newWord, int position, int countOfTests)
{
    HashTableArray *newElement = new HashTableArray{newWord, 1, countOfTests};
    table->bucket[position] = newElement;
}

HashTableArray *quadraticSample(HashTable *table, String *string, int position)
{
    int attemtp = 0;
    int shift = attemtp * attemtp;
    int countOfTests = 2;
    int newPosition = (position + shift) % size;
    HashTableArray *current = table->bucket[newPosition];
    while (current && compare(current->word, string))
    {
        ++attemtp;
        ++countOfTests;
        shift = attemtp * attemtp;
        newPosition = (position + shift) % size;
        current = table->bucket[newPosition];
    }
    return current;
}

bool exist(HashTable *table, String *string, int position)
{
    return quadraticSample(table, string, position);
}

void newWordProcessing(HashTable *table, char *newWord)
{
    String *newString = charToString(newWord);
    int position = hashFunction(newString);
    if (exist(table, newString, position))
    {
        HashTableArray *current = quadraticSample(table, newString, position);
        ++current->countOfSameWords;
        deleteString(newString);
    }
    else
    {
        int attemtp = 0;
        int shift = attemtp * attemtp;
        int countOfTests = 2;
        int newPosition = (position + shift) % size;
        HashTableArray *current = table->bucket[newPosition];
        while (current)
        {
            ++attemtp;
            ++countOfTests;
            shift = attemtp * attemtp;
            newPosition = (position + shift) % size;
            current = table->bucket[newPosition];
        }
        addNewWord(table, newString, newPosition, countOfTests);
        ++table->countOfWords;
    }
}

int maximumTestes(HashTable *table)
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

void wordsWithMaxTests(HashTable *table, int maxTestes)
{
    for (int i = 0; i < size; ++i)
    {
        if (table->bucket[i] && table->bucket[i]->countOfTests == maxTestes)
        {
            printString(table->bucket[i]->word);
        }
    }
}

double averageValueOfTests(HashTable *table)
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

void printResults(HashTable *table)
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
