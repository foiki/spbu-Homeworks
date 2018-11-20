#include <iostream>
#include "ATD.h"

using namespace std;

int main()
{
    Tree *tree = createTree();
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
                addNewElement(tree);
                break;
            case 2:
                removeElement(tree);
                break;
            case 3:
                elementExist(tree);
                break;
            case 4:
                printInAscendingOrder(tree);
                break;
            case 5:
                printInDescendingOrder(tree);
                break;
            case 6:
                printInABCFormat(tree);
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
    deleteTree(tree);
    return 0;
}
