#include "aStar.hpp"

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
    return new Node {minimumPoint->x, minimumPoint->y};
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

void aStar(Graph *graph, Node *start, Node *end)
{
    if (!isPointFree(graph->matrix[end->x][end->y]))
    {
        cout << "You can not go to the point END" << endl;
        return;
    }
    Queue *open = createQueue();
    bool **used = createBoolMatrix(graph->size);
    add(open, new Node {start->x, start->y});
    Node ***previousPoints = createNodeMatrix(graph->size);
    previousPoints[start->x][start->y]->x = start->x;
    previousPoints[start->x][start->y]->y = start->y;
    int **distances = createIntMatrix(graph->size);
    distances[start->x][start->y] = 0;
    bool isWayExist = false;
    while (!isQueueEmpty(open) && !isWayExist)
    {
        Node *current = findClosestPoint(open, distances, end); //findPointWithMinimumEuristicDistance
        if (isSamePoint(current, end))
        {
            isWayExist = true;
        }
        else
        {
            remove(open, current);
            used[current->x][current->y] = true;
            updateDistance(graph, current, previousPoints, distances);
            addNeighboursToQueue(graph, open, used, current);
        }
        delete current;
    }
    deleteQueue(open);
    if (!isWayExist)
    {
        cout << "There is no way between 'start' and 'end'!" << endl;
        return;
    }
    cout << "Shortest distance: " << distances[end->x][end->y] << endl;
    Node *current = new Node {end->x, end->y};
    cout << "Shortest distance marked as '*'" << endl;
    while (!isSamePoint(current, start))
    {
        graph->matrix[current->x][current->y] = '*';
        int lastX = current->x;
        current->x = previousPoints[current->x][current->y]->x;
        current->y = previousPoints[lastX][current->y]->y;
    }
    delete current;
    graph->matrix[start->x][start->y] = 'S';
    graph->matrix[end->x][end->y] = 'E';
    graphPrint(graph);
    deleteIntMatrix(distances, graph->size);
    deleteBoolMatrix(used, graph->size);
    deleteNodeMatrix(previousPoints, graph->size);
}
