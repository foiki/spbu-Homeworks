#include <iostream>
#include <string.h>
#include "calculator.h"
#include "stack.h"

using namespace std;

int main()
{
    const long maxLength = 100000000;
    Stack *stack = createStack();
    char *string = new char[maxLength] {};
    cout << "Enter the new expression: " << endl;
    cin.get(string, maxLength);
    long length = strlen(string);
    char *postfixForm = new char[length] {};
    fromInfixToPostfix(stack, string, postfixForm, length);
    delete[] string;
    if (postfixCalculation(stack, postfixForm, length))
    {
        cout << "Result: " << stack->top->token << endl;
    }
    else
    {
        cout << "Incorrect Expression, error" << endl;
    }
    delete[] postfixForm;
    stackDelete(stack);
    return 0;
    }
