#include <iostream>
#include "binarySystem.h"

using namespace std;

const int countOfBitesForEachNumber = 16;

void fromDecimalToBinary(int number, int *result)
{
    int *reversedNumber = new int[countOfBitesForEachNumber] {0};
    if(number >= 0)
    {
        reversedNumber[countOfBitesForEachNumber - 1] = 0;
    }
    else
    {
        reversedNumber[countOfBitesForEachNumber - 1] = 1;
        number *= -1;
    }
    int i = 0;
    while (number > 1)
    {
        reversedNumber[i] = number % 2;
        number /= 2;
        ++i;
    }
    reversedNumber[i] = number;
    for (int i = countOfBitesForEachNumber - 1; i >= 0; --i)
    {
        result[countOfBitesForEachNumber - i - 1] = reversedNumber[i];
    }
    if (result[0] == 1)
    {
        for (int i = 1; i < countOfBitesForEachNumber; ++i)
        {
            if (result[i] == 1)
            {
                result[i] = 0;
            }
            else
            {
                result[i] = 1;
            }
        }
    }
}

void printBinaryNumber(int *number)
{
    for (int i = 0; i < countOfBitesForEachNumber; ++i)
    {
        cout << number[i];
    }
    cout << endl;
}

void additionInBinary(int *firstNumber, int *secondNumber, int *result)
{
    int overflow = 0;
    for (int i = countOfBitesForEachNumber - 1; i >= 0; --i)
    {
        result[i] = firstNumber[i] + secondNumber[i] + overflow;
        overflow = 0;
        if (result[i] == 2)
        {
            overflow = 1;
            result[i] = 0;
        }
        if (result[i] == 3)
        {
            overflow = 1;
            result[i] = 1;
        }

        if (i == 0 && overflow == 1)
        {
            int j = countOfBitesForEachNumber - 1;
            while (overflow != 0)
            {
                result[j] += overflow;
                overflow = 0;
                if (result[j] == 2)
                {
                    overflow = 1;
                    result[j] = 0;
                }
                --j;
            }
        }
    }
    if (result[0] == 1)
    {
        for (int i = 1; i < countOfBitesForEachNumber; ++i)
        {
            if (result[i] == 1)
            {
                result[i] = 0;
            }
            else
            {
                result[i] = 1;
            }
        }
    }
}

int binaryToDecimal(int *sumInBinary)
{
    int result = 0;
    int factor = 1;
    for (int i = countOfBitesForEachNumber - 1; i > 0; --i)
    {
        result += sumInBinary[i] * factor;
        factor *= 2;
    }
    return result;
}
