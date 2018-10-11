#include <iostream>

using namespace std;

void fareyAlorithm(int number)
{
    int nominatorFirst = 0;
    int denominatorFirst = 1;
    int nominatorSecond = 1;
    int denominatorSecond = number;
    cout << nominatorFirst << "/" << denominatorFirst << endl;
    while (nominatorSecond <= number)
    {
        int k = (number + denominatorFirst) / denominatorSecond;
        int oldNominator = nominatorFirst;
        int oldDenominator = denominatorFirst;
        nominatorFirst = nominatorSecond;
        denominatorFirst = denominatorSecond;
        nominatorSecond = (k * nominatorSecond) - oldNominator;
        denominatorSecond = (k * denominatorSecond) - oldDenominator;
        cout << nominatorFirst << "/" << denominatorFirst << endl;
    }
}
int main()
{
    int number = 0;
    cout << "Enter the maximinum denominator: ";
    cin >> number;
    fareyAlorithm(number);
    return 0;
}
