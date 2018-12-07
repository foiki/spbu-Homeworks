#include <iostream>
#include <fstream>
#include "civilian.hpp"

using namespace std;

int main()
{
    int toKill = 0;
    int toExile = 0;
    cout << "Enter how many people should be killed: ";
    cin >> toKill;
    cout << "Enter how many people should exiled to Siberia: ";
    cin >> toExile;
    ifstream fin("input.txt");
    List *list = createList();
    fileRead(list, fin);
    killMostDangerous(list, toKill);
    exilePeople(list, toExile);
    cout << endl;
    listPrint(list);
    deleteList(list);
    return 0;
}
