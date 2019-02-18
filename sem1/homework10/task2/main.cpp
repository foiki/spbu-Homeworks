#include "rabinKarpAlgorithm.hpp"

int main()
{
    char *string = new char[maxLength];
    char *subString = new char[maxLength];
    cout << "Enter line and subLine: " << endl;
    cin >> string;
    cin >> subString;
    bool result = rabinKarpAlgorithm(string, subString);
    if (result == false)
    {
        cout << "Not found!" << endl;
    }
    delete[] string;
    delete[] subString;
    return 0;
}
