#include <iostream>

using namespace std;

int pow(int number, int power)
{
    int result = 1;
    for (int i = 1; i < power; ++i)
    {
        result *= number;
    }
    return result;
}

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
    int number = 0;
    cout << "Enter a number to create the minimum of it's digits: ";
    cin >> number;
    int position = 0;
    int arr[maxSize];
    while (number > 0)
    {
        arr[position] = number % 10;
        number = number / 10;
        ++position;
    }
    quickSort(arr, 0, position - 1);
    int result = 0;
    int category = 10;
    for (int i = 0 ; i < position; ++i)
    {
        result = result + arr[i] * pow(category, position - i);
    }
    cout << "Result: " << result << endl;
    return 0;
}
