#pragma once

using namespace std;

struct Node
{
    int value;
    char operation;
    Node *left;
    Node *right;
};

struct ArithmeticTree
{
    Node *root;
};

ArithmeticTree *createTree();
void add(ArithmeticTree *tree, ifstream &fin);
Node *add(ifstream &fin);
void deleteTree(ArithmeticTree *tree);
void deleteNode(Node *node);
void print(ArithmeticTree *tree);
void print(Node *node);
bool isLeaf(Node *node);
int calculate(ArithmeticTree *tree);
int calculate(Node *node);
