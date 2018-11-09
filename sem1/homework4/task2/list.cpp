#include <iostream>
#include "list.h"
#include "phoneBook.h"

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

void add(List *list, char newName[], long long newNumber)
{
    ListElement *current = list->first;
    if ((list->first == nullptr) || !isFirstAlphabeticalAbove(current->name, newName))
    {
        ListElement *oldListFirst = list->first;
        list->first = new ListElement;
        approriateSecondInFirst(list->first->name, newName);
        list->first->number = newNumber;
        list->first->next = oldListFirst;
        return;
    }
    while ((current->next) && (isFirstAlphabeticalAbove(current->next->name, newName)))
    {
        current = current->next;
    }
    ListElement *newElement = new ListElement;
    approriateSecondInFirst(newElement->name, newName);
    newElement->number = newNumber;
    newElement->next = current->next;
    current->next = newElement;
}
