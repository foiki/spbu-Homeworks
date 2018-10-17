#include <fstream>
#include <iostream>
#include "list.h"

using namespace std;

int main()
{
    //const int maxSize = 10000;
    List *list = createList();
    fileRead(list);
    //print(list);
    cout << "Enter the command if you now how it works or type '5' to see help menu";
    int newRequest = -1;
    while (newRequest != 0)
    {
        cout << "Enter new request: " << endl;
        cin >> newRequest;
        if (newRequest == 0)
        {
            deleteList(list);
            cout << "ololo, bye)";
            return 0;
        }
        else
        {
            if (newRequest == 1)
            {
                firstRequest(list);
            }
            else
            {
                if (newRequest == 2)
                {
                    secondRequest(list);
                }
                else
                {
                    if (newRequest == 3)
                    {
                        thirdRequest(list);
                    }
                    else
                    {
                        if (newRequest == 4)
                        {
                            fourthRequest(list);
                        }
                        else
                        {
                            if (newRequest == 5)
                            {
                                cout << "Enter '0' to exit" << endl;
                                cout << "Enter '1' to add new note to phonebook" << endl;
                                cout << "Enter '2' to find the phone number if you know the name of it's owner" << endl;
                                cout << "Enter '3' if you know the number and want to find the name of it's owner" << endl;
                                cout << "Enter '4' to to save changes in 'phoneBook.txt'" << endl;
                                cout << "Enter '5' to see help menu" << endl;
                            }
                        }
                    }
                }
            }
        }
    }
    return 0;
}
