#include "graph.hpp"

Graph *createGraph()
{
    return new Graph {nullptr, 0, 0};
}

void deleteGraph(Graph *graph)
{
    if (graph->incidenceMatrix)
    {
        for (int i = 0; i < graph->numberOfNodes; ++i)
        {
            delete[] graph->incidenceMatrix[i];
        }
        delete[] graph->incidenceMatrix;
    }
    delete graph;
}

void readMatrixFromFile(Graph *graph, ifstream &fin)
{
    fin >> graph->numberOfNodes;
    fin >> graph->numberOfRibs;
    graph->incidenceMatrix = new int*[graph->numberOfNodes];
    for (int i = 0; i < graph->numberOfNodes; ++i)
    {
        graph->incidenceMatrix[i] = new int[graph->numberOfRibs];
        for (int j = 0; j < graph->numberOfRibs; ++j)
        {
            fin >> graph->incidenceMatrix[i][j];
        }
    }
}
