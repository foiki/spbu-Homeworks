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

void remove(BinaryTree *binaryTree, int number)
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
            if (current->left && current->right)
            {
                deleteNodeWithTwoChildren(current);
                current = nullptr;
                return;
            }
            else if (current->left || current->right)
            {
                deleteNodeWithOneChild(previous, current);
                current = nullptr;
                return;
            }
            else
            {
                if (previous->value > number)
                {
                    previous->left = nullptr;
                }
                else
                {
                    previous->right = nullptr;
                }
                delete current;
            }
        }
    }
    cout << "Not Found!" << endl;
}

void deleteTree(BinaryTree *binaryTree)
{
    if (binaryTree->root)
    {
        if (binaryTree->root->left)
        {
            deleteNode(binaryTree->root->left);
        }
        if (binaryTree->root->right)
        {
            deleteNode(binaryTree->root->right);
        }
    }
    delete binaryTree->root;
    delete binaryTree;
}

void deleteNode(Node *node)
{
    if (node->left)
    {
        deleteNode(node->left);
        delete node->left;
    }
    if (node->right)
    {
        deleteNode(node->right);
        delete node->right;
    }
}

void deleteNodeWithTwoChildren(Node *current)
{
    Node *previous = nullptr;
    Node *minimalInRightSubtree = current;
    minimalInRightSubtree = current->right;
    while (minimalInRightSubtree->left)
    {
        previous = minimalInRightSubtree;
        minimalInRightSubtree = minimalInRightSubtree->left;
    }
    current->value = minimalInRightSubtree->value;
    if (minimalInRightSubtree->left)
    {
        deleteNodeWithOneChild(previous, minimalInRightSubtree);
    }
    else
    {
        if (!previous || previous->value > minimalInRightSubtree->value)
        {
            previous->left = nullptr;
        }
        else if (!previous)
        {
            previous->right = nullptr;
        }
        delete minimalInRightSubtree;
        
    }
}

void deleteNodeWithOneChild(Node *previous, Node *current)
{
    Node *child = nullptr;
    if (current->left)
    {
        
        child = current->left;
    }
    else
    {
        child = current->right;
    }
    if (previous->value > current->value)
    {
        previous->right = child;
        delete current;
    }
    else
    {
        previous->left = child;
        delete current;
    }
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
            cout << current->value << " ";
        }
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
            cout << current->value << " ";
        }
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
