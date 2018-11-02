#include <iostream>
#include <fstream>

using namespace std;

int main()
{
    ifstream fin("input.txt");
    const int maxSize = 1000;
    int array[maxSize] {0};
    if (fin.is_open())
    {
        while (!fin.eof())
        {
            char letter =' ';
            fin >> letter;
            if (letter >= 'a' && letter <= 'z')
            {
                ++array[letter];
            }
        }
        ofstream fout("output.txt");
        if (fout.is_open())
        {
            for (int i = 'a'; i <= 'z'; ++i)
            {
                if (array[i] > 0)
                {
                    fout << char(i) << " " << array[i] << endl;
                }
            }
        }
        else
        {
            cout << "Output file not found!" << endl;
        }
    }
    else
    {
        cout << "Input file not found!" << endl;
    }
    return 0;
}
