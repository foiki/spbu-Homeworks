#include <iostream>
#include "binaryTree.h"
#include "ATDSet.h"

using namespace std;

ATDSet *createSet()
{
    return new ATDSet {createTree()};
}

void deleteSet(ATDSet *set)
{
    deleteTree(set->tree);
    delete[] set;
}

void addNewElement(ATDSet *set)
{
    cout << "Enter new element to add: ";
    int newElement = 0;
    cin >> newElement;
    add(set->tree, newElement);
}

void removeElement(ATDSet *set)
{
    cout << "Enter the element to remove: ";
    int toDelete = 0;
    cin >> toDelete;
    remove(set->tree, toDelete);
}

void elementExist(ATDSet *set)
{
    cout << "Enter the element to search: ";
    int toSearch = 0;
    cin >> toSearch;
    bool result = isElementBelongs(set->tree, toSearch);
    if (result)
    {
        cout << "Element is in the tree" << endl;
    }
    else
    {
        cout << "Element is not in the tree" << endl;
    }
}

void printInAscendingOrder(ATDSet *set)
{
    printInAscendingOrder(set->tree);
}

void printInDescendingOrder(ATDSet *set)
{
    printInAscendingOrder(set->tree);
}

void printInABCFormat(ATDSet *set)
{
    printInABCFormat(set->tree);
}


