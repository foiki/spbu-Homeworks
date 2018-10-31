#pragma once

struct StackElement
{
    int token;
    StackElement *next;
};

struct Stack
{
    StackElement * top;
};

Stack *CreateStack();
void StackPush(Stack *&Stack, int x);
void StackPop(Stack *&Stack);
int ArithmeticOperation(int a, int b, char x);
