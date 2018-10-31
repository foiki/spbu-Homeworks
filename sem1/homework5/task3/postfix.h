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

Stack *CreateStack();
void StackPush(Stack *&Stack, char x);
void StackPop(Stack *&Stack);
void StackPrint(Stack *Stack);
void StackDelete(Stack *Stack);
int priority(char a);
bool isStackEmpty(Stack *Stack);
