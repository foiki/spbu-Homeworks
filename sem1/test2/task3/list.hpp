#pragma once
#include "string.hpp"

const int maxSize = 10000;

struct ListElement
{
    char surname[maxSize];
    long loyalty;
    int whatToDo;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);
void add(List *list, char newSurname[], long newLoyalty);
bool isFirstAlphabeticalAbove(char a[], char b[]);
void approriateSecondInFirst(char a[], char b[]);
void listPrint(List *list);
