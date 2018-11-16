#include <iostream>
#include "binaryTree.h"
#include "AVLSet.h"

using namespace std;

void addNewElement(BinaryTree *binaryTree)
{
    cout << "Enter new element to add: ";
    int newElement = 0;
    cin >> newElement;
    add(binaryTree, newElement);
}

void removeElement(BinaryTree *binaryTree)
{
    cout << "Enter the element to remove: ";
    int toDelete = 0;
    cin >> toDelete;
    remove(binaryTree, toDelete);
}

void elementExist(BinaryTree *binaryTree)
{
    cout << "Enter the element to search: ";
    int toSearch = 0;
    cin >> toSearch;
    bool result = isElementBelongs(binaryTree, toSearch);
    if (result)
    {
        cout << "Element is in the tree" << endl;
    }
    else
    {
        cout << "Element is not in the tree" << endl;
    }
}
