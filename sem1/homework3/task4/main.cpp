#include <iostream>
#include <ctime>

using namespace std;

int bullFinder(int a, int b[])
{
    int count = 0;
    for (int i = 0; i < 4; ++i)
    {
        if (a % 10 == b[3 - i])
        {
            ++count;
        }
        a = a / 10;
    }
    return count;
}

int cowFinder(int n, int b[])
{
    int count = 0;
    int a[4];
    a[0] = n / 1000;
    a[1] = (n / 100) % 10;
    a[2] = (n / 10) % 10;
    a[3] = n % 10;
    for (int i = 0; i < 4; ++i)
    {
        for (int j = 0; j < 4; ++j)
        {
            if(b[j] == a[i] && i != j)
            {
                ++count;
            }
        }
    }
    return count;
}

bool areNumbersRepeated(int a[], int n)
{
    for (int i = 0; i < n; ++i)
    {
        for (int j = i + 1; j < n; ++j)
        {
            if (a[i] == a[j])
            {
                return true;
            }
        }
    }
    return false;
}

int main()
{
    const int numberOfDigits = 4;
    int hiddenNumber[4];
    srand(time(0));
    bool isNumberNotMatch = true;
    while (isNumberNotMatch)
    {
        hiddenNumber[0] = rand() % 10;
        hiddenNumber[1] = rand() % 10;
        hiddenNumber[2] = rand() % 10;
        hiddenNumber[3] = rand() % 10;
        isNumberNotMatch = areNumbersRepeated(hiddenNumber, numberOfDigits);
    }
    cout << "Computer generated new number, try to divine it :)" << endl;
    int bullCount = 0;
    while (bullCount != 4)
    {
        int newNumber = 0;
        cout << "Enter the number: ";
        cin >> newNumber;
        bullCount = bullFinder(newNumber, hiddenNumber);
        cout << "Bulls: " << bullCount << endl << "Cows: " << cowFinder(newNumber, hiddenNumber) << endl;
    }
    cout << "You have done it, congratulations!!!";
    return 0;
}
