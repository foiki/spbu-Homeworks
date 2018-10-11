#include <iostream>

using namespace std;

long long exponentional(long long answer, int power, int number)
{
    if (power == 0)
    {
        return 1;
    }
    if (power == 1)
    {
        return answer;
    }
    return exponentional(answer * number, --power, number);
}

int main()
{
    cout << "Enter the number and it's power: " << endl;
    int a = 0;
    cin >> a;
    int n = 0;
    cin >> n;
    if (n < 0)
    {
        double result = 0;
        n = n * (-1);
        result = 1.0 / exponentional(a , n , a);
        cout << "Result: " << result << endl;
    }
    else
    {
        cout << "Result: " << exponentional(a, n, a) << endl;
    }
    return 0;
}
