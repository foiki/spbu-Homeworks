#pragma once

struct ListElement
{
    int number;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);
void add(List *list, int newNumber);
void selectionSort(List *list);
void printList(List *list);
