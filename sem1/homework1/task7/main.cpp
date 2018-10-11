#include <iostream>

using namespace std;

int main()
{
    int n = 0;
    cin >> n;
    bool erato[n + 1];
    for (int i = 0; i <= n; ++i)
    {
        erato[i] = true;
    }
    for (int i = 2; i <= n; ++i){
        for (int j = i * i; j <= n; j += i)
        {
            erato[j] = false;
        }
    }
    for (int i = 2; i <= n; ++i){
        if (erato[i])
        {
            cout << i << " ";
        }
    }
    cout << endl;
    return 0;
}
