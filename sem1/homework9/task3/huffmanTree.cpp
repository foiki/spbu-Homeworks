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
    if (node->isLeaf)
    {
        if (node->symbols)
        {
            cout << '{' << node->symbols->elements[0] << '}';
            return;
        }
    }
    cout << "(";
    if (node->left != nullptr)
    {
        printInABCFormat(node->left);
    }
    if (node->right != nullptr)
    {
        printInABCFormat(node->right);
    }
    cout << ")";
}
