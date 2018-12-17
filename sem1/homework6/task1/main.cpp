#include <iostream>

using namespace std;

int calculateDegree(unsigned char *numberInBinary)
{
    return ((numberInBinary[7] & 0b01111111) << 4) + (numberInBinary[6] >> 4) - 1023;
}

void calculateMantisa(unsigned char *&numberInBinary)
{
    numberInBinary[6] &= 0b00001111;
    numberInBinary[6] += 0b11110000;
    numberInBinary[7] = 0b00111111;
    
}

int main()
{
    double number = 0.0;
    cout << "Enter new number: ";
    cin >> number;
    if (number == 0)
    {
        cout << 0 << endl;
    }
    else
    {
        unsigned char *numberInBinary = (unsigned char *)&number;
        unsigned char sign = 0b10000000;
        cout << ((numberInBinary[7] & sign) ? "-" : "+");
        int degree = calculateDegree(numberInBinary);
        calculateMantisa(numberInBinary);
        cout << number << "*2^" << degree << endl;
    }
}
