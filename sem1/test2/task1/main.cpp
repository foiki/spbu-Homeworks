#include <iostream>

using namespace std;

int main()
{
    int index = 0;
    cout << "Enter the index of fibonachi number: ";
    cin >> index;
    int firstNumber = 1;
    int secondNumber = 1;
    int result = 0;
    for (int i = 3; i <= index; ++i)
    {
        result = firstNumber + secondNumber;
        firstNumber = secondNumber;
        secondNumber = result;
    }
    cout << "Result: " << result << endl;
    return 0;
}
