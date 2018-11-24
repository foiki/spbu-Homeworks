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

Stack *createStack();
void stackPush(Stack *&Stack, int x);
void stackPop(Stack *&Stack);
void stackPrint(Stack *Stack);
void stackDelete(Stack *Stack);
