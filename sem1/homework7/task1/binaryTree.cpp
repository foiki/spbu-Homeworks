#include <iostream>
#include "binaryTree.h"

using namespace std;

BinaryTree *createTree()
{
    return new BinaryTree {nullptr};
}

void add(BinaryTree *binaryTree, int number)
{
    Node *previous = nullptr;
    Node *current = binaryTree->root;
    while (current)
    {
        if (current->value > number)
        {
            previous = current;
            current = current->left;
        }
        else if (current->value < number)
        {
            previous = current;
            current = current->right;
        }
        else
        {
            cout << "Already added!" << endl;
            return;
        }
    }
    Node *newElement = new Node {number, nullptr, nullptr};
    if (previous && previous->value > number)
    {
        previous->left = newElement;
    }
    else if (previous && previous->value < number)
    {
        previous->right = newElement;
    }
    else
    {
        binaryTree->root = newElement;
    }
}

void remove(BinaryTree *&binaryTree, int number)
{
    if (binaryTree->root)
    {
        remove(binaryTree->root, number);
    }
    else
    {
        cout << "Nothing to remove!" << endl;
    }
}

void remove(Node *&node, int number)
{
    if (!node)
    {
        cout << "Element not found!" << endl;
        return;
    }
    if (node->value > number)
    {
        remove(node->left, number);
    }
    else if (node->value < number)
    {
        remove(node->right, number);
    }
    else
    {
        remove(node);
    }
}

void remove(Node *&node)
{
    if (!node->left && !node->right)
    {
        Node *removing = node;
        delete removing;
        node = nullptr;
    }
    else if (!node->left && node->right)
    {
        Node *removing = node;
        node = node->right;
        delete removing;
    }
    else if(node->left && !node->right)
    {
        Node *removing = node;
        node = node->left;
        delete removing;
    }
    else
    {
        Node **minimalInRightSubtree = &node->right;
        while ((*minimalInRightSubtree)->left)
        {
            *minimalInRightSubtree = (*minimalInRightSubtree)->left;
        }
        node->value = (*minimalInRightSubtree)->value;
        remove(*minimalInRightSubtree);
    }
}

void deleteTree(BinaryTree *binaryTree)
{
    if (binaryTree->root)
    {
        deleteNode(binaryTree->root);
    }
    delete binaryTree;
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

bool isElementBelongs(BinaryTree *binaryTree, int number)
{
    Node *current = binaryTree->root;
    while (current)
    {
        if (current->value > number)
        {
            current = current->left;
        }
        else if (current->value < number)
        {
            current = current->right;
        }
        else
        {
            return true;
        }
    }
    return false;
}

void printInAscendingOrder(BinaryTree *binaryTree)
{
    Node *current = binaryTree->root;
    if (current)
    {
        if (current->left)
        {
            printInAscendingOrder(current->left);
        }
        cout << current->value << " ";
        if (current->right)
        {
            printInAscendingOrder(current->right);
        }
        cout << endl;
    }
    else
    {
        cout << "No elements!" << endl;
    }
}

void printInAscendingOrder(Node *node)
{
    if (node->left && node->right)
    {
        printInAscendingOrder(node->left);
        cout << node->value << " ";
        printInAscendingOrder(node->right);
    }
    else if (node->left && !node->right)
    {
        printInAscendingOrder(node->left);
        cout << node->value << " ";
    }
    else if (node->right)
    {
        cout << node->value << " ";
        printInAscendingOrder(node->right);
    }
    else
    {
        cout << node->value << " ";
    }
}

void printInDescendingOrder(BinaryTree *binaryTree)
{
    Node *current = binaryTree->root;
    if (current)
    {
        if (current->right)
        {
            printInDescendingOrder(current->right);
        }
        cout << current->value << " ";
        if (current->left)
        {
            printInDescendingOrder(current->left);
        }
        cout << endl;
    }
    else
    {
        cout << "No elements!" << endl;
    }
}

void printInDescendingOrder(Node *node)
{
    if (node->left && node->right)
    {
        printInAscendingOrder(node->right);
        cout << node->value << " ";
        printInAscendingOrder(node->left);
    }
    else if (!node->left && node->right)
    {
        printInAscendingOrder(node->right);
        cout << node->value << " ";
    }
    else if (node->left)
    {
        cout << node->value << " ";
        printInAscendingOrder(node->left);
    }
    else
    {
        cout << node->value << " ";
    }
}

void printInABCFormat(BinaryTree *binaryTree)
{
    Node *current = binaryTree->root;
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
    cout << "(" << node->value << " ";
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
