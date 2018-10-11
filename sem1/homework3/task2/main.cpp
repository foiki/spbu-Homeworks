#include <iostream>

using namespace std;

void swap(char *a, char *b)
{
    char *c = a;
    *a = *b;
    *b = *c;
}

void quickSort(char string[], long begin, long end)
{
    long left = begin;
    long right = end;
    char mid = string[(left + right) / 2];
    while (left <= right)
    {
        while (string[left] < mid)
        {
            ++left;
        }
        while (string[right] > mid)
        {
            --right;
        }
        if (left <= right)
        {
            swap(string[left], string[right]);
            ++left;
            --right;
        }
        if (left < end)
        {
            quickSort(string, left, end);
        }
        if (right > begin)
        {
            quickSort(string, begin, right);
        }
    }
}

int main()
{
    const int maxSize = 1000000;
    char stringFirst[maxSize];
    char stringSecond[maxSize];
    cout << "Enter two strigns: ";
    cin >> stringFirst >> stringSecond;
    long lengthFirst = strlen(stringFirst);
    long lengthSecond = strlen(stringFirst);
    quickSort(stringFirst, 0, lengthFirst - 1);
    quickSort(stringSecond, 0, lengthSecond - 1);
    bool areEqual = true;
    if (lengthFirst == lengthSecond)
    {
        int pointer = 0;
        while(areEqual && pointer < lengthFirst)
        {
            if (stringFirst[pointer] != stringSecond[pointer])
            {
                areEqual = false;
            }
            ++pointer;
        }
    }
    else
    {
        areEqual = false;
    }
    if (areEqual)
    {
        cout << "Yes, it's possible";
    }
    else
    {
        cout << "No, it's not possible";
    }
}
