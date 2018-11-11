#pragma once

struct StackElement
{
    char token;
    StackElement *next;
};

struct Stack
{
    StackElement *top;
};

Stack *createStack();
void stackPush(Stack *&Stack, char x);
void stackPop(Stack *&Stack);
void stackPrint(Stack *Stack);
void stackDelete(Stack *Stack);
bool isStackEmpty(Stack *Stack);
