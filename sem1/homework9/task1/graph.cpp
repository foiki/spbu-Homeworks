#include <fstream>
#include <iostream>
#include "graph.hpp"

using namespace std;

Graph *createGraph(int numberOfObjects)
{
    Graph *newGraph = new Graph {numberOfObjects, nullptr, 0, nullptr};
    newGraph->adjacencyMatrix = new int*[numberOfObjects]{nullptr};
    for (int i = 0; i < numberOfObjects; ++i)
    {
        newGraph->adjacencyMatrix[i] = new int[numberOfObjects]{0};
    }
    return newGraph;
}

void deleteGraph(Graph *graph)
{
    for (int i = 0; i < graph->size; ++i)
    {
        delete graph->adjacencyMatrix[i];
    }
    delete[] graph->adjacencyMatrix;
    for (int i = 0; i < graph->numberOfStates; ++i)
    {
        delete graph->states[i];
    }
    delete[] graph->states;
    delete graph;
}

void statesRead(Graph *graph, ifstream &fin)
{
    graph->states = new int*[graph->numberOfStates]{nullptr};
    for (int i = 0; i < graph->numberOfStates; ++i)
    {
        graph->states[i] = new int[graph->size]{0};
    }
    int stateNumber = 0;
    while (stateNumber < graph->numberOfStates)
    {
        int newState = 0;
        fin >> newState;
        graph->states[stateNumber][0] = newState;
        ++stateNumber;
    }
    
}

Graph *graphRead(ifstream &fin)
{
    int numberOfCities = 0;
    int numberOfRoads = 0;
    fin >> numberOfCities >> numberOfRoads;
    Graph *graph = createGraph(numberOfCities);
    for (int i = 0; i < numberOfRoads; ++i)
    {
        int firstNode = 0;
        int secondNode = 0;
        int length = 0;
        fin >> firstNode >> secondNode >> length;
        graph->adjacencyMatrix[firstNode - 1][secondNode - 1] = length;
        graph->adjacencyMatrix[secondNode - 1][firstNode - 1] = length;
    }
    
    int numberOfStates = 0;
    fin >> numberOfStates;
    graph->numberOfStates = numberOfStates;
    statesRead(graph, fin);
    return graph;
}

void graphPrint(Graph *graph)
{
    for (int i = 0; i < graph->size; ++i)
    {
        for (int j = 0; j < graph->size; ++j)
        {
            cout << graph->adjacencyMatrix[i][j] << " ";
        }
        cout << endl;
    }
}

int numberOfNearestFreeCity(int *roads, int size, bool *used)
{
    int minimumLength = -1;
    int numberOfCity = 0;
    for (int i = 0; i < size; ++i)
    {
        if (roads[i] != 0 && !used[i])
        {
            if (minimumLength == -1)
            {
                minimumLength = roads[i];
                numberOfCity = i + 1;
            }
            else
            {
                if (roads[i] < minimumLength)
                {
                    minimumLength = roads[i];
                    numberOfCity = i + 1;
                }
            }
        }
    }
    return numberOfCity;
}

int lengthToNearestFreeCity(int *roads, int size, bool *used)
{
    int minimumLength = -1;
    for (int i = 0; i < size; ++i)
    {
        if (roads[i] != 0 && !used[i])
        {
            if (minimumLength == -1)
            {
                minimumLength = roads[i];
            }
            else
            {
                minimumLength = min(minimumLength, roads[i]);
            }
        }
    }
    return minimumLength;
}

int numberOfNearestFreeCity(Graph *graph, int current, bool *used)
{
    int minimumLength = -1;
    int numberOfCity = 0;
    for (int i = 0; i < graph->size; ++i)
    {
        if (graph->states[current][i] != 0)
        {
            int numberOfCurrentCity = graph->states[current][i];
            int newCity = numberOfNearestFreeCity(graph->adjacencyMatrix[numberOfCurrentCity - 1], graph->size, used);
            int newLength = lengthToNearestFreeCity(graph->adjacencyMatrix[numberOfCurrentCity - 1], graph->size, used);
            if (newLength != -1)
            {
                if (minimumLength == -1)
                {
                    minimumLength = newLength;
                    numberOfCity = newCity;
                }
                else
                {
                    if (newLength < minimumLength)
                    {
                        minimumLength = newLength;
                        numberOfCity = newCity;
                    }
                }
            }
        }
    }
    return numberOfCity;
}

void addNewCityToState(int *state, int size, int numberOfCity)
{
    for (int i = 0; i < size; ++i)
    {
        if (state[i] == 0)
        {
            state[i] = numberOfCity;
            return;
        }
    }
}

void findNewCities(Graph *graph)
{
    int freeCities = graph->size - graph->numberOfStates;
    int current = 0;
    bool *used = new bool[graph->size]{false};
    for (int i = 0; i < graph->numberOfStates; ++i)
    {
        used[graph->states[i][0] - 1] = true;
    }
    while (freeCities > 0)
    {
        int newCity = numberOfNearestFreeCity(graph, current, used);
        if (newCity != 0)
        {
            addNewCityToState(graph->states[current], graph->size, newCity);
            --freeCities;
            used[newCity - 1] = true;
        }
        ++current;
        if (current == graph->numberOfStates)
        {
            current = 0;
        }
    }
    delete[] used;
}

void printStates(Graph *graph)
{
    for (int i = 0; i < graph->numberOfStates; ++i)
    {
        cout << i+1 << " State: ";
        for (int j = 0; j < graph->size; ++j)
        {
            if (graph->states[i][j] != 0)
            {
                cout << graph->states[i][j] << " ";
            }
        }
        cout << endl;
    }
    cout << endl;
    }
