#include "graph.hpp"

Graph *createGraph()
{
    return new Graph{0, nullptr};
}

void deleteGraph(Graph *graph)
{
    deleteIntMatrix(graph->matrix, graph->size);
    delete graph;
}

void graphRead(Graph *graph, ifstream &fin)
{
    graph->matrix = new int*[graph->size];
    for (int i = 0; i < graph->size; ++i)
    {
        graph->matrix[i] = new int[graph->size];
        for (int j = 0; j < graph->size; ++j)
        {
            fin >> graph->matrix[i][j];
        }
    }
}

void graphPrint(Graph *graph)
{
    for (int i = 0; i < graph->size; ++i)
    {
        for (int j = 0; j < graph->size; ++j)
        {
            if (graph->matrix[i][j] == '*' || graph->matrix[i][j] == 'S' || graph->matrix[i][j] == 'E')
            {
                cout << (char)graph->matrix[i][j] << " ";
            }
            else
            {
                cout << graph->matrix[i][j] << " ";
            }
        }
        cout << endl;
    }
}


bool isPointAvailable(Graph *graph, int x, int y)
{
    return (x >= 0 && y >= 0 && x < graph->size && y < graph->size && graph->matrix[x][y] == 0);
}

bool isSamePoint(Node *first, Node *second)
{
    return ((first->x == second->x) && (first->y == second->y));
}

bool isPointFree(int valueOfPoint)
{
    return (valueOfPoint == 0);
}
