#include <iostream>

using namespace std;

long long recursiveMethod(int num, int nextFactor)
{
    if (nextFactor < 0)
    {
        return 0;
    }
    if (nextFactor == 0)
    {
        return 1;
    }
    if (nextFactor == 1)
    {
        return num;
    }
    return recursiveMethod(num * (nextFactor - 1), nextFactor - 1);
}

long long iterativeMethod(int num)
{
    if (num < 0)
    {
        return 0;
    }
    if (num == 0)
    {
        return 1;
    }
    long long result = 1;
    for (int i = 1; i <= num; ++i)
    {
        result *= i;
    }
    return result;
}

int main()
{
    cout << "Enter a number to calculate the factorial: " << endl;
    int num = 0;
    cin >> num;
    int nextFactor = num;
    cout << "Recursive result: " << recursiveMethod(num, nextFactor) << endl;
    cout << "Itetative result: " << iterativeMethod(num) << endl;
    return 0;
}
