#include <iostream>
#include <fstream>
#include "civilian.hpp"
#include "list.hpp"

void fileRead(List *list, ifstream &fin)
{
    if (!fin.is_open())
    {
        cout << "File not found!" << endl;
        return;
    }
    while (!fin.eof())
    {
        char newSurname[maxSize]{0};
        long newLoyalty = 0;
        char ch;
        fin >> newSurname >> ch >> newLoyalty;
        if (!fin.eof())
        {
            add(list, newSurname, newLoyalty);
        }
    }
}

void killMostDangerous(List *list, int toKill)
{
    ListElement *current = list->first;
    while (current)
    {
        if (current->loyalty >= toKill)
        {
            current->whatToDo = 1;
        }
        current = current->next;
    }
}

void exilePeople(List *list, int toExile)
{
    ListElement *current = list->first;
    while (current && toExile > 0)
    {
        if (current->whatToDo == 0)
        {
            current->whatToDo = 2;
            --toExile;
        }
        current = current->next;
    }
}
