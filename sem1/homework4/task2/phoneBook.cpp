#include <iostream>
#include <fstream>
#include "phoneBook.h"

using namespace std;

List *createList()
{
    return new List {nullptr};
}

void deleteList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        ListElement *nextElement = current->next;
        delete current;
        current = nextElement;
    }
    delete list;
}

void fileRead(List *list)
{
    ifstream fin("phoneBook.txt");
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
    fin.close();
}

void filePrint(List *list)
{
    ofstream fout("phoneBook.txt");
    ListElement *current = list->first;
    while (current)
    {
        fout << current->name << " " << current->number << endl;
        current = current->next;
    }
    fout.close();
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

void add(List *list, char newName[], long long newNumber)
{
    ListElement *current = list->first;
    if ((list->first == nullptr) || !isFirstAlphabeticalAbove(current->name, newName))
    {
        ListElement *oldListFirst = list->first;
        list->first = new ListElement;
        approriateSecondInFirst(list->first->name, newName);
        list->first->number = newNumber;
        list->first->next = oldListFirst;
        return;
    }
    while ((current->next) && (isFirstAlphabeticalAbove(current->next->name, newName)))
    {
        current = current->next;
    }
    ListElement *newElement = new ListElement;
    approriateSecondInFirst(newElement->name, newName);
    newElement->number = newNumber;
    newElement->next = current->next;
    current->next = newElement;
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

void saveChangesInFile(List *list)
{
    filePrint(list);
    cout << "All changes has been saved" << endl;
}
