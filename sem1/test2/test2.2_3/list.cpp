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
    list->first = new ListElement {newNumber, list->first};
}

void selectionSort(List *list)
{
    ListElement *afterSorted = list->first;
    while (afterSorted)
    {
        ListElement *minimum = afterSorted;
        ListElement *current = afterSorted;
        while (current)
        {
            if (current->number < minimum->number)
            {
                minimum = current;
            }
            current = current->next;
        }
        if (minimum)
        {
            swap(afterSorted->number, minimum->number);
        }
        afterSorted = afterSorted->next;
    }
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->number << " ";
        current = current->next;
    }
    cout << endl;
}
