#include <iostream>
#include <fstream>

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
        for (int i = 0; i <= length; ++i)
        {
            if (newLine[i] != ' ' && newLine[i] != '\t' && int(newLine[i]) != 0)
            {
                ++notEmptyLines;
            }
        }
    }
    cout << "Count of not empty lines: " << notEmptyLines << endl;
    return 0;
}
