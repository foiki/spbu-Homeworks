#include <iostream>

using namespace std;

int main()
{
    int allAmounts[28];
    for (int i = 0; i < 28; ++i)
    {
        allAmounts[i] = 0;
    }
    for (int q = 0; q < 10; ++q)
    {
        for (int w = 0; w < 10; ++w)
        {
            for (int e = 0; e < 10; ++e)
            {
                ++allAmounts[q + w + e];
            }
        }
    }
    int count = 0;
    for (int i = 0; i < 28; ++i)
    {
        count += allAmounts[i] * allAmounts[i];
    }
    cout << count << endl;
    return 0;
}
