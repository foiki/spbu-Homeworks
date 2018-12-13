#include <iostream>
#include "huffmanTree.hpp"

using namespace std;

HuffmanTree *createTree()
{
    return new HuffmanTree{nullptr};
}

void deleteNode(Node *node)
{
    if (node)
    {
        deleteNode(node->left);
        deleteNode(node->right);
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
