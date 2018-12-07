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
int value(List *list, int number);
int size(List *list);
ListElement *getElement(List *list, int number);
void swap(List *list, int numberFirst, int numberSecond);
void insertionSort(List *list);
void printList(List *list);

