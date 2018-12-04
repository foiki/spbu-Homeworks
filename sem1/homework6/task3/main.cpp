#include <iostream>

using namespace std;

void outputDegrees(int array[], int number)
{
    for (int i = 0; i < number - 1; i++)
    {
        if (array[i] != 0)
        {
            int countOfSpaces = 1;
            int coefficient = 0;
            if (i == 0)
            {
                if (array[0] < 0)
                {
                    ++countOfSpaces;
                    coefficient = -1 * array[i];
                }
                else
                {
                    coefficient = array[i];
                }
            }
            if (array[i] < 0 && i != 0)
            {
                coefficient = -1 * array[i];
                countOfSpaces += 2;
            }
            else if (i != 0)
            {
                coefficient = array[i];
                countOfSpaces += 2;
            }
            if (coefficient == 1)
            {
                coefficient = 0;
            }
            while (coefficient > 0)
            {
                coefficient = coefficient / 10;
                ++countOfSpaces;
            }
            for (int j = 0; j < countOfSpaces; ++j)
            {
                cout << " ";
            }
            if ((array[i] != 0) && (i != number - 1))
            {
                cout << number - i - 1;
            }
        }
    }
    cout << endl;
}

void outputBasis(int array[], int number)
{
    if (number == 1)
    {
        cout << array[0];
    }
    else
    {
        if (array[0] < 0)
        {
            cout << "-";
            array[0] = -1 * array[0];
        }
        if (array[0] != 1)
        {
            cout << array[0] << "x";
        }
        else
        {
            cout << "x";
        }
    }
    for (int i = 1; i < number; ++i)
    {
        if (array[i] != 0)
        {
            int previousDegree = 0;
            int j = i - 1;
            while (previousDegree == 0)
            {
                if (array[j] != 0)
                {
                    previousDegree = number - j;
                }
                --j;
            }
            while (previousDegree > 0)
            {
                cout << " ";
                previousDegree /= 10;
            }
            if (array[i] < 0)
            {
                cout << "- ";
                array[i] = -1 * array[i];
            }
            else
            {
                cout << "+ ";
            }
            if (array[i] != 1 && i != number - 1)
            {
                cout << array[i] << "x";
            }
            else if (i != number - 1)
            {
                cout << "x";
            }
            if (i == number - 1)
            {
                cout << array[i];
            }
        }
    }
    
    cout << endl << endl;
}

int main()
{
    cout << "Enter the number of coefficients: " << endl;
    int number = 0;
    cin >> number;
    int *array = new int[number];
    cout << "Enter the coefficients: " << endl;
    for (int i = 0; i < number; ++i)
    {
        cin >> array[i];
    }
    cout << endl;
    outputDegrees(array, number);
    outputBasis(array, number);
    delete[] array;
    return 0;
}
