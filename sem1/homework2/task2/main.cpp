#include <iostream>

using namespace std;

void decomposition(int number, int array[], int left, int minimum = 0, int pointer = 0)
{
    if (left < 0 || minimum == number)
    {
        return;
    }
    array[pointer] = minimum;
    if (minimum != 0)
    {
        decomposition(number, array, left - minimum, minimum, pointer + 1);
    }
    decomposition(number, array, left - 1, minimum + 1, pointer);
    if (left == 0)
    {
        for (int i = 0; i < pointer; ++i)
        {
            cout << array[i] << " + ";
        }
        cout << array[pointer] << endl;
    }
}

int main(){
    const int maxSize = 1000000;
    int number = 0;
    cout << "Enter number N for the decomposition into summands: ";
    cin >> number;
    int array[maxSize];
    if (number <= 0)
    {
        cout << "Сannot be decomposed";
    }
    else
    {
        decomposition(number, array, number);
    }
    return 0;
}
