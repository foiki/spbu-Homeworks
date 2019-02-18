#pragma once

struct Node
{
    int x;
    int y;
};

int **createIntMatrix(int size);
void deleteIntMatrix(int **matrix, int size);
bool **createBoolMatrix(int size);
void deleteBoolMatrix(bool **matrix, int size);
Node ***createNodeMatrix(int size);
void deleteNodeMatrix(Node ***matrix, int size);
