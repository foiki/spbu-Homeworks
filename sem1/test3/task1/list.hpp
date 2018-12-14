#pragma once
#include <iostream>

const int maxSize = 10000;

struct ListElement
{
    int number;
    int count;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);
bool exist(List *list, int number);
void add(List *list, int newNumber);
void printList(List *list);
