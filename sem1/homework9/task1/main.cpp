#include <iostream>
#include <fstream>
#include "graph.hpp"

using namespace std;

int main()
{
    ifstream fin("File.txt");
    if (!fin.is_open())
    {
        cout << "File 'File.txt' not found!" << endl;
        return 0;
    }
    Graph *graph = graphRead(fin);
    fin.close();
    findNewCities(graph);
    printStates(graph);
    deleteGraph(graph);
    return 0;
}
