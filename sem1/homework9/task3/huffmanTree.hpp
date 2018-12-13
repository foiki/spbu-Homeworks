#pragma once
#include "string.hpp"

using namespace std;

struct Node
{
    String *symbols;
    int countOfSame;
    bool isLeaf;
    String *code;
    Node *left;
    Node *right;
};

struct HuffmanTree
{
    Node *root;
};

HuffmanTree *createTree();
void deleteNode(Node *node);
void deleteTree(HuffmanTree *tree);
void printInABCFormat(HuffmanTree *tree);
void printInABCFormat(Node *node);
