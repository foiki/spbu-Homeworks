#include "aStar.hpp"

void aStar(Graph *graph, Node *start, Node *end)
{
    if (!isFree(graph->matrix[end->x][end->y]))
    {
        cout << "You can not go to the point END" << endl;
        return;
    }
    Queue *open = createQueue();
    bool **used = createBoolMatrix(graph->size);
    add(open, start);
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
    graph->matrix[start->x][start->y] = 'S';
    graph->matrix[end->x][end->y] = 'E';
    graphPrint(graph);
    delete current;
    deleteQueue(open);
    deleteIntMatrix(distances, graph->size);
    deleteBoolMatrix(used, graph->size);
    deleteNodeMatrix(previousPoints, graph->size);
}
