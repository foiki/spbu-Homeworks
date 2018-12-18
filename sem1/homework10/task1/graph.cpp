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

void deleteIntMatrix(int **matrix, int size)
{
    for (int i = 0; i < size; ++i)
    {
        delete[] matrix[i];
    }
    delete[] matrix;
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

bool **createBoolMatrix(int size)
{
    bool **matrix = new bool*[size];
    for (int i = 0; i < size; ++i)
    {
        matrix[i] = new bool[size]{false};
    }
    return matrix;
}

void deleteBoolMatrix(bool **matrix, int size)
{
    for (int i = 0; i < size; ++i)
    {
        delete[] matrix[i];
    }
    delete[] matrix;
}

int **createIntMatrix(int size)
{
    int **matrix = new int*[size];
    for (int i = 0; i < size; ++i)
    {
        matrix[i] = new int[size];
        for (int j = 0; j < size; ++j)
        {
            matrix[i][j] = -1;
        }
    }
    return matrix;
}

bool isFree(int valueOfPoint)
{
    return (valueOfPoint == 0);
}

Node ***createNodeMatrix(int size)
{
    Node ***matrix = new Node**[size];
    for (int i = 0; i < size; ++i)
    {
        matrix[i] = new Node*[size];
        for (int j = 0; j < size; ++j)
        {
            matrix[i][j] = new Node{-1, -1};
        }
    }
    return matrix;
}

void deleteNodeMatrix(Node ***matrix, int size)
{
    for (int i = 0; i < size; ++i)
    {
        for (int j = 0; j < size; ++j)
        {
            delete matrix[i][j];
        }
        delete[] matrix[i];
    }
    delete[] matrix;
}

double euristicFunction(Node *from, Node *to)
{
    return sqrt((from->x - to->x) * (from->x - to->x) + (from->y - to->y) * (from->y - to->y));
}

Node *findClosestPoint(Queue *queue, int **distances, Node *end)
{
    double minimumEuristicDistance = -1.0;
    QueueElement *current = queue->first;
    Node *minimumPoint = current->node;
    while (current)
    {
        int newEuristicDistance = distances[current->node->x][current->node->y] + euristicFunction(current->node, end);
        if (minimumEuristicDistance == -1 || newEuristicDistance < minimumEuristicDistance)
        {
            minimumPoint = current->node;
            minimumEuristicDistance = newEuristicDistance;
        }
        current = current->next;
    }
    return minimumPoint;
}

bool isPointAvailable(Graph *graph, int x, int y)
{
    return (x >= 0 && y >= 0 && x < graph->size && y < graph->size && graph->matrix[x][y] == 0);
}

bool isNewDistanceLess(int **distances, Node *point, int newX, int newY)
{
    return (distances[newX][newY] == -1 || distances[point->x][point->y] + 1 < distances[newX][newY]);
}

void updateDistance(Graph *graph, Node *point, Node ***previousPoints, int **distances, int newX, int newY)
{
    if (isPointAvailable(graph, newX, newY) && isNewDistanceLess(distances, point, newX, newY))
    {
        distances[newX][newY] = distances[point->x][point->y] + 1;
        previousPoints[newX][newY]->x = point->x;
        previousPoints[newX][newY]->y = point->y;
    }
}

void updateDistance(Graph *graph, Node *point, Node ***previousPoints, int **distances)
{
    updateDistance(graph, point, previousPoints, distances, point->x + 1, point->y);
    updateDistance(graph, point, previousPoints, distances, point->x - 1, point->y);
    updateDistance(graph, point, previousPoints, distances, point->x, point->y + 1);
    updateDistance(graph, point, previousPoints, distances, point->x, point->y - 1);
    
}

void checkNewPoint(Graph *graph, Queue *queue, bool **used, int x, int y)
{
    if (isPointAvailable(graph, x, y) && !used[x][y])
    {
        Node *newNode = new Node {x, y};
        if (!exist(queue, newNode))
        {
            add(queue, newNode);
        }
        else
        {
            delete newNode;
        }
    }
}

void addNeighboursToQueue(Graph *graph, Queue *queue, bool **used, Node *current)
{
    checkNewPoint(graph, queue, used, current->x - 1, current->y);
    checkNewPoint(graph, queue, used, current->x, current->y - 1);
    checkNewPoint(graph, queue, used, current->x + 1, current->y);
    checkNewPoint(graph, queue, used, current->x, current->y + 1);
}
