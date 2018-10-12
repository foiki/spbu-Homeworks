#include <iostream>
#include <ctime>

using namespace std;

void randomMembers(int a[], int n)
{
    for (int i = 0; i < n; ++i)
    {
        a[i] = rand() % 33 + 11;
    }
}

void read(int a[], int n)
{
    for (int i = 0; i < n; ++i)
    {
        cin >> a[i];
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

void insertionSort(int a[], int size)
{
    for (int i = 3; i < size; i += 2)
    {
        int key = a[i];
        int j = i - 2;
        while (j >= 0 && a[j] > key)
        {
            a[j + 2] = a[j];
            j -= 2;
        }
        a[j + 2] = key;
    }
}

int main()
{
    srand(time(0));
    const int maxSize = 1000000;
    int array[maxSize];
    int number = 0;
    cout << "Enter the size of array: ";
    cin >> number;
    cout << "If you want to enter the members of array by yourself, enter '0', if you want to generate them, enter '1'" << endl;
    bool random = false;
    cin >> random;
    if (random)
    {
        randomMembers(array, number);
        cout << "Generated Array: " << endl;
        print(array, number);
    }
    else
    {
        cout << "Enter the array members: ";
        read(array, number);
        cout << "Entered Array: " << endl;
        print(array, number);
    }
    insertionSort(array, number);
    cout << "Result: " << endl;
    print(array, number);
    return 0;
}
