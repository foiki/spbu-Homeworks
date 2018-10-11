#include <iostream>

using namespace std;

int main()
{
    const int maxLine = 10000;
    char string[maxLine];
    char substring[maxLine];
    int count = 0;
    cout << "Enter string and substring: ";
    cin >> string >> substring;
    long lengthS = strlen(string);
    long lengthS1 = strlen(substring);
    for (int i = 0; i <= lengthS - lengthS1; ++i)
    {
        bool areDifference = false;
        int pointerFirst = i;
        int pointerSecond = 0;
        while (!areDifference && pointerSecond < lengthS1)
        {
            if (string[pointerFirst] != substring[pointerSecond])
            {
                areDifference = true;
            }
            ++pointerFirst;
            ++pointerSecond;
        }
        if (!areDifference)
        {
            ++count;
        }
    }
    cout << "The number of occurrences: ";
    cout << count << endl;
    return 0;
}
