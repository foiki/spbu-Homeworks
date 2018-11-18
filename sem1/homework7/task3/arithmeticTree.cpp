#include <iostream>
#include <fstream>
#include "arithmeticTree.h"

using namespace std;

ArithmeticTree *createTree()
{
    return new ArithmeticTree {nullptr};
}

void add(ArithmeticTree *tree, ifstream &fin)
{
    tree->root = add(fin);
}

Node *add(ifstream &fin)
{
    Node *newNode = new Node {0, ' ', nullptr, nullptr};
    char newElement = ' ';
    fin >> newElement;
    if (newElement == '(')
    {
        fin >> newElement;
        newNode->operation = newElement;
        newNode->left = add(fin);
        fin.get();
        newNode->right = add(fin);
        fin.get();
    }
    else
    {
        int newNumber = newElement - '0';
        newElement = fin.get();
        while (newElement >= '0' && newElement <= '9')
        {
            newNumber = newNumber * 10 + newElement - '0';
            fin >> newElement;
        }
        fin.unget();
        newNode->value = newNumber;
    }
    return newNode;
}

void deleteTree(ArithmeticTree *tree)
{
    if (tree->root)
    {
        deleteNode(tree->root);
    }
    delete tree;
}

void deleteNode(Node *node)
{
    if (node->left)
    {
        deleteNode(node->left);
    }
    if (node->right)
    {
        deleteNode(node->right);
    }
    delete node;
}

void print(ArithmeticTree *tree)
{
    if (tree->root)
    {
        print(tree->root);
    }
    cout << endl;
}

void print(Node *node)
{
    if (!node->left && !node->right)
    {
        cout << node->value;
    }
    else
    {
        cout << "(";
        print(node->left);
        cout << " " << node->operation << " ";
        print(node->right);
        cout << ")";
    }
}

int calculate(ArithmeticTree *tree)
{
    if (tree->root)
    {
        return calculate(tree->root);
    }
    return 0;
}



int calculate(Node *node)
{
    if (node->left && node->right)
    {
        switch (node->operation)
        {
            case '+':
            {
                return calculate(node->left) + calculate(node->right);
            }
            case '-':
            {
                return calculate(node->left) - calculate(node->right);
            }
            case '*':
            {
                return calculate(node->left) * calculate(node->right);
            }
            case '/':
            {
                return calculate(node->left) / calculate(node->right);
            }
        }
    }
    return node->value;
}
