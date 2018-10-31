#include <iostream>
#include <fstream>
#include <string.h>

using namespace std;

int main()
{
    const int maxSize = 1000000;
    ifstream fin("File.txt");
    char *newLine = new char[maxSize];
    int notEmptyLines = 0;
    while (!fin.eof())
    {
        fin.getline(newLine, maxSize, '\n');
        long length = strlen(newLine);
        int i = 0;
        bool isEmpty = true;
        while (i < length && isEmpty)
        {
            if (newLine[i] != ' ' && newLine[i] != '\t')
            {
                ++notEmptyLines;
                isEmpty = false;
            }
            ++i;
        }
    }
    delete[] newLine;
    cout << "Count of not empty lines: " << notEmptyLines << endl;
    return 0;
}
