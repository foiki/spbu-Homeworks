#include <iostream>
#include "list.h"

using namespace std;

List *createList()
{
    return new List {nullptr};
}

void add(List *list, int x)
{
    ListElement *current = list->first;
    if (list->first == nullptr || current->position > x)
    {
        list->first = new ListElement {x, false, list->first};
        return;
    }
    while ((current->next) && (current->next->position < x))
    {
        current = current->next;
    }
    ListElement *newElement = new ListElement {x, false, current->next};
    current->next = newElement;
}

void connect(List *list, int number)
{
    ListElement *current = list->first;
    for (int i = 1; i <= number; ++i)
    {
        if (current->position == number)
        {
            current->next = list->first;
        }
        current = current->next;
    }
}

void deleteCircledList(List *list)
{
    ListElement *current = list->first;
    ListElement *begin = current;
    while (current)
    {
        if (current->next != begin)
        {
            ListElement *nextElement = current->next;
            delete current;
            current = nextElement;
        }
        else
        {
            delete current;
            current = nullptr;
        }
    }
    delete list;
}

void print(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->position << "   " << current->killed << endl;
        current = current->next;
    }
}

int findLastAlive(List *list)
{
    ListElement *current = list->first;
    while (current->killed)
    {
        current = current->next;
    }
    return current->position;
}

