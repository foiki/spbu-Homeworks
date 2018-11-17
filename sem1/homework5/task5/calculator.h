#pragma once
#include "stack.h"

int priority(char a);
int arithmeticOperation(int a, int b, char x);
void handlingTheSign(Stack *Stack, int &j, char a, char postfixForm[]);
void fromInfixToPostfix(Stack *stack, char *string, char *postfixForm, long length);
bool postfixCalculation(Stack *stack, char *postfixForm, long length);
