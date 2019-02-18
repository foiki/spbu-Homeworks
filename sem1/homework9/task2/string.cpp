#include <iostream>
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
    if (string)
    {
        if (string->elements)
        {
            delete[] string->elements;
            string->elements = nullptr;
        }
        delete string;
        string = nullptr;
    }
}

String *clone(String *string)
{
    if (!string)
    {
        return createString();
    }
    return charToString(string->elements);
}

void concatenation(String *&string, String *secondString)
{
    String *newString = new String;
    newString->length = string->length + secondString->length;
    newString->elements = new char[newString->length];
    
    for (int i = 0; i < string->length; i++)
        newString->elements[i] = string->elements[i];
    
    for (int j = 0; j < secondString->length; j++)
        newString->elements[string->length + j] = secondString->elements[j];
    
    deleteString(string);
    string = newString;
}

void printString(String *string)
{
    if (string && string->elements)
    {
        for (int i = 0; i < string->length; ++i)
        {
            cout << string->elements[i];
        }
    }
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
