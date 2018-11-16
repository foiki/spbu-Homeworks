#pragma once

struct Node
{
    int value;
    int height;
    Node *left;
    Node *right;
};

struct BinaryTree
{
    Node *root;
};

BinaryTree *createTree();
void add(BinaryTree *binaryTree, int number);
void add(Node *node, int number);
void remove(BinaryTree *&binaryTree, int number);
void remove(Node *&node, int number);
void remove(Node *&node);
void deleteTree(BinaryTree *binaryTree);
void deleteNode(Node *node);
bool isElementBelongs(BinaryTree *binaryTree, int number);
void printInAscendingOrder(BinaryTree *binaryTree);
void printInAscendingOrder(Node *node);
void printInDescendingOrder(BinaryTree *binaryTree);
void printInDescendingOrder(Node *node);
void printInABCFormat(BinaryTree *binaryTree);
void printInABCFormat(Node *node);
int height(Node *node);
int balanceFactor(Node *node);
void updateHeight(Node *node);
Node *rotateRight(Node* root);
Node *rotateLeft(Node* root);
Node *balance(Node *node);
