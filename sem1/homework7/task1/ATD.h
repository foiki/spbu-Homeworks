#pragma once

struct Node
{
    int value;
    Node *left;
    Node *right;
};

struct Tree
{
    Node *root;
};

Tree *createTree();
void add(Tree *tree, int number);
void remove(Tree *&tree, int number);
void remove(Node *&node, int number);
void remove(Node *&node);
void deleteTree(Tree *tree);
void deleteNode(Node *node);
bool isElementBelongs(Tree *tree, int number);
void printInAscendingOrder(Tree *tree);
void printInAscendingOrder(Node *node);
void printInDescendingOrder(Tree *tree);
void printInDescendingOrder(Node *node);
void printInABCFormat(Tree *tree);
void printInABCFormat(Node *node);

void addNewElement(Tree *tree);
void removeElement(Tree *tree);
void elementExist(Tree *tree);
