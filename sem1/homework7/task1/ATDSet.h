#pragma once
#include "binaryTree.h"

struct ATDSet
{
    BinaryTree *tree;
};

ATDSet *createSet();
void deleteSet(ATDSet *set);
void addNewElement(ATDSet *set);
void removeElement(ATDSet *set);
void elementExist(ATDSet *set);
void printInAscendingOrder(ATDSet *set);
void printInDescendingOrder(ATDSet *set);
void printInABCFormat(ATDSet *set);
