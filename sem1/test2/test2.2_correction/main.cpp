#include <iostream>
#include "list.hpp"

using namespace std;

int main()
{
    int number = 0;
    cout << "Enter number of elements: ";
    cin >> number;
    List *list = createList();
    cout << "Enter elements:" << endl;
    for (int i = 0; i < number; i++)
    {
        int newElement = 0;
        cin >> newElement;
        add(list, newElement);
    }
    insertionSort(list);
    printList(list);
}
