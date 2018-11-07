#include "calculator.h"

int priority(char a)
{
    if ((a == '*') || (a == '/'))
    {
        return 2;
    }
    if ((a == '+') || (a == '-'))
    {
        return 1;
    }
    else
    {
        return 0;
    }
}

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

void handlingTheSign(Stack *Stack, int &j, char a, char postfixForm[])
{
    while (Stack->top && (priority(char(Stack->top->token)) > priority(a)))
    {
        postfixForm[j] = char(Stack->top->token);
        ++j;
        stackPop(Stack);
    }
    stackPush(Stack, a);
}
