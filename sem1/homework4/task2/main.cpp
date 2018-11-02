#include <fstream>
#include <iostream>
#include "phoneBook.h"

using namespace std;

int main()
{
    List *list = createList();
    fileRead(list);
    cout << "Enter the command if you now how it works or type '5' to see help menu" << endl;
    int newRequest = -1;
    while (newRequest != 0)
    {
        cout << "Enter new request: " << endl;
        cin >> newRequest;
        switch(newRequest)
        {
            case 0:
                deleteList(list);
                cout << "ololo, bye)";
                return 0;
            case 1:
                addNewContact(list);
                break;
            case 2:
                findPhoneNumber(list);
                break;
            case 3:
                findName(list);
                break;
            case 4:
                saveChangesInFile(list);
                break;
            case 5:
                cout << "Enter '0' to exit" << endl;
                cout << "Enter '1' to add new note to phonebook" << endl;
                cout << "Enter '2' to find the phone number if you know the name of it's owner" << endl;
                cout << "Enter '3' if you know the number and want to find the name of it's owner" << endl;
                cout << "Enter '4' to to save changes in 'phoneBook.txt'" << endl;
                cout << "Enter '5' to see help menu" << endl;
                break;
        }
    }
    return 0;
}
