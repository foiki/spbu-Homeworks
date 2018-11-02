#include <iostream>
#include <string.h>

using namespace std;

int countOfSpaces(char *line, long length)
{
    int result = 0;
    for (int i = 1; i < length; ++i)
    {
        if (line[i] == ' ' && line[i - 1] != ' ')
        {
            ++result;
        }
    }
    return result;
}

int main()
{
    const int maxLength = 1000000;
    cout << "Enter the line: " << endl;
    char *line = new char[maxLength];
    cin.getline(line, maxLength);
    long length = strlen(line);
    cout << "Enter requested length: " << endl;
    long newLength = 0;
    cin >> newLength;
    int spacesCount = countOfSpaces(line, length);
    long difference = (newLength - length) % spacesCount;
    long addToEachSpace = (newLength - length) / spacesCount;
    int *longOfEachSpace = new int[spacesCount] {0};
    for (int i = 0; i < spacesCount; ++i)
    {
        longOfEachSpace[i] += addToEachSpace + 1;
        if (difference > 0)
        {
            ++longOfEachSpace[i];
            --difference;
        }
    }
    int pointer = 0;
    for (int i = 0; i < length; ++i)
    {
        if (line[i] == ' ')
        {
            for(int j = 0; j < longOfEachSpace[pointer]; ++j)
            {
                cout << ' ';
            }
            ++pointer;
        }
        else
        {
            cout << line[i];
        }
    }
    cout << endl;
    delete[] longOfEachSpace;
    delete[] line;
    return 0;
}
