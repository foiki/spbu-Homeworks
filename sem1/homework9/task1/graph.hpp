#pragma once
#include <fstream>

using namespace std;

struct Graph
{
    int size;
    int **adjacencyMatrix;
    int numberOfStates;
    int **states;
};

Graph *createGraph(int numberOfObjects);
void deleteGraph(Graph *graph);
void statesRead(Graph *graph, ifstream &fin);
Graph *graphRead(ifstream &fin);
void graphPrint(Graph *graph);
int numberOfNearestFreeCity(int *roads, int size, bool *used);
int lengthToNearestFreeCity(int *roads, int size, bool *used);
int numberOfNearestFreeCity(Graph *graph, int current, bool *used);
void findingNewCities(Graph *graph);
void printStates(Graph *graph);
