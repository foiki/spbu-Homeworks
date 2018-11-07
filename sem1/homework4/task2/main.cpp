#include <fstream>
#include <iostream>
#include "phoneBook.h"

using namespace std;

int main()
{
    PhoneBook *phoneBook = createPhoneBook();
    ifstream fin("phoneBook.txt");
    fileRead(phoneBook, fin);
    fin.close();
    cout << "Enter the command if you now how it works or type '5' to see help menu" << endl;
    int newRequest = -1;
    ofstream fout("phoneBook.txt");
    while (newRequest != 0)
    {
        cout << "Enter new request: " << endl;
        cin >> newRequest;
        switch(newRequest)
        {
            case 0:
                deleteList(phoneBook);
                cout << "ololo, bye)" << endl;
                return 0;
            case 1:
                addNewContact(phoneBook);
                break;
            case 2:
                findPhoneNumber(phoneBook);
                break;
            case 3:
                findName(phoneBook);
                break;
            case 4:
                saveChangesInFile(phoneBook, fout);
                fout.close();
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
