#include <iostream>
#include "list.hpp"

using namespace std;

List *createList()
{
    return new List {nullptr};
}

void deleteListElement(ListElement *toDelete)
{
    deleteNode(toDelete->node);
    delete toDelete;
}

void deleteList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        ListElement *nextElement = current->next;
        deleteListElement(current);
        current = nextElement;
    }
    delete list;
}


void add(List *list, Node *node)
{
    ListElement *newElement = new ListElement{node, list->first};
    list->first = newElement;
}

void remove(List *list, ListElement *&current)
{
    ListElement *afterDeleted = current->next;
    deleteListElement(current);
    current = afterDeleted;
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->node->countOfSame << " ";
        printString(current->node->symbols);
        cout << endl;
        current = current->next;
    }
}

Node *findMinimum(List *list)
{
    ListElement *beforeMinimum = list->first;
    ListElement *current = list->first;
    while (current->next)
    {
        if (current->next->node->countOfSame < beforeMinimum->next->node->countOfSame)
        {
            beforeMinimum = current;
        }
        current = current->next;
    }
    Node *minimumNode = beforeMinimum->next->node;
    ListElement *toRemove = beforeMinimum->next;
    if (list->first->node->countOfSame <= beforeMinimum->next->node->countOfSame)
    {
        minimumNode = list->first->node;
        toRemove = list->first;
        list->first = list->first->next;
    }
    else
    {
        beforeMinimum->next = beforeMinimum->next->next;
    }
    delete toRemove;
    toRemove = nullptr;
    return minimumNode;
}
