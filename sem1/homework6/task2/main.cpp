#include <iostream>
#include "binarySystem.h"

using namespace std;

const int countOfBitesForEachNumber = 16;

int main()
{
    
    cout << "Enter two numbers in decimal notation: " << endl;
    int numberFirstDecimal = 0;
    int numberSecondDecimal = 0;
    cin >> numberFirstDecimal >> numberSecondDecimal;
    int *numberFirstBinary = new int[countOfBitesForEachNumber];
    int *numberSecondBinary = new int[countOfBitesForEachNumber];
    fromDecimalToBinary(numberFirstDecimal, numberFirstBinary);
    fromDecimalToBinary(numberSecondDecimal, numberSecondBinary);
    cout << "Negative numbers will be presented in the reversed code." << endl;
    cout << "First number: ";
    printBinaryNumber(numberFirstBinary);
    cout << "Second number: ";
    printBinaryNumber(numberSecondBinary);
    int *sumInBinary = new int[countOfBitesForEachNumber];
    additionInBinary(numberFirstBinary, numberSecondBinary, sumInBinary);
    cout << "Sum in binary system: ";
    printBinaryNumber(sumInBinary);
    int sumInDecimal = binaryToDecimal(sumInBinary);
    cout << "Sum in decimal system: ";
    if (sumInBinary[0] == 1 && sumInDecimal != 0)
    {
        cout << "-" << sumInDecimal;
    }
    else
    {
        cout << sumInDecimal;
    }
    cout << endl;
    delete[] numberFirstBinary;
    delete[] numberSecondBinary;
    delete[] sumInBinary;
    return 0;
}
