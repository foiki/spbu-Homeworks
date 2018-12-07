#include <iostream>
#include "huffmanTree.hpp"

using namespace std;

HuffmanTree *createTree()
{
    return new HuffmanTree{nullptr};
}

Node *createNode(String *symbols, int countOfSame, Node *left, Node *right)
{
    return new Node{symbols, countOfSame, false, nullptr, left, right};
}

void deleteNode(Node *node)
{
    if (node)
    {
        deleteNode(node->left);
        deleteNode(node->right);
        if (node->code)
        {
            deleteString(node->code);
        }
        if (node->symbols)
        {
            deleteString(node->symbols);
        }
    }
}

void deleteTree(HuffmanTree *tree)
{
    if (tree->root)
    {
        deleteNode(tree->root);
    }
    delete tree;
}

void printInABCFormat(HuffmanTree *tree)
{
    Node *current = tree->root;
    if (current)
    {
        printInABCFormat(current);
        cout << endl;
    }
    else
    {
        cout << "No elements!" << endl;
    }
}

void printInABCFormat(Node *node)
{
    cout << "('";
    for (int i = 0; i <= node->symbols->length; ++i)
    {
        cout << node->symbols->elements[i];
    }
    cout << "'";
    if (node->left)
    {
        printInABCFormat(node->left);
    }
    else
    {
        cout << "null ";
    }
    if (node->right)
    {
        printInABCFormat(node->right);
    }
    else
    {
        cout << "null";
    }
    cout << ")";
}
