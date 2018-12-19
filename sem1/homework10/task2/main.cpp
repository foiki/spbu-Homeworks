#include "rabinKarpAlgorithm.hpp"

int main()
{
    string line = "";
    string subLine = "";
    cout << "Enter line and subLine: " << endl;
    cin >> line;
    cin >> subLine;
    bool result = rabinKarpAlgorithm(line, subLine);
    if (result == false)
    {
        cout << "Not found!" << endl;
    }
    return 0;
}
