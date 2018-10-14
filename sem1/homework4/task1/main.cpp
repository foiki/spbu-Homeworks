#include <iostream>
#include "list.h"

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
        add(list, i);
    }
    connect(list, number);
    int killedInThisCircle = 0;
    int j = 1;
    ListElement *current = list->first;
    while(number - killedInThisCircle > 1)
    {
        if (j % spot == 0 && !current->killed)
        {
            current->killed = true;
            ++killedInThisCircle;
        }
        current = current->next;
        ++j;
        if (j % spot == 0 && current->killed)
        {
            --j;
        }
        if (j >= number && killedInThisCircle > 0)
        {
            j = 1;
            number -= killedInThisCircle;
            killedInThisCircle = 0;
            current = list->first;
        }
    }
    cout << "Last alive: " << findLastAlive(list) << endl;
    deleteCircledList(list);
}
