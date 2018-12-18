#pragma once
#include <fstream>
#include <cmath>
#include "queue.hpp"

using namespace std;

struct Graph
{
    int size;
    int **matrix;
};

Graph *createGraph();
void deleteGraph(Graph *graph);
void deleteIntMatrix(int **matrix, int size);
void graphRead(Graph *graph, ifstream &fin);
void graphPrint(Graph *graph);
bool **createBoolMatrix(int size);
void deleteBoolMatrix(bool **matrix, int size);
int **createIntMatrix(int size);
bool isFree(int valueOfPoint);
Node ***createNodeMatrix(int size);
void deleteNodeMatrix(Node ***matrix, int size);
double euristicFunction(Node *from, Node *to);
Node *findClosestPoint(Queue *queue, int **distances, Node *end);
bool isPointAvailable(Graph *graph, int x, int y, int size);
bool isNewDistanceLess(int **distances, Node *point, int newX, int newY);
void updateDistance(Graph *graph, Node *point, Node ***previousPoints, int **distances, int newX, int newY);
void updateDistance(Graph *graph, Node *point, Node ***previousPoints, int **distances);
void checkNewPoint(Graph *graph, Queue *queue, bool **used, int x, int y);
void addNeighboursToQueue(Graph *graph, Queue *queue, bool **used, Node *current);
