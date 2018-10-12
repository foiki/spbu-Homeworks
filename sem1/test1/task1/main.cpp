#include <iostream>

using namespace std;

void read(int a[], int n)
{
    for (int i = 0; i < n; ++i)
    {
        cin >> a[i];
    }
}

void swap(int *a, int *b)
{
    int c = *a;
    *a = *b;
    *b = c;
}

void quickSort(int array[], int begin, int end)
{
    int left = begin;
    int right = end;
    int mid = array[(left + right) / 2];
    while (left <= right)
    {
        while (array[left] > mid)
        {
            ++left;
        }
        while (array[right] < mid)
        {
            --right;
        }
        if (left <= right)
        {
            swap(array[left], array[right]);
            ++left;
            --right;
        }
        if (left < end)
        {
            quickSort(array, left, end);
        }
        if (right > begin)
        {
            quickSort(array, begin, right);
        }
    }
}

void connection(int a[], int b[], int minLenght, int Length)
{
    for (int i = 0; i < minLenght; ++i)
    {
        a[Length + i] = b[i];
    }
}

void print(int a[], int n)
{
    for (int i = 0; i < n; ++i)
    {
        cout << a[i] << " ";
    }
    cout << endl;
}

int main()
{
    const int maxSize = 1000000;
    int firstNumber = 0;
    int secondNumber = 0;
    cout << "Enter the number of notes in first stack: ";
    cin >> firstNumber;
    cout << "Enter the number of notes in second stack: ";
    cin >> secondNumber;
    int stackFirst[maxSize];
    int stackSecond[maxSize];
    cout << "Enter the set of first stack: ";
    read(stackFirst, firstNumber);
    cout << "Enter the set of second stack: ";
    read(stackSecond, secondNumber);
    if (firstNumber < secondNumber)
    {
        connection(stackSecond, stackFirst, firstNumber, secondNumber);
        quickSort(stackSecond, 0, firstNumber + secondNumber - 1);
        print (stackSecond, firstNumber + secondNumber);
    }
    else
    {
        connection(stackFirst, stackSecond, secondNumber, firstNumber);
        quickSort(stackFirst, 0, firstNumber + secondNumber - 1);
        print (stackFirst, firstNumber + secondNumber);
    }
    return 0;
}
