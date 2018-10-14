#pragma once

struct ListElement
{
    int position;
    bool killed;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void add(List *list, int value);
void connect(List *list, int number);
void deleteCircledList(List *list);
void print(List *list);
int findLastAlive(List *list);
