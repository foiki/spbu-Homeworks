#pragma once
#include <fstream>
#include "list.h"

using namespace std;

void fileRead(List *list, ifstream &fin);
void filePrint(List *list, ofstream &fout);
int min(long long a, long long b);
bool areArraysEqual(char a[], char b[]);
bool isFirstAlphabeticalAbove(char a[], char b[]);
void approriateSecondInFirst(char a[], char b[]);
void findPhoneNumber(List *list, char a[]);
void findName(List *list, long long x);
void addNewContact(List *list);
void findPhoneNumber(List *list);
void findName(List *list);
void saveChangesInFile(List *list, ofstream &fout);
