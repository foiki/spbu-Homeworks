#include <iostream>

using namespace std;

void swap(int *a, int *b)
{
    int c = *a;
    *a = *b;
    *b =c;
}

void read(int a[], long n)
{
    cout << "Enter the array members: ";
    for (long i = 0; i < n; ++i)
    {
        cin >> a[i];
    }
}

void print(int a[], long n)
{
    cout << "Result: ";
    for (long i = 0; i < n; ++i)
    {
        cout << a[i] << " ";
    }
    cout << endl;
}

void downHeap(int a[], long k, long n)
{
    int newElement = a[k];
    long child = 0;
    bool isUpper = false;
    while (k <= n / 2 && !isUpper)
    {
        child = 2 * k;
        if (child < n && a[child] < a[child + 1])
        {
            ++child;
        }
        if (newElement >= a[child])
        {
            isUpper = true;
        }
        else
        {
            a[k] = a[child];
            k = child;
        }
    }
    a[k] = newElement;
}

void heapSort(int array[], long size)
{
    for (long i = size / 2 - 1; i >= 0; --i)
    {
        downHeap(array, i, size - 1);
    }
    for (long i = size - 1; i > 0; --i)
    {
        swap(array[0], array[i]);
        downHeap(array, 0, i - 1);
    }
}

int main()
{
    const int maxSize = 1000000;
    long number = 0;
    cout << "Enter the size of array: ";
    cin >> number;
    int array[maxSize];
    read(array, number);
    heapSort(array, number);
    print(array, number);
    return 0;
}
