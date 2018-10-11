#include <iostream>

using namespace std;

void reverse(int array[], int begin, int end)
{
    int i = 0;
    while (i < end - begin - i)
    {
        int c = 0;
        c = array[i + begin];
        array[i + begin] = array[end - i];
        array[end - i] = c;
        ++i;
    }
}

int main()
{
    const int arraySize = 1000000;
    int n = 0;
    int m = 0;
    cout << "Enter the sizes of the first and second segments: ";
    cin >> m >> n;
    int array[arraySize];
    cout << "Enter the members of the array: ";
    for (int i = 1; i < n + m + 1; ++i)
    {
        cin >> array[i];
    }
    reverse(array, 1, m);
    reverse(array, m + 1, m + n);
    reverse(array, 1, m + n);
    cout << "Result: ";
    for (int i = 1; i < n + m + 1; ++i)
    {
        cout << array[i] << " ";
    }
    cout << endl;
    return 0;
}
