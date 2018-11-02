#include <iostream>
#include <fstream>
#include <string.h>

using namespace std;

const int maxSize = 100000;

void printingTheFirstOccurrences(char *newWord)
{
    bool *isMet = new bool[maxSize] {false};
    long newLength = strlen(newWord);
    for (int i = 0; i < newLength; ++i)
    {
        if (!isMet[(int)newWord[i]])
        {
            cout << newWord[i];
            isMet[(int)newWord[i]] = true;
        }
    }
    delete[] isMet;
    cout << " ";
}

int main()
{
    const int maxLength = 100000;
    ifstream fin("Text.txt");
    if (fin.is_open()){
        while (!fin.eof())
        {
            char *newWord = new char[maxLength];
            fin >> newWord;
            printingTheFirstOccurrences(newWord);
        }
        cout << endl;
    }
    else
    {
        cout << "File not found!" << endl;
    }
    return 0;
}
