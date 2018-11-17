#include <iostream>
#include <fstream>
#include <string.h>
#include "phoneBook.h"

using namespace std;

void fileRead(List *list, ifstream &fin)
{
    char newName[maxSize] {0};
    long long newNumber = 0;
    while (!fin.eof())
    {
        fin >> newName >> newNumber;
        if (!fin.eof())
        {
            add(list, newName, newNumber);
        }
    }
}

void filePrint(List *list, ofstream &fout)
{
    ListElement *current = list->first;
    while (current)
    {
        fout << current->name << " " << current->number << endl;
        current = current->next;
    }
}

int min(int a, int b)
{
    if (a > b)
    {
        return b;
    }
    else
    {
        return a;
    }
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

void findPhoneNumber(List *list, char a[])
{
    ListElement *current = list->first;
    while (current)
    {
        if (areArraysEqual(current->name, a))
        {
            cout << a << "  " << current->number << endl;
            return;
        }
        current = current->next;
    }
    if (current == nullptr)
    {
        cout << "Not found!" << endl;
    }
}

void findName(List *list, long long x)
{
    ListElement *current = list->first;
    while (current)
    {
        if (current->number == x)
        {
            cout << current->name << "  " << x << endl;
            return;
        }
        current = current->next;
    }
    if (current == nullptr)
    {
        cout << "Not found!" << endl;
    }
}

void addNewContact(List *list)
{
    cout << "Enter the name and phone number to add to phonebook: " << endl;
    char newName[maxSize] {0};
    long long newNumber = 0;
    cin >> newName >> newNumber;
    add(list, newName, newNumber);
}

void findPhoneNumber(List *list)
{
    cout << "Enter the name: " << endl;
    char newName[maxSize] {0};
    cin >> newName;
    findPhoneNumber(list, newName);
}

void findName(List *list)
{
    cout << "Enter the phone number: " << endl;
    long long newNumber = 0;
    cin >> newNumber;
    findName(list, newNumber);
}

void saveChangesInFile(List *list, ofstream &fout)
{
    filePrint(list, fout);
    cout << "All changes has been saved" << endl;
}
