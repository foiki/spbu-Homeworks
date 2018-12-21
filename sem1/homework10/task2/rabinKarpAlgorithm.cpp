#include "rabinKarpAlgorithm.hpp"

void countNewHashOfString(char *line, long length, long &hash)
{
    hash -= (line[0] * prime) % intMax;
    hash += (line[length - 1] * prime) % intMax;
}

int hashFunction(char *line)
{
    int result = 0;
    long length = strlen(line);
    for (int i = 0; i < length; ++i)
    {
        result = (result + line[i] * prime) % intMax;
    }
    return result;
}

char *subLineCopy(char *to, char *from, long begin, long number)
{
    long j = 0;
    for (long i = begin; i < begin + number; ++i)
    {
        to[j] = from[i];
        ++j;
    }
    return to;
}

bool isEqual(char *string, char *subString)
{
    long length = strlen(subString);
    for (int i = 0; i < length; ++i)
    {
        if (string[i] != subString[i])
        {
            return false;
        }
    }
    return true;
}

bool rabinKarpAlgorithm(char *string, char *subString)
{
    int subLineHash = hashFunction(subString);
    long firstLength = strlen(string);
    long secondLength = strlen(subString);
    cout << "First indices of all occurrences of a subline in a line:" << endl;
    bool isOccurrenceFound = false;
    long newSubLineHash = 0;
    for (long i = 0; i < firstLength - secondLength + 1; ++i)
    {
        char *newSubLine = new char[maxLength];
        if (i == 0)
        {
            newSubLine = subLineCopy(newSubLine, string, 0, secondLength);
            newSubLineHash = hashFunction(newSubLine);
        }
        else
        {
            countNewHashOfString(subLineCopy(newSubLine, string, i - 1, secondLength + 1), secondLength + 1, newSubLineHash);
        }
        newSubLine = subLineCopy(newSubLine, string, i, secondLength);
        if (subLineHash == newSubLineHash && isEqual(newSubLine, subString))
        {
            isOccurrenceFound = true;
            cout << i << " ";
        }
        delete[] newSubLine;
    }
    cout << endl;
    return isOccurrenceFound;
}
