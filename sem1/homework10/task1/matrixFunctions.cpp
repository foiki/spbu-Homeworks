#include "matrixFunctions.hpp"

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

void deleteIntMatrix(int **matrix, int size)
{
    for (int i = 0; i < size; ++i)
    {
        delete[] matrix[i];
    }
    delete[] matrix;
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
