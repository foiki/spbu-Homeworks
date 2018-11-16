#include <iostream>
#include "binaryTree.h"

using namespace std;

BinaryTree *createTree()
{
    return new BinaryTree {nullptr};
}

void add(BinaryTree *binaryTree, int number)
{
    if (binaryTree->root)
    {
        add(binaryTree->root, number);
        binaryTree->root = balance(binaryTree->root);
    }
    else
    {
        binaryTree->root = new Node {number, 1, nullptr, nullptr};
    }
}

void add(Node *node, int number)
{
    if (node->value == number)
    {
        cout << "Already added!" << endl;
        return;
    }
    else
    {
        if (node->value > number)
        {
            if (node->left)
            {
                add(node->left, number);
                node->left = balance(node->left);
            }
            else
            {
                node->left = new Node {number, 1, nullptr, nullptr};
            }
        }
        else
        {
            if (node->right)
            {
                add(node->right, number);
                node->right = balance(node->right);
            }
            else
            {
                node->right = new Node {number, 1, nullptr, nullptr};
            }
        }
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
        updateHeight(node);
        balance(node);
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

int height(Node *node)
{
    return node ? node->height : 0;
}

int balanceFactor(Node *node)
{
    return height(node->right) - height(node->left);
}

void updateHeight(Node *node)
{
    int heightLeft = height(node->left);
    int heightRight = height(node->right);
    node->height = ((heightLeft > heightRight) ? heightLeft : heightRight) + 1;
}

Node *rotateRight(Node *node)
{
    Node *pivot = node->left;
    node->left = pivot->right;
    pivot->right = node;
    updateHeight(node);
    updateHeight(pivot);
    return pivot;
}

Node *rotateLeft(Node *node)
{
    Node *pivot = node->right;
    node->right = pivot->left;
    pivot->left = node;
    updateHeight(node);
    updateHeight(pivot);
    return pivot;
}

Node *balance(Node *node)
{
    updateHeight(node);
    if (balanceFactor(node) == 2)
    {
        if (balanceFactor(node->right) < 0)
        {
            node->right = rotateRight(node->right);
        }
        return rotateLeft(node);
    }
    if (balanceFactor(node) == -2)
    {
        if (balanceFactor(node->left) > 0)
        {
            node->left = rotateLeft(node->left);
        }
        return rotateRight(node);
    }
    return node;
}




