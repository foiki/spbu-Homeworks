#include <iostream>
#include <string.h>
#include "postfixCalculate.h"

using namespace std;

int main()
{
    const long maxLength = 100000000;
    Stack *Stack = CreateStack();
    char *string = new char[maxLength];
    cout << "Enter the new expression: " << endl;
    cin.get(string, maxLength);
    long length = strlen(string);
    for (long i = 0; i <= length; ++i)
    {
        if (int(string[i]) >= 48 && int(string[i]) <= 57)
        {
            StackPush(Stack, int(string[i]) - 48);
        }
        
        else if (string[i] == '+' || string[i] == '-' || string[i] == '*' || string[i] == '/')
        {
            if (Stack->top && Stack->top->next)
            {
                int operandFirst = int(Stack->top->token);
                StackPop(Stack);
                int operandSecond = int(Stack->top->token);
                StackPop(Stack);
                
                StackPush(Stack, ArithmeticOperation(operandSecond, operandFirst, string[i]));
            }
            else
            {
                cout << "Incorrect Expression, error" << endl;
                return 0;
            }
            
        }
    }
    cout << "Result: " << Stack->top->token << endl;
    delete Stack->top;
    delete Stack;
    delete[] string;
    return 0;
}
