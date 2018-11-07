#include <iostream>
#include <fstream>
#include <string.h>
#include "phoneBook.h"

using namespace std;

PhoneBook *createPhoneBook()
{
    return new PhoneBook {nullptr};
}

void deleteList(PhoneBook *phoneBook)
{
    PhoneBookElement *current = phoneBook->first;
    while (current)
    {
        PhoneBookElement *nextElement = current->next;
        delete current;
        current = nextElement;
    }
    delete phoneBook;
}

void fileRead(PhoneBook *phoneBook, ifstream &fin)
{
    char newName[maxSize] {0};
    long long newNumber = 0;
    while (!fin.eof())
    {
        fin >> newName >> newNumber;
        if (!fin.eof())
        {
            add(phoneBook, newName, newNumber);
        }
    }
}

void filePrint(PhoneBook *phoneBook, ofstream &fout)
{
    PhoneBookElement *current = phoneBook->first;
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

void add(PhoneBook *phoneBook, char newName[], long long newNumber)
{
    PhoneBookElement *current = phoneBook->first;
    if ((phoneBook->first == nullptr) || !isFirstAlphabeticalAbove(current->name, newName))
    {
        PhoneBookElement *oldListFirst = phoneBook->first;
        phoneBook->first = new PhoneBookElement;
        approriateSecondInFirst(phoneBook->first->name, newName);
        phoneBook->first->number = newNumber;
        phoneBook->first->next = oldListFirst;
        return;
    }
    while ((current->next) && (isFirstAlphabeticalAbove(current->next->name, newName)))
    {
        current = current->next;
    }
    PhoneBookElement *newElement = new PhoneBookElement;
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

void findPhoneNumber(PhoneBook *phoneBook, char a[])
{
    PhoneBookElement *current = phoneBook->first;
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

void findName(PhoneBook *phoneBook, long long x)
{
    PhoneBookElement *current = phoneBook->first;
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

void addNewContact(PhoneBook *phoneBook)
{
    cout << "Enter the name and phone number to add to phonebook: " << endl;
    char newName[maxSize] {0};
    long long newNumber = 0;
    cin >> newName >> newNumber;
    add(phoneBook, newName, newNumber);
}

void findPhoneNumber(PhoneBook *phoneBook)
{
    cout << "Enter the name: " << endl;
    char newName[maxSize] {0};
    cin >> newName;
    findPhoneNumber(phoneBook, newName);
}

void findName(PhoneBook *phoneBook)
{
    cout << "Enter the phone number: " << endl;
    long long newNumber = 0;
    cin >> newNumber;
    findName(phoneBook, newNumber);
}

void saveChangesInFile(PhoneBook *phoneBook, ofstream &fout)
{
    filePrint(phoneBook, fout);
    cout << "All changes has been saved" << endl;
}
