#include <iostream>
#include "warriors.h"

using namespace std;

List *createList()
{
    return new List {nullptr};
}

void addCircled(List *list, int x, int maxNumber)
{
    ListElement *current = list->first;
    if (list->first == nullptr || current->position > x)
    {
        list->first = new ListElement {x, list->first};
        return;
    }
    while ((current->next) && (current->next->position < x))
    {
        current = current->next;
    }
    ListElement *newElement = new ListElement {x, current->next};
    current->next = newElement;
    if (x == maxNumber)
    {
        current->next->next = list->first;
    }
}

void killNext(List *list, ListElement *&current)
{
    ListElement *now = list->first;
    while (now->next->position != current->position)
    {
        now = now->next;
    }
    if (now->next->position == 1)
    {
        list->first = now->next->next;
    }
    current = now;
    ListElement *toBeKilled = now->next;
    current->next = current->next->next;
    delete toBeKilled;
    
}

void deleteCircledList(List *list, ListElement *current)
{
    delete current->next;
    delete current;
    delete list;
    
}

void print(List *list)
{
    ListElement *current = list->first;
    while (current && current->next != list->first)
    {
        cout << current->position << "   " << endl;
        current = current->next;
    }
    cout << current->position << "   " << endl;
}
