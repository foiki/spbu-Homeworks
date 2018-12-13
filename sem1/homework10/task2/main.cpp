#include <iostream>
#include "rabinKarpAlgorithm.hpp"

int main()
{
    string line = "";
    string subLine = "";
    cout << "Enter line and subLine: " << endl;
    cin >> line;
    cin >> subLine;
    int result = rabinKarpAlgorithm(line, subLine);
    cout << "Еhe position of the first occurrence: ";
    if (result != -1)
    {
        cout << result + 1;
    }
    else
    {
        cout << "Not found!";
    }
    cout << endl;
    return 0;
}
