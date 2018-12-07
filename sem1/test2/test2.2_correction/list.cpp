#include <iostream>
#include "list.hpp"

using namespace std;

List *createList()
{
    return new List {nullptr};
}

void deleteList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        ListElement *nextElement = current->next;
        delete current;
        current = nextElement;
    }
    delete list;
}

void add(List *list, int newNumber)
{
    ListElement *current = list->first;
    ListElement *newElement = new ListElement{newNumber, nullptr};
    if (current)
    {
        while (current->next)
        {
            current = current->next;
        }
        current->next = newElement;
    }
    else
    {
        list->first = newElement;
    }
}

void printList(List *list)
{
    ListElement *current = list->first;
    while(current)
    {
        cout << current->number << " ";
        current = current->next;
    }
    cout << endl;
}

int value(List *list, int number)
{
    ListElement *current = list->first;
    while(number != 0)
    {
        --number;
        current = current->next;
    }
    return current->number;
}

int size(List *list)
{
    int result = 0;
    ListElement *current = list->first;
    while(current)
    {
        ++result;
        current = current->next;
    }
    return result;
}

ListElement *getElement(List *list, int number)
{
    ListElement *current = list->first;
    while(number != 0)
    {
        --number;
        current = current->next;
    }
    return current;
}

void swap(List *list, int numberFirst, int numberSecond)
{
    ListElement *first = getElement(list, numberFirst);
    ListElement *second = getElement(list, numberSecond);
    int forSwap = first->number;
    first->number = second->number;
    second->number = forSwap;
}

void insertionSort(List *list)
{
    int length = size(list);
    for (int i = 1; i < length; ++i)
    {
        int j = i;
        while (j > 0 && value(list, j) < value(list, j - 1))
        {
            swap(list, j, j - 1);
            --j;
        }
    }
}
