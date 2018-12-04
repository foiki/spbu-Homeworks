#include <iostream>
#include <string.h>
#include "string.hpp"

using namespace std;

String *createString()
{
    return new String {nullptr, 0};
}

String *charToString(char *array)
{
    if (!array)
    {
        return nullptr;
    }
    String *string = new String {nullptr, 0};
    string->length = strlen(array);
    string->elements = new char[string->length];
    strcpy(string->elements, array);
    return string;
}

void deleteString(String *string)
{
    if (string->elements)
    {
        delete[] string->elements;
    }
    delete[] string;
}

String *clone(String *string)
{
    if (!string)
    {
        return createString();
    }
    return charToString(string->elements);
}

String *concatenation(String *firstString, String *secondString)
{
    if (!firstString && !secondString)
    {
        return createString();
    }
    if (!firstString)
    {
        return charToString(firstString->elements);
    }
    if (!secondString)
    {
        return charToString(secondString->elements);
    }
    return charToString(strcat(firstString->elements, secondString->elements));
}

void printString(String *string)
{
    for (int i = 0; i <= string->length; ++i)
    {
        cout << string->elements[i];
    }
    cout << endl;
}

bool compare(String *firstString, String *secondString)
{
    return (strcmp(firstString->elements, secondString->elements) != 0);
}

long length(String *string)
{
    if (string)
    {
        return string->length;
    }
    return 0;
}

bool isEmpty(String *string)
{
    return !(string && string->elements && string->length > 0);
}

String *subString(String *string, long i, long lenght)
{
    if (string && string->elements)
    {
        String *newString = createString();
        newString->elements = new char[lenght];
        for (int j = 0; j < lenght; ++j)
        {
            newString->elements[j] = string->elements[j + i];
        }
        newString->length = lenght;
        return newString;
    }
    return createString();
}

char *stringToChar(String *string)
{
    if (string && string->elements)
    {
        char *newArray = new char[string->length];
        strcpy(newArray, string->elements);
        return newArray;
    }
    return nullptr;
}
