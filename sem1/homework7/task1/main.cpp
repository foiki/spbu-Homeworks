#include <iostream>
#include "ATDSet.h"

using namespace std;

int main()
{
    ATDSet *newSet = createSet();
    cout << "Empty set has been created." << endl;
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
                addNewElement(newSet);
                break;
            case 2:
                removeElement(newSet);
                break;
            case 3:
                elementExist(newSet);
                break;
            case 4:
                printInAscendingOrder(newSet);
                break;
            case 5:
                printInDescendingOrder(newSet);
                break;
            case 6:
                printInABCFormat(newSet);
                break;
            case 7:
                cout << "Enter '1' to add new element" << endl;
                cout << "Enter '2' to add remove element" << endl;
                cout << "Enter '3' to search element in tree" << endl;
                cout << "Enter '4' to print set in ascendind order" << endl;
                cout << "Enter '5' to print set in descendind order" << endl;
                cout << "Enter '6' to print set in ABC format" << endl;
        }
    }
    deleteSet(newSet);
    return 0;
}
