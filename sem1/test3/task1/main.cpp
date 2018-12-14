#include <iostream>
#include "list.hpp"

using namespace std;

int main()
{
    List *list = createList();
    cout << "Enter the elements: " << endl;
    int newElement = 1;
    while (newElement != 0)
    {
        cin >> newElement;
        if (newElement != 0 && !exist(list, newElement))
        {
            add(list, newElement);
        }
    }
    cout << "Result: " << endl;
    printList(list);
    cout << "The number in brackets indicates the count of uses for each number" << endl;
    deleteList(list);
    return 0;
}
