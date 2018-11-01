#include "calculator.h"
#include <iostream>

using namespace std;

Stack *CreateStack()
{
    return new Stack {nullptr};
}

void StackPush(Stack *&Stack, char x)
{
    StackElement *newElement = new StackElement{x, Stack->top};
    Stack->top = newElement;
}

void StackPop(Stack *&Stack)
{
    if (!Stack->top)
    {
        cout << "Stack is empty!" << endl;
    }
    else
    {
        StackElement *afterDeleted = Stack->top->next;
        delete Stack->top;
        Stack->top = afterDeleted;
    }
}

void StackPrint(Stack *Stack)
{
    StackElement *current = Stack->top;
    while (current)
    {
        cout << current->token << " ";
        current = current->next;
    }
    cout << endl;
}

void StackDelete(Stack *Stack)
{
    StackElement *current = Stack->top;
    while(current)
    {
        StackElement *nextElement = current->next;
        delete current;
        current = nextElement;
    }
    delete Stack;
}

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
