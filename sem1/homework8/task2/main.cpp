#include <iostream>
#include "string.h"

using namespace std;

int main()
{
    char array[] = "Hello world!";
    cout << "Created array of chars: Hello world!" << endl;
    String *string = charToString(array);
    cout << "Created string from char*: ";
    printString(string);
    String *clonedString = clone(string);
    cout << "Cloned string to new string: ";
    printString(clonedString);
    String *connectedStrings = concatenation(string, clonedString);
    cout << "Concatenation of two 'string' and 'clonedString': ";
    printString(connectedStrings);
    if (!compare(string, connectedStrings))
    {
        cout << "'string' and 'connectedStrins' are equal" << endl;
    }
    else
    {
        cout << "'string' and 'connectedStrins' are not equal" << endl;
    }
    cout << "Length of connected strings: " << length(connectedStrings) << endl;
    if (isEmpty(string))
    {
        cout << "'string' is empty" << endl;
    }
    else
    {
        cout << "String is not empty" << endl;
    }
    String *newString = subString(string ,4 , 7);
    cout << "Сopied seven elements of the 'string' starting from the fourth:" << endl;
    printString(newString);
    char *newArray = stringToChar(string);
    long length = string->length;
    cout << "Created char* from char string: ";
    for (int i = 0; i < length; ++i)
    {
        cout << newArray[i];
    }
    cout << endl;
    deleteString(string);
    deleteString(clonedString);
    deleteString(connectedStrings);
    deleteString(newString);
    delete[] newArray;
    return 0;
}
