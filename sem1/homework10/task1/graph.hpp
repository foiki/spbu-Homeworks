#pragma once
#include <iostream>
#include <fstream>
#include <cmath>
#include "matrixFunctions.hpp"

using namespace std;

struct Graph
{
    int size;
    int **matrix;
};

Graph *createGraph();
void deleteGraph(Graph *graph);
void graphRead(Graph *graph, ifstream &fin);
void graphPrint(Graph *graph);
bool isPointAvailable(Graph *graph, int x, int y);
bool isSamePoint(Node *first, Node *second);
bool isPointFree(int valueOfPoint);
