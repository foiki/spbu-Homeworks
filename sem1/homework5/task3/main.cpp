#include <iostream>
#include <string.h>
#include "postfix.h"

using namespace std;

int main()
{
    const long maxLength = 100000000;
    Stack *Stack = CreateStack();
    char *string = new char[maxLength];
    cout << "Enter the new expression: " << endl;
    cin.get(string, maxLength);
    long length = strlen(string);
    for (long i = 0; i < length; ++i)
    {
        if (string[i] == '(')
        {
            StackPush(Stack, string[i]);
        }
        else if (string[i] == '+' || string[i] == '-' || string[i] == '*' || string[i] == '/')
        {
            while (!isStackEmpty(Stack) && (priority(Stack->top->token) >= priority(string[i])))
            {
                cout << Stack->top->token << " ";
                StackPop(Stack);
            }
            StackPush(Stack, string[i]);

        }
        else if (string[i] == ')')
        {
            while (!isStackEmpty(Stack) && Stack->top->token != '(')
            {
                cout << Stack->top->token << " ";
                StackPop(Stack);
            }
            StackPop(Stack);
        }
        else if (int(string[i]) >= 48 && int(string[i]) <= 57)
        {
            cout << string[i] << " ";
        }
    }
    while (!isStackEmpty(Stack))
    {
        cout << Stack->top->token << " ";
        StackPop(Stack);
    }
    StackDelete(Stack);
    return 0;
}
