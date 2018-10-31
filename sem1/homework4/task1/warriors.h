#pragma once

struct ListElement
{
    int position;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void addCircled(List *list, int value, int maxNumber);
void killNext(List *list, ListElement *&current);
void deleteCircledList(List *list, ListElement *current);
void print(List *list);
int findLastAlive(List *list);
