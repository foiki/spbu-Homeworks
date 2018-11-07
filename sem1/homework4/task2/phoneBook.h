#pragma once
#include <fstream>

using namespace std;

const int maxSize = 10000;

struct PhoneBookElement
{
    char name[maxSize];
    long long number;
    PhoneBookElement *next;
};

struct PhoneBook
{
    PhoneBookElement *first;
};

PhoneBook *createPhoneBook();
void deleteList(PhoneBook *phoneBook);
void fileRead(PhoneBook *phoneBook, ifstream &fin);
void filePrint(PhoneBook *phoneBook, ofstream &fout);
int min(long long a, long long b);
void add(PhoneBook *phoneBook, char newName[], long long newNumber);
bool areArraysEqual(char a[], char b[]);
bool isFirstAlphabeticalAbove(char a[], char b[]);
void approriateSecondInFirst(char a[], char b[]);
void findPhoneNumber(PhoneBook *phoneBook, char a[]);
void findName(PhoneBook *phoneBook, long long x);
void addNewContact(PhoneBook *phoneBook);
void findPhoneNumber(PhoneBook *phoneBook);
void findName(PhoneBook *phoneBook);
void saveChangesInFile(PhoneBook *phoneBook, ofstream &fout);
