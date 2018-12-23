#pragma once
#include "graph.hpp"
#include "queue.hpp"

double euristicFunction(Node *from, Node *to);
Node *findClosestPoint(Queue *queue, int **distances, Node *end);
bool isNewDistanceLess(int **distances, Node *point, int newX, int newY);
void updateDistance(Graph *graph, Node *point, Node ***previousPoints, int **distances, int newX, int newY);
void updateDistance(Graph *graph, Node *point, Node ***previousPoints, int **distances);
void checkNewPoint(Graph *graph, Queue *queue, bool **used, int x, int y);
void addNeighboursToQueue(Graph *graph, Queue *queue, bool **used, Node *current);
void aStar(Graph *graph, Node *start, Node *end);
