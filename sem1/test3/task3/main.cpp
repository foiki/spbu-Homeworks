#include <iostream>
#include <fstream>
#include "graph.hpp"

int findNewCity(Graph *graph, int currentIndex)
{
    for (int i = 0; i < graph->numberOfNodes; ++i)
    {
        if (graph->incidenceMatrix[i][currentIndex] == 1)
        {
            return i;
        }
    }
    return 0;
}

void howWeCanGetHere(Graph *graph, int i, bool *visited)
{
    for (int j = 0; j < graph->numberOfRibs; ++j)
    {
        if (graph->incidenceMatrix[i][j] == -1)
        {
            int numberOfNewCity = findNewCity(graph, j);
            if (!visited[numberOfNewCity])
            {
                visited[numberOfNewCity] = true;
                howWeCanGetHere(graph, numberOfNewCity, visited);
            }
        }
    }
}

void findNodes(Graph *graph)
{
    for (int i = 0; i < graph->numberOfNodes; ++i)
    {
        bool *visited = new bool[graph->numberOfNodes]{false};
        visited[i] = true;
        howWeCanGetHere(graph, i, visited);
        bool isCorrectNode = true;
        for (int j = 0; j < graph->numberOfNodes; ++j)
        {
            if (!visited[j])
            {
                isCorrectNode = false;
            }
        }
        if (isCorrectNode)
        {
            cout << i << endl;
        }
        delete[] visited;
    }
}

int main()
{
    ifstream fin("File.txt");
    if (!fin.is_open())
    {
        cout << "'File.txt' not found!" << endl;
        return 0;
    }
    Graph *graph = createGraph();
    readMatrixFromFile(graph, fin);
    findNodes(graph);
    deleteGraph(graph);
    return 0;
}
