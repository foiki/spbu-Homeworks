#include <iostream>

using namespace std;

int exponentiation(int a, int power)
{
    if (power == 0)
    {
        return 1;
    }
    if (power % 2 == 1)
    {
        return exponentiation(a, power - 1) * a;
    }
    else
    {
        int result = exponentiation(a, power / 2);
        return result * result;
    }
}

int main()
{
    int number = 0;
    int power = 0;
    cout << "Enter the number and it's power: ";
    cin >> number >> power;
    cout << "Result: " << exponentiation(number, power);
    return 0;
}
