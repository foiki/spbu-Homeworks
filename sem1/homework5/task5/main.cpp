#include <iostream>
#include <string.h>
#include "calculator.h"
#include "stack.h"

using namespace std;

int main()
{
    const long maxLength = 100000000;
    Stack *Stack = createStack();
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
            stackPush(Stack, int(string[i]));
        }
        else if (string[i] == '+' || string[i] == '-' || string[i] == '*' || string[i] == '/')
        {
            handlingTheSign(Stack, j, string[i], postfixForm);
        }
        else if (string[i] == ')')
        {
            while (Stack->top && char(Stack->top->token != '('))
            {
                postfixForm[j] = char(Stack->top->token);
                ++j;
                stackPop(Stack);
            }
            stackPop(Stack);
        }
        else if (string[i] >= '0' && string[i] <= '9')
        {
            postfixForm[j] = string[i];
            ++j;
        }
    }
    while (Stack->top)
    {
        postfixForm[j] = char(Stack->top->token);
        ++j;
        stackPop(Stack);
    }
    for (long i = 0; i < length; ++i)
    {
        if (postfixForm[i] >= '0' && postfixForm[i] <= '9')
        {
            stackPush(Stack, int(postfixForm[i]) - '0');
        }
        
        else if (char(postfixForm[i]) == '+' || char(postfixForm[i]) == '-' || char(postfixForm[i]) == '*' || char(postfixForm[i]) == '/')
        {
            if (Stack->top && Stack->top->next)
            {
                int operandFirst = Stack->top->token;
                stackPop(Stack);
                int operandSecond = Stack->top->token;
                stackPop(Stack);
                stackPush(Stack, arithmeticOperation(operandSecond, operandFirst, postfixForm[i]));
            }
            else
            {
                cout << "Incorrect Expression, error" << endl;
                return 0;
            }
            
        }
    }
    delete[] postfixForm;
    cout << "Result: " << Stack->top->token << endl;
    stackDelete(Stack);
    return 0;
    }
