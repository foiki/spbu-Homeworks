#include <iostream>
#include "stack.h"

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
    StackElement *newTop = Stack->top->next;
    delete Stack->top;
    Stack->top = newTop;
}
