#include "stack.h"
#include <iostream>

using namespace std;

Stack *createStack()
{
    return new Stack {nullptr};
}

void stackPush(Stack *&Stack, int x)
{
    StackElement *newElement = new StackElement{x, Stack->top};
    Stack->top = newElement;
}

void stackPop(Stack *&Stack)
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

void stackPrint(Stack *Stack)
{
    StackElement *current = Stack->top;
    while (current)
    {
        cout << current->token << " ";
        current = current->next;
    }
    cout << endl;
}

void stackDelete(Stack *Stack)
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
