#include "postfix.h"

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
    if ((a == '(') || (a == ')'))
    {
        return 0;
    }
    return 0;
}
