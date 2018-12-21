#pragma once
#include <fstream>

using namespace std;

struct Graph
{
    int **incidenceMatrix;
    int numberOfNodes;
    int numberOfRibs;
};

Graph *createGraph();
void deleteGraph(Graph *graph);
void readMatrixFromFile(Graph *graph, ifstream &fin);
