#include <iostream>
#include <fstream>
#include "arithmeticTree.h"

using namespace std;

int main()
{
    ifstream fin("File.txt");
    ArithmeticTree *tree = createTree();
    add(tree, fin);
    cout << "Expression: ";
    print(tree);
    cout << "Result: " << calculate(tree) << endl;
    deleteTree(tree);
    return 0;
}
