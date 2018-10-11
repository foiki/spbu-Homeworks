#include <iostream>
#include <string.h>

using namespace std;

int main()
{
    const int maxLength = 100000;
    char str[maxLength];
    cout << "Enter a word to check for palindrome: ";
    cin >> str;
    long long pointerBegin = 0;
    long long pointerEnd = strlen(str) - 1;
    bool areDifferent = false;
    while (!areDifferent && pointerBegin < pointerEnd - pointerBegin)
    {
        if (str[pointerBegin] != str[pointerEnd - pointerBegin])
        {
            areDifferent = true;
        }
        ++pointerBegin;
    }
    if (!areDifferent)
    {
        cout << "Palindrome" << endl;
    }
    else
    {
        cout << "Not a palindrome" << endl;
    }
    return 0;
}
