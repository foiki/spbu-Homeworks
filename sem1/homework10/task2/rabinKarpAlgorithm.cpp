#include "rabinKarpAlgorithm.hpp"

int hashFunction(string line)
{
    int const prime = 13;
    int result = 0;
    long length = line.length();
    for (int i = 0; i < length; ++i)
    {
        result = ((result + line.c_str()[i]) * prime);
    }
    return result;
}

int rabinKarpAlgorithm(string line, string subLine)
{
    int subLineHash = hashFunction(subLine);
    long firstLength = line.length();
    long secondLength = subLine.length();
    for (int i = 0; i < firstLength - secondLength + 1; ++i)
    {
        string newSubLine = line.substr(i, i + secondLength - 1);
        long newSubLineHash = hashFunction(newSubLine);
        if (subLineHash == newSubLineHash)
        {
            if (newSubLine == subLine)
            {
                return i;
            }
        }
    }
    return -1;
}
