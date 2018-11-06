#include <iostream>
#include "warriors.h"

using namespace std;

int main()
{
    int number = 0;
    int spot = 0;
    cout << "Enter the number of warriors and position on which every one should be killed: " << endl;
    cin >> number >> spot;
    List *list = createList();
    for (int i = 1; i <= number; ++i)
    {
        addCircled(list, i, number);
    }
    int j = 1;
    ListElement *current = list->first;
    while (number > 1)
    {
        if (j % spot == 0)
        {
            killNext(list, current);
            --number;
        }
        current = current->next;
        ++j;
    }
    cout << "Last Alive: " << current->position << endl;
    delete current;
    delete list;
    return 0;
}
