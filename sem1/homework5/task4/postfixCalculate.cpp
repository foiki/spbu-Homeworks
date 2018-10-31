#include <iostream>
#include "postfixCalculate.h"

using namespace std;

Stack *CreateStack()
{
    return new Stack {nullptr};
}

void StackPush(Stack *&Stack, int x)
{
    StackElement *newElement = new StackElement{x, Stack->top};
    Stack->top = newElement;
}

void StackPop(Stack *&Stack)
{
    StackElement *newTop = Stack->top->next;
    delete Stack->top;
    Stack->top = newTop;
}

int ArithmeticOperation(int a, int b, char x)
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
