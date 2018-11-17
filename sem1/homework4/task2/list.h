#pragma once

const int maxSize = 10000;

struct ListElement
{
    char name[maxSize];
    long long number;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);
void add(List *list, char newName[], long long newNumber);
