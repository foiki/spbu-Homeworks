#include <iostream>
#include "list.hpp"

using namespace std;

int main()
{
    cout << "Enter the number of elements: ";
    int number = 0;
    cin >> number;
    cout << "Enter the elements: " << endl;
    List *list = createList();
    for (int i = 0; i < number; ++i)
    {
        int newElement = 0;
        cin >> newElement;
        add(list, newElement);
    }
    selectionSort(list);
    cout << "Result: " << endl;
    printList(list);
    deleteList(list);
}
