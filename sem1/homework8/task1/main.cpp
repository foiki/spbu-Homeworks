#include <iostream>

using namespace std;

int findOriginal(int *studentsWorks, int number)
{
    if (number == 1 || number == 2 || number == 3)
    {
        return number;
    }
    else if (studentsWorks[number] == -1)
    {
        return -1;
    }
    return findOriginal(studentsWorks, studentsWorks[number]);
}

int main()
{
    cout << "Enter count of students: ";
    int number = 0;
    cin >> number;
    cout << "Enter the pairs of students and numbers of their homeworks without first three:" << endl;
    int *studentsWorks = new int[number + 1] {0};
    for (int i = 1; i <= 3; ++i)
    {
        studentsWorks[i] = i;
    }
    for (int i = 0; i < number - 3; ++i)
    {
        int numberOfStudent = 0;
        cin >> numberOfStudent;
        int numberOfHomework = 0;
        cin >> numberOfHomework;
        studentsWorks[numberOfStudent] = numberOfHomework;
    }
    cout << endl;
    for (int i = 1; i <= number; i++)
    {
        studentsWorks[i] = findOriginal(studentsWorks, i);
        cout << i << " " << studentsWorks[i] << endl;
    }
    delete[] studentsWorks;
    return 0;
}
