#include <iostream>

using namespace std;

int findOriginal(int *array, int number)
{
    if (number == 1 || number == 2 || number == 3)
    {
        return number;
    }
    else if (array[number] == -1)
    {
        return -1;
    }
    return findOriginal(array, array[number]);
}

int main()
{
    cout << "Enter count of students: ";
    int number = 0;
    cin >> number;
    cout << "Enter the pairs of students and numbers of their homeworks without first three:" << endl;
    int *array = new int[number + 1] {0};
    for (int i = 1; i <= 3; ++i)
    {
        array[i] = i;
    }
    for (int i = 0; i < number - 3; ++i)
    {
        int numberOfStudent = 0;
        cin >> numberOfStudent;
        int numberOfHomework = 0;
        cin >> numberOfHomework;
        array[numberOfStudent] = numberOfHomework;
    }
    cout << endl;
    for (int i = 1; i <= number; i++)
    {
        array[i] = findOriginal(array, i);
        cout << i << " " << array[i] << endl;
    }
    return 0;
}
