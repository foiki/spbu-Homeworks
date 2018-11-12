#pragma once

struct Node
{
    int value;
    Node *left;
    Node *right;
};

struct BinaryTree
{
    Node *root;
};

BinaryTree *createTree();
void add(BinaryTree *binaryTree, int number);
void remove(BinaryTree *binaryTree, int number);
void deleteTree(BinaryTree *binaryTree);
void deleteNode(Node *node);
void deleteNodeWithTwoChildren(Node *current);
void deleteNodeWithOneChild(Node *previous, Node *current);
bool isElementBelongs(BinaryTree *binaryTree, int number);
void printInAscendingOrder(BinaryTree *binaryTree);
void printInAscendingOrder(Node *node);
void printInDescendingOrder(BinaryTree *binaryTree);
void printInDescendingOrder(Node *node);
void printInABCFormat(BinaryTree *binaryTree);
void printInABCFormat(Node *node);
