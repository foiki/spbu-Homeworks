#include <iostream>
#include <fstream>
#include <string.h>

using namespace std;

const int lengthOfDate = 10;

bool isNumber(char symbol)
{
    if (symbol >= '0' && symbol <= '9')
    {
        return 1;
    }
    return 0;
}

bool isDateReal(int day, int month)
{
    switch (month) {
        case 0:
            return 0;
        case 1:
            if (day <= 31)
            {
                return 1;
            }
            return 0;
        case 2:
            if (day <= 28)
            {
                return 1;
            }
            return 0;
        case 3:
            if (day <= 31)
            {
                return 1;
            }
            return 0;
        case 4:
            if (day <= 30)
            {
                return 1;
            }
            return 0;
        case 5:
            if (day <= 31)
            {
                return 1;
            }
            return 0;
        case 6:
            if (day <= 30)
            {
                return 1;
            }
            return 0;
        case 7:
            if (day <= 31)
            {
                return 1;
            }
            return 0;
        case 8:
            if (day <= 31)
            {
                return 1;
            }
            return 0;
        case 9:
            if (day <= 30)
            {
                return 1;
            }
            return 0;
        case 10:
            if (day <= 31)
            {
                return 1;
            }
            return 0;
        case 11:
            if (day <= 30)
            {
                return 1;
            }
            return 0;
        case 12:
            if (day <= 31)
            {
                return 1;
            }
            return 0;
        default:
            return 0;
    }
}

bool isDate(char *string)
{
    if (strlen(string) == 10)
    {
        if (string[2] == '.' && string[5] == '.')
        {
            if (isNumber(string[0]) && isNumber(string[1]) && isNumber(string[3]) && isNumber(string[4]) && isNumber(string[6]) && isNumber(string[7]) && isNumber(string[8]) && isNumber(string[9]))
            {
                int newDay = (string[0] - '0') * 10 + (string[1] - '0');
                int newMonth = (string[3] - '0') * 10 + (string[4] - '0');
                if (isDateReal(newDay, newMonth))
                {
                    return 1;
                }
            }
        }
    }
    return 0;
}

void searchMinumumDate(char *newDate, char *minimumDate, bool isSomethingAlreadyTested)
{
    if (!isSomethingAlreadyTested)
    {
        strncpy(minimumDate, newDate, 10);
    }
    else
    {
        int newYear = (newDate[6] - '0') * 1000 + (newDate[7] - '0') * 100 + (newDate[8] - '0') * 10 + (newDate[9] - '0');
        int minimumYear = (minimumDate[6] - '0') * 1000 + (minimumDate[7] - '0') * 100 + (minimumDate[8] - '0') * 10 + (minimumDate[9] - '0');
        if (newYear == minimumYear)
        {
            int newMonth = (newDate[3] - '0') * 10 + (newDate[4] - '0');
            int minimumMonth = (minimumDate[3] - '0') * 10 + (minimumDate[4] - '0');
            if (newMonth == minimumMonth)
            {
                int newDay = (newDate[0] - '0') * 10 + (newDate[1] - '0');
                int minimumDay = (minimumDate[0] - '0') * 10 + (minimumDate[1] - '0');
                if (newDay < minimumDay)
                {
                    strncpy(minimumDate, newDate, 10);
                }
            }
            else if (newMonth < minimumMonth)
            {
                strncpy(minimumDate, newDate, 10);
            }
        }
        else if (newYear < minimumYear)
        {
            strncpy(minimumDate, newDate, 10);
        }
    }
}

int main()
{
    ifstream fin("File.txt");
    char *newWord = new char[lengthOfDate];
    char *minimumDate = new char[lengthOfDate];
    bool isSomethingAlreadyTested = false;
    if (fin.is_open())
    {
        while(!fin.eof())
        {
            fin >> newWord;
            if (!fin.eof())
            {
                if (isDate(newWord))
                {
                    searchMinumumDate(newWord, minimumDate, isSomethingAlreadyTested);
                    isSomethingAlreadyTested = true;
                }
            }
        }
    }
    else
    {
        cout << "File not found!" << endl;
    }
    fin.close();
    if (isSomethingAlreadyTested)
    {
        cout << "Minimum date is: ";
        for (int i = 0; i < lengthOfDate; ++i)
        {
            cout << minimumDate[i];
        }
        cout << endl;
    }
    else
    {
        cout << "Date has not been found!" << endl;
    }
    delete[] newWord;
    delete[] minimumDate;
    return 0;
}
