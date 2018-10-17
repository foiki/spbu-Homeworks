#pragma once
#include <fstream>

using namespace std;

const int maxSize = 10000;

struct ListElement
{
    char name[maxSize];
    long long number;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);
void fileRead(List *list);
void filePrint(List *list);
int min(long long a, long long b);
void add(List *list, char newName[], long long newNumber);
bool charComparsion(char a[], char b[]);
bool alphabetical(char a[], char b[]);
void assignment(char a[], char b[]);
void phoneNumberFinder(List *list, char a[]);
void nameFinder(List *list, long long x);
void firstRequest(List *list);
void secondRequest(List *list);
void thirdRequest(List *list);
void fourthRequest(List *list);
