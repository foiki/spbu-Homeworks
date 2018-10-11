#include <iostream>

using namespace std;

int recursiveFibonacci(int number)
{
    if (number == 1 || number == 2)
    {
        return 1;
    }
    return recursiveFibonacci(number - 1) + recursiveFibonacci(number - 2);
}

int iterativeFibonacci(int N)
{
    int firstTerm = 1;
    int secondTerm = 1;
    int result = 0;
    for (int i = 2; i < N; ++i)
    {
        result = firstTerm + secondTerm;
        firstTerm = secondTerm;
        secondTerm = result;
    }
    return result;
}

int main()
{
    int position = 0;
    cout << "Enter the position in a Fibonachi row: " << endl;
    cin >> position;
    cout << "Recursive result: " << recursiveFibonacci(position) << endl;
    cout << "Iterative result: " << iterativeFibonacci(position) << endl;
    return 0;
}
