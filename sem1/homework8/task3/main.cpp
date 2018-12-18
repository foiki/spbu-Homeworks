#include <iostream>
#include <fstream>
#include "hashTable.hpp"

using namespace std;

int main()
{
    const int maxLength = 100000;
    HashTable *table = createHashTable();
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
    printResults(table);
    delete[] newWord;
    deleteTable(table);
    return 0;
}
