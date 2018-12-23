#include "rabinKarpAlgorithm.hpp"

void countNewHashOfString(char *line, long begin, long length, long &hash)
{
    hash -= (line[begin] * prime) % intMax;
    hash += (line[begin + length] * prime) % intMax;
}

int hashFunction(char *line, long begin, long length)
{
    int result = 0;
    for (long i = begin; i < begin + length; ++i)
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

bool isEqual(char *subString, char *string, long begin, long length)
{
    for (int i = 0; i < length; ++i)
    {
        if (string[i + begin] != subString[i])
        {
            return false;
        }
    }
    return true;
}

bool rabinKarpAlgorithm(char *string, char *subString)
{
    long firstLength = strlen(string);
    long secondLength = strlen(subString);
    int subLineHash = hashFunction(subString, 0, secondLength);
    cout << "First indices of all occurrences of a subline in a line:" << endl;
    bool isOccurrenceFound = false;
    long newSubLineHash = 0;
    for (long i = 0; i < firstLength - secondLength + 1; ++i)
    {
        if (i == 0)
        {
            newSubLineHash = hashFunction(string, 0, secondLength);
        }
        else
        {
            countNewHashOfString(string, i - 1, secondLength, newSubLineHash);
        }
        if (subLineHash == newSubLineHash)
        {
            if (isEqual(subString, string, i, secondLength))
            {
                isOccurrenceFound = true;
                cout << i << " ";
            }
        }
    }
    cout << endl;
    return isOccurrenceFound;
}
