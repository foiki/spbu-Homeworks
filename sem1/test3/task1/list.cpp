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
    if (list->first == nullptr || current->number > newNumber)
    {
        list->first = new ListElement {newNumber, 1, list->first};
        return;
    }
    while ((current->next) && (current->next->number < newNumber))
    {
        current = current->next;
    }
    if (current->next && current->next->number == newNumber)
    {
        ++current->next->count;
    }
    else
    {
        ListElement *newElement = new ListElement {newNumber, 1, current->next};
        current->next = newElement;
    }
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->number << "(" << current->count << ") ";
        current = current->next;
    }
    cout << endl;
}
