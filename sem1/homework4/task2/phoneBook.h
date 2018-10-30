#pragma once

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
bool areArraysEqual(char a[], char b[]);
bool isFirstAlphabeticalAbove(char a[], char b[]);
void approriateSecondInFirst(char a[], char b[]);
void findPhoneNumber(List *list, char a[]);
void findName(List *list, long long x);
void addNewContact(List *list);
void findPhoneNumber(List *list);
void findName(List *list);
void saveChangesInFile(List *list);
