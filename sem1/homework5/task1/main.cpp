#include <iostream>

using namespace std;

const int maxSize = 1000;

void fillingTheArray(int array[maxSize][maxSize], int number)
{
    int currentLine = number / 2;
    int currentCollumn = number / 2;
    array[currentLine][currentCollumn] = 1;
    currentCollumn -= 1;
    int currentNumber = 2;
    int currentSideSize = 1;
    while (currentNumber <= number*number)
    {
        currentSideSize += 2;
        for (int i = 1; i < currentSideSize; ++i)
        {
            array[currentLine][currentCollumn] = currentNumber;
            ++currentLine;
            ++currentNumber;
        }
        --currentLine;
        ++currentCollumn;
        for (int i = 0; i < currentSideSize - 1; ++i)
        {
            array[currentLine][currentCollumn] = currentNumber;
            ++currentCollumn;
            ++currentNumber;
        }
        --currentCollumn;
        --currentLine;
        for (int i = 0; i < currentSideSize - 1; ++i)
        {
            array[currentLine][currentCollumn] = currentNumber;
            --currentLine;
            ++currentNumber;
        }
        ++currentLine;
        --currentCollumn;
        for (int i = 0; i < currentSideSize - 1; ++i)
        {
            array[currentLine][currentCollumn] = currentNumber;
            --currentCollumn;
            ++currentNumber;
        }
    }
}

void print (int array[maxSize][maxSize], int number)
{
    for (int i = 0; i < number; ++i)
    {
        for (int j = 0; j < number; ++j)
        {
            cout << array[i][j] << "   ";
        }
        cout << endl;
    }
}

int main()
{
    
    cout << "Enter the size of matrix: " << endl;
    int number = 0;
    cin >> number;
    int array[maxSize][maxSize] {0};
    fillingTheArray(array, number);
    print(array, number);
    return 0;
}
