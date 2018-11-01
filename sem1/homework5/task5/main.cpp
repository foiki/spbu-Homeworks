#include <iostream>
#include <string.h>
#include "calculator.h"

using namespace std;

int main()
{
    const long maxLength = 100000000;
    Stack *Stack = CreateStack();
    char *string = new char[maxLength] {};
    cout << "Enter the new expression: " << endl;
    cin.get(string, maxLength);
    long length = strlen(string);
    char *postfixForm = new char[length] {};
    int j = 0;
    for (long i = 0; i < length; ++i)
    {
        if (string[i] == '(')
        {
            StackPush(Stack, int(string[i]));
        }
        else if (string[i] == '+' || string[i] == '-' || string[i] == '*' || string[i] == '/')
        {
            while (Stack->top && (priority(char(Stack->top->token)) > char(priority(string[i]))))
            {
                postfixForm[j] = char(Stack->top->token);
                ++j;
                StackPop(Stack);
            }
            StackPush(Stack, int(string[i]));
            
        }
        else if (string[i] == ')')
        {
            while (Stack->top && char(Stack->top->token != '('))
            {
                postfixForm[j] = char(Stack->top->token);
                ++j;
                StackPop(Stack);
            }
            StackPop(Stack);
        }
        else if (int(string[i]) >= 48 && int(string[i]) <= 57)
        {
            postfixForm[j] = string[i];
            ++j;
        }
    }
    while (Stack->top)
    {
        postfixForm[j] = char(Stack->top->token);
        ++j;
        StackPop(Stack);
    }
    for (long i = 0; i < length; ++i)
    {
        if (postfixForm[i] >= 48 && postfixForm[i] <= 57)
        {
            StackPush(Stack, int(postfixForm[i]) - 48);
        }
        
        else if (char(postfixForm[i]) == '+' || char(postfixForm[i]) == '-' || char(postfixForm[i]) == '*' || char(postfixForm[i]) == '/')
        {
            if (Stack->top && Stack->top->next)
            {
                int operandFirst = Stack->top->token;
                StackPop(Stack);
                int operandSecond = Stack->top->token;
                StackPop(Stack);
                
                StackPush(Stack, ArithmeticOperation(operandSecond, operandFirst, postfixForm[i]));
            }
            else
            {
                cout << "Incorrect Expression, error" << endl;
                return 0;
            }
            
        }
    }
    cout << "Result: " << Stack->top->token << endl;
    StackDelete(Stack);
    return 0;
    }
