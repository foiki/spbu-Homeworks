#include <iostream>

using namespace std;

void quickSort(int array[], int begin, int end)
{
    int left = begin;
    int right = end;
    int mid = array[(left + right) / 2];
    while (left <= right)
    {
        while (array[left] < mid)
        {
            ++left;
        }
        while (array[right] > mid)
        {
            --right;
        }
        if (left <= right)
        {
            int c = array[left];
            array[left] = array[right];
            array[right] = c;
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

int main()
{
    const int maxSize = 1000000;
    int arraySize = 0;
    cout << "Enter the array size: ";
    cin >> arraySize;
    int arr[maxSize];
    cout << "Enter the members of array: ";
    for (int i = 0; i < arraySize; ++i)
    {
        cin >> arr[i];
    }
    quickSort(arr, 0, arraySize - 1);
    int pointer = arraySize - 1;
    int maximum = 0;
    bool isFound = false;
    while (!isFound && pointer > 0)
    {
        if (arr[pointer] == arr[pointer - 1])
        {
            maximum = arr[pointer];
            isFound = true;
        }
        --pointer;
    }
    if (isFound)
    {
        cout << maximum << endl;
    }
    else
    {
        cout << "Not Found!" << endl;
    }
}
