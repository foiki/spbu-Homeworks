#include <iostream>
#include <string.h>
#include "postfix.h"
#include "stack.h"

using namespace std;

int main()
{
    const long maxLength = 100000000;
    Stack *Stack = createStack();
    char *string = new char[maxLength];
    cout << "Enter the new expression: " << endl;
    cin.get(string, maxLength);
    long length = strlen(string);
    for (long i = 0; i < length; ++i)
    {
        if (string[i] == '(')
        {
            stackPush(Stack, string[i]);
        }
        else if (string[i] == '+' || string[i] == '-' || string[i] == '*' || string[i] == '/')
        {
            while (!isStackEmpty(Stack) && (priority(Stack->top->token) >= priority(string[i])))
            {
                cout << Stack->top->token << " ";
                stackPop(Stack);
            }
            stackPush(Stack, string[i]);

        }
        else if (string[i] == ')')
        {
            while (!isStackEmpty(Stack) && Stack->top->token != '(')
            {
                cout << Stack->top->token << " ";
                stackPop(Stack);
            }
            stackPop(Stack);
        }
        else if (string[i] >= '0' && string[i] <= '9')
        {
            cout << string[i] << " ";
        }
    }
    delete[] string;
    while (!isStackEmpty(Stack))
    {
        cout << Stack->top->token << " ";
        stackPop(Stack);
    }
    cout << endl;
    stackDelete(Stack);
    return 0;
}
