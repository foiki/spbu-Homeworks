#include "aStar.hpp"

int main()
{
    ifstream fin("File.txt");
    if (!fin.is_open())
    {
        cout << "'File.txt' not found!" << endl;
        return 0;
    }
    int startX = 0;
    int startY = 0;
    fin >> startX >> startY;
    Node *start = new Node {startX, startY};
    int endX = 0;
    int endY = 0;
    fin >> endX >> endY;
    Node *end = new Node {endX, endY};
    Graph *graph = createGraph();
    fin >> graph->size;
    graphRead(graph, fin);
    aStar(graph, start, end);
    deleteGraph(graph);
    delete end;
    return 0;
}
