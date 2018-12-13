#include <iostream>
#include "huffman.hpp"

using namespace std;

void textRead(char *symbols, int *numberOfEachSymbol, char *text, int &numberOfSymbols)
{
    char *newSymbols = new char[size];
    int *newNumberOfEachSymbol = new int[size]{0};
    long length = strlen(text);
    for (int i = 0; i < length; ++i)
    {
        ++newNumberOfEachSymbol[text[i]];
        newSymbols[text[i]] = text[i];
    }
    for (int i = 0; i < size; ++i)
    {
        if (newNumberOfEachSymbol[i] > 0)
        {
            symbols[numberOfSymbols] = newSymbols[i];
            numberOfEachSymbol[numberOfSymbols] = newNumberOfEachSymbol[i];
            ++numberOfSymbols;
        }
    }
    delete[] newSymbols;
    delete[] newNumberOfEachSymbol;
}

void huffmanAlgorithm(HuffmanTree *tree, char *symbols, int *numberOfEachSymbol, int numberOfSymbols)
{
    List *list = createList();
    for (int i = 0; i < numberOfSymbols; ++i)
    {
        char *newElement = new char[1];
        newElement[0] = symbols[i];
        Node *newNode = new Node{charToString(newElement), numberOfEachSymbol[i], nullptr, nullptr};
        newNode->isLeaf = true;
        add(list, newNode);
    }
    cout << "The frequency of occurrences of characters:" << endl;
    printList(list);
    while (list->first->next->next)
    {
        Node *firstMinimum = findMinimum(list);
        Node *secondMinimum = findMinimum(list);
        String *newSymbols = clone(concatenation(clone(firstMinimum->symbols), secondMinimum->symbols));
        int newCount = firstMinimum->countOfSame + secondMinimum->countOfSame;
        Node *newNode = new Node{newSymbols, newCount, false, firstMinimum, secondMinimum};
        add(list, newNode);
    }
    Node *firstMinimum = findMinimum(list);
    Node *secondMinimum = list->first->node;
    String *newSymbols = clone(concatenation(clone(firstMinimum->symbols), secondMinimum->symbols));
    int newCount = firstMinimum->countOfSame + secondMinimum->countOfSame;
    Node *newNode = new Node{newSymbols, newCount, false, firstMinimum, secondMinimum};
    delete list->first;
    delete list;
    tree->root = newNode;
}

void getCodes(Node *node, String **codes, String *currentCode)
{
    if (node->isLeaf)
    {
        char currentSymbol = node->symbols->elements[0];
        if (!codes[currentSymbol])
        {
            codes[currentSymbol] = currentCode;
        }
    }
    char left[2] = {'0', '\0'};
    char right[2] = {'1', '\0'};
    String *toLeft = charToString(left);
    String *toRight = charToString(right);
    if (node->right)
    {
        getCodes(node->right, codes, concatenation(clone(currentCode), toRight));
    }
    if (node->left)
    {
        getCodes(node->left, codes, concatenation(currentCode, toLeft));
    }
    deleteString(toLeft);
    deleteString(toRight);
}

String **getCodes(HuffmanTree *huffmanTree)
{
    String **codes = new String*[size]{nullptr};
    String *currentCode = nullptr;
    getCodes(huffmanTree->root, codes, currentCode);
    return codes;
}

void printCode(String **codes, char *text)
{
    long length = strlen(text);
    for (int i = 0; i < length; ++i)
    {
        if (text[i] != '\t')
        {
            printString(codes[text[i]]);
        }
    }
    cout << endl;
}

void deleteCodes(String **codes)
{
    for (int i = 0; i < size; ++i)
    {
        if (codes[i] != nullptr)
        {
            deleteString(codes[i]);
        }
    }
    delete[] codes;
}
