#include "postfixCalculate.h"

int arithmeticOperation(int a, int b, char x)
{
    if (x == '+')
    {
        return a + b;
    }
    else if (x == '-')
    {
        return a - b;
    }
    else if (x == '*')
    {
        return a * b;
    }
    else
    {
        return a / b;
    }
}
