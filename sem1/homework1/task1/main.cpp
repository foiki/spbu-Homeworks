#include <iostream>

using namespace std;

int main()
{
    int x = 0;
    cout << "Enter the value of X: ";
    cin >> x;
    int first = x * x;
    int second = (first + 1) * (first + x) + 1;
    cout << "Result: " << second << endl;
    return 0;
}
