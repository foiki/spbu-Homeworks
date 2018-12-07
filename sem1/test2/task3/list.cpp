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

void add(List *list, char newSurname[], long newLoyalty)
{
    ListElement *current = list->first;
    if ((list->first == nullptr) || !isFirstAlphabeticalAbove(current->surname, newSurname))
    {
        ListElement *oldListFirst = list->first;
        list->first = new ListElement;
        approriateSecondInFirst(list->first->surname, newSurname);
        list->first->loyalty = newLoyalty;
        list->first->next = oldListFirst;
        list->first->whatToDo = 0;
        return;
    }
    while ((current->next) && (isFirstAlphabeticalAbove(current->next->surname, newSurname)))
    {
        current = current->next;
    }
    ListElement *newElement = new ListElement;
    approriateSecondInFirst(newElement->surname, newSurname);
    newElement->loyalty = newLoyalty;
    newElement->whatToDo = 0;
    newElement->next = current->next;
    current->next = newElement;
}

bool isFirstAlphabeticalAbove(char a[], char b[])
{
    long minLength = min(strlen(a), strlen(b));
    int i = 0;
    while (i < minLength && a[i] == b[i])
    {
        ++i;
    }
    return a[i] < b[i];
}

void approriateSecondInFirst(char a[], char b[])
{
    long newLength = strlen(b);
    for (int i = 0; i < newLength; ++i)
    {
        a[i] = b[i];
    }
}

bool areArraysEqual(char a[], char b[])
{
    long lengthFirst = strlen(a);
    long lengthSecond = strlen(b);
    if (lengthFirst != lengthSecond)
    {
        return false;
    }
    else
    {
        for (int i = 0; i < lengthFirst; ++i)
        {
            if (a[i] != b[i])
            {
                return false;
            }
        }
    }
    return true;
}

void listPrint(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->surname << " ";
        if (current->whatToDo == 0)
        {
            cout << "Will stay alive(may be...)" << endl;
        }
        else if (current->whatToDo == 1)
        {
            cout << "Should be killed" << endl;
        }
        else
        {
            cout << "Should be exiled" << endl;
        }
        current = current->next;
    }
}
