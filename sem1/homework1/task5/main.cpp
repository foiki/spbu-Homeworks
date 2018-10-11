#include <iostream>
#include <string.h>

using namespace std;

int main()
{
    const int n = 10000;
    char s[n];
    cout << "Enter the string: ";
    cin >> s;
    long lengthS = strlen(s);
    char brackets[lengthS];
    int positionOfLastBracket = 0;
    int i = 0;
    bool reasonToContinue = true;
    while (reasonToContinue == true && i < lengthS)
    {
        if ((s[i] == '(') || (s[i] == '{') || (s[i] == '['))
        {
            brackets[positionOfLastBracket] = s[i];
            ++positionOfLastBracket;
        }
        if (((s[i] == ')') || (s[i] == '}') || (s[i] == ']')) && positionOfLastBracket < 1)
        {
            reasonToContinue = false;
        }
        else
        {
            if (((s[i] == ')') || (s[i] == '}') || (s[i] == ']')) && positionOfLastBracket > 0)
            {
                if (s[i] == ')' && brackets[positionOfLastBracket - 1] == '(')
                {
                    brackets[positionOfLastBracket-1] = 0;
                    --positionOfLastBracket;
                }
                else
                {
                    if (s[i] == '}' && brackets[positionOfLastBracket - 1] == '{')
                    {
                        brackets[positionOfLastBracket-1] = 0;
                        --positionOfLastBracket;
                    }
                    else
                    {
                        if (s[i] == ']' && brackets[positionOfLastBracket - 1] == '[')
                        {
                            brackets[positionOfLastBracket-1] = 0;
                            --positionOfLastBracket;
                        }
                        else
                        {
                            reasonToContinue = false;
                        }
                    }
                }
            }
        }
        ++i;
    }
    if (positionOfLastBracket == 0 && reasonToContinue == true)
    {
        cout << "The balance of the brackets is not broken" << endl;
    } else
    {
        cout << "The balance of the brackets is broken" << endl;
    }
    return 0;
}
