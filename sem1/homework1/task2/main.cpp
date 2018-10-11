#include <iostream>

using namespace std;

int main(){
    int a = 0;
    int b = 0;
    cin >> a >> b;
    int minus = 1;
    int incompQuotient = 0;
    if (a < 0)
    {
        minus = minus * (-1);
        a = a * (-1);
    }
    if (b < 0)
    {
        minus = minus * (-1);
        b = b * (-1);
    }
    if (b == 0)
    {
        cout << "Error" << endl;
    }
    else
    {
        while (a >= b)
        {
            a = a - b;
            ++incompQuotient;
        }
    }
    int result = incompQuotient * minus;
    cout << result << endl;
    return 0;
}
