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
    ListElement *current = list->first;
    if ((list->first == nullptr) || current->node->countOfSame > node->countOfSame)
    {
        ListElement *oldListFirst = list->first;
        list->first = new ListElement{node, oldListFirst};
        return;
    }
    while (current->next && current->next->node->countOfSame < node->countOfSame)
    {
        current = current->next;
    }
    ListElement *newElement = new ListElement{node, current->next};
    current->next = newElement;
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
    Node *minimum = list->first->node;
    ListElement *toRemove = list->first;
    list->first = list->first->next;
    delete toRemove;
    return minimum;
}
