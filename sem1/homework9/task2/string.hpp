#pragma once

struct String
{
    char *elements;
    long length;
};

String *createString();
String *charToString(char *array);
void deleteString(String *string);
String *clone(String *string);
String *concatenation(String *firstString, String *secondString);
void printString(String *string);
bool compare(String *firstString, String *secondString);
long length(String *string);
