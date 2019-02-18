#include <iostream>
#include "binaryTree.h"
#include "AVLSet.h"

using namespace std;

int main()
{
    BinaryTree *binaryTree = createTree();
    cout << "Empty binary tree has been created." << endl;
    int newRequest = -1;
    cout << "Enter '7' to see help menu" << endl;
    while (newRequest != 0)
    {
        cout << "Enter new request: ";
        cin >> newRequest;
        switch (newRequest) {
            case 0:
                cout << "Bye:)))" << endl;
                break;
            case 1:
                addNewElement(binaryTree);
                break;
            case 2:
                removeElement(binaryTree);
                break;
            case 3:
                elementExist(binaryTree);
                break;
            case 4:
                printInAscendingOrder(binaryTree);
                break;
            case 5:
                printInDescendingOrder(binaryTree);
                break;
            case 6:
                printInABCFormat(binaryTree);
                break;
            case 7:
                cout << "Enter '1' to add new element" << endl;
                cout << "Enter '2' to add remove element" << endl;
                cout << "Enter '3' to search element in tree" << endl;
                cout << "Enter '4' to print tree in ascendind order" << endl;
                cout << "Enter '5' to print tree in descendind order" << endl;
                cout << "Enter '6' to print tree in ABC format" << endl;
        }
    }
    deleteTree(binaryTree);
    return 0;
}
