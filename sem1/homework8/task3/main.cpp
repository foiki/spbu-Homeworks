#include <iostream>
#include <fstream>
#include "hashTable.hpp"

using namespace std;

int main()
{
    const int size = 512;
    const int maxLength = 100000;
    hashTable *table = createHashTable();
    ifstream fin("File.txt");
    char *newWord = new char[maxLength];
    if (fin.is_open())
    {
        while (!fin.eof())
        {
            fin >> newWord;
            if (!fin.eof())
            {
                newWordProcessing(table, newWord);
            }
            
        }
    }
    else
    {
        cout << "'File.txt' not found!" << endl;
    }
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
    delete[] newWord;
    deleteTable(table);
    return 0;
}
