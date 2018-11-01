#pragma once

struct StackElement
{
    int token;
    StackElement *next;
};

struct Stack
{
    StackElement *top;
};

Stack *CreateStack();
void StackPush(Stack *&Stack, char x);
void StackPop(Stack *&Stack);
void StackPrint(Stack *Stack);
void StackDelete(Stack *Stack);
int priority(char a);
int ArithmeticOperation(int a, int b, char x);
