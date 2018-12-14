#include <fstream>
#include <iostream>
#include <string.h>

using namespace std;

const int maxSize = 100000;
const int size = 256;

void findLongestWord(char *wordWithMaxLength, ifstream &fin, long &maxLength, long &countOfWords)
{
    char *newWord = new char[maxSize];
    while (!fin.eof())
    {
        fin >> newWord;
        if (!fin.eof())
        {
            ++countOfWords;
            long newLength = strlen(newWord);
            if (newLength > maxLength)
            {
                strcpy(wordWithMaxLength, newWord);
                maxLength = newLength;
            }
        }
    }
    delete[] newWord;
}

void countNumberOfDifferentLetters(char *word, long length, bool *isLetterMet, int &numberOfLetters)
{
    for (int i = 0; i < length; ++i)
    {
        if (!isLetterMet[word[i]])
        {
            isLetterMet[word[i]] = true;
            ++numberOfLetters;
        }
    }
}

int main()
{
    ifstream fin("File.txt");
    if (!fin.is_open())
    {
        cout << "'File.txt' not found!" << endl;
        return 0;
    }
    long maxLength = 0;
    long countOfWords = 0;
    char *wordWithMaxLength = new char[maxSize];
    findLongestWord(wordWithMaxLength, fin, maxLength, countOfWords);
    fin.close();
    cout << "Number of words: " << countOfWords << endl;
    bool *isLetterMet = new bool[size]{false};
    int numberOfLetters = 0;
    countNumberOfDifferentLetters(wordWithMaxLength, maxLength, isLetterMet, numberOfLetters);
    cout << "Number of different letters in the longest word: " << numberOfLetters << endl;
    delete[] isLetterMet;
    delete[] wordWithMaxLength;
    return 0;
}

