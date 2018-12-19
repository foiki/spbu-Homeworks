#include "rabinKarpAlgorithm.hpp"

const int intMax = 2147483647;

int hashFunction(string line)
{
    int const prime = 13;
    int result = 0;
    long length = line.length();
    for (int i = 0; i < length; ++i)
    {
        result = ((result + line[i]) * prime) % intMax;
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
    for (long i = 0; i < firstLength - secondLength + 1; ++i)
    {
        string newSubLine = line.substr(i, secondLength);
        long newSubLineHash = hashFunction(newSubLine);
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
