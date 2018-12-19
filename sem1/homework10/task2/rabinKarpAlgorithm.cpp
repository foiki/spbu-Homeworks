#include "rabinKarpAlgorithm.hpp"

const int intMax = 2147483647;
const int prime = 13;

void countNewHashOfString(string line, long length, long &hash)
{
    hash -= (line[0] * prime) % intMax;
    hash += (line[length - 1] * prime) % intMax;
}

int hashFunction(string line)
{
    int result = 0;
    long length = line.length();
    for (int i = 0; i < length; ++i)
    {
        result = (result + line[i] * prime) % intMax;
    }
    return result;
}

bool rabinKarpAlgorithm(string line, string subLine)
{
    int subLineHash = hashFunction(subLine);
    long firstLength = line.length();
    long secondLength = subLine.length();
    cout << "First indices of all occurrences of a subline in a line:" << endl;
    bool isOccurrenceFound = false;
    string newSubLine = line.substr(0, secondLength);
    long newSubLineHash = hashFunction(newSubLine);
    for (long i = 0; i < firstLength - secondLength + 1; ++i)
    {
        if (i != 0)
        {
            countNewHashOfString(line.substr(i - 1, secondLength + 1), secondLength + 1, newSubLineHash);
        }
        newSubLine = line.substr(i, secondLength);
        if (subLineHash == newSubLineHash)
        {
            if (newSubLine == subLine)
            {
                cout << i << " ";
                isOccurrenceFound = true;
            }
        }
    }
    cout << endl;
    return isOccurrenceFound;
}
