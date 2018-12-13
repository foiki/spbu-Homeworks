#pragma once
#include <iostream>
#include "huffmanTree.hpp"

const int maxSize = 10000;

struct ListElement
{
    Node *node;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteListElement(ListElement *toDelete);
void deleteList(List *list);
void add(List *list, Node *node);
void remove(List *list, ListElement *&current);
void printList(List *list);
Node *findMinimum(List *list);
