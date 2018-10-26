#include <iostream>
#include <fstream>

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
    cout << " ";
}

int main()
{
    const int maxLength = 100000;
    ifstream fin("Text.txt");
    while (!fin.eof())
    {
        char *newWord = new char[maxLength];
        fin >> newWord;
        printingTheFirstOccurrences(newWord);
    }
    cout << endl;
    return 0;
}
