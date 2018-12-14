#include <iostream>
#include <fstream>

using namespace std;

int findMinimumInARow(int *row, int numberOfColoumns)
{
    int minimum = row[0];
    for (int i = 1; i < numberOfColoumns; ++i)
    {
        if (row[i] < minimum)
        {
            minimum = row[i];
        }
    }
    return minimum;
}

int findMaximumInAColoumn(int **matrix, int numberOfRows, int numberOfColoumn)
{
    int maximum = matrix[0][numberOfColoumn];
    for (int i = 1; i < numberOfRows; ++i)
    {
        if (matrix[i][numberOfColoumn] > maximum)
        {
            maximum = matrix[i][numberOfColoumn];
        }
    }
    return maximum;
}

void findSaddlePoints(int **matrix, int numberOfRows, int numberOfColumns)
{
    for (int i = 0; i < numberOfRows; ++i)
    {
        int minimumInARow = findMinimumInARow(matrix[i], numberOfColumns);
        for (int j = 0; j < numberOfColumns; ++j)
        {
            if (matrix[i][j] == minimumInARow)
            {
                if (matrix[i][j] == findMaximumInAColoumn(matrix, numberOfRows, j))
                {
                    cout << "(" << i + 1 << "," << j + 1 << ")" << endl;
                }
            }
        }
    }
}

void deleteMatrix(int **matrix, int numberOfRows)
{
    for (int i = 0; i < numberOfRows; ++i)
    {
        delete[] matrix[i];
    }
    delete[] matrix;
}

int main()
{
    ifstream fin("File.txt");
    if (!fin.is_open())
    {
        cout << "'File.txt' not found!" << endl;
        return 0;
    }
    int numberOfRows = 0;
    int numberOfColumns = 0;
    fin >> numberOfRows;
    fin >> numberOfColumns;
    int **matrix = new int*[numberOfRows];
    for (int i = 0; i < numberOfRows; ++i)
    {
        matrix[i] = new int[numberOfColumns];
        for (int j = 0; j < numberOfColumns; ++j)
        {
            fin >> matrix[i][j];
        }
    }
    cout << "The coordinates of the saddle points:" << endl;
    findSaddlePoints(matrix, numberOfRows, numberOfColumns);
    deleteMatrix(matrix, numberOfRows);
    return 0;
}
