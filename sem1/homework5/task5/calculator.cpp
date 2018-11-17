#include "calculator.h"

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

int arithmeticOperation(int a, int b, char x)
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

void handlingTheSign(Stack *Stack, int &j, char a, char postfixForm[])
{
    while (Stack->top && (priority(char(Stack->top->token)) > priority(a)))
    {
        postfixForm[j] = char(Stack->top->token);
        ++j;
        stackPop(Stack);
    }
    stackPush(Stack, a);
}

void fromInfixToPostfix(Stack *stack, char *string, char *postfixForm, long length)
{
    int j = 0;
    for (long i = 0; i < length; ++i)
    {
        if (string[i] == '(')
        {
            stackPush(stack, int(string[i]));
        }
        else if (string[i] == '+' || string[i] == '-' || string[i] == '*' || string[i] == '/')
        {
            handlingTheSign(stack, j, string[i], postfixForm);
        }
        else if (string[i] == ')')
        {
            while (stack->top && char(stack->top->token != '('))
            {
                postfixForm[j] = char(stack->top->token);
                ++j;
                stackPop(stack);
            }
            stackPop(stack);
        }
        else if (string[i] >= '0' && string[i] <= '9')
        {
            postfixForm[j] = string[i];
            ++j;
        }
    }
    while (stack->top)
    {
        postfixForm[j] = char(stack->top->token);
        ++j;
        stackPop(stack);
    }
}

bool postfixCalculation(Stack *stack, char *postfixForm, long length)
{
    for (long i = 0; i < length; ++i)
    {
        if (postfixForm[i] >= '0' && postfixForm[i] <= '9')
        {
            stackPush(stack, int(postfixForm[i]) - '0');
        }
        
        else if (char(postfixForm[i]) == '+' || char(postfixForm[i]) == '-' || char(postfixForm[i]) == '*' || char(postfixForm[i]) == '/')
        {
            if (stack->top && stack->top->next)
            {
                int operandFirst = stack->top->token;
                stackPop(stack);
                int operandSecond = stack->top->token;
                stackPop(stack);
                stackPush(stack, arithmeticOperation(operandSecond, operandFirst, postfixForm[i]));
            }
            else
            {
                return 0;
            }
            
        }
    }
    return 1;
}
