#include <iostream>
#include "huffman.hpp"

using namespace std;

void findSymbolFrequencyInFile(char *symbols, int *symbolFrequency, ifstream &fin, int &numberOfSymbols)
{
    char *newSymbols = new char[size];
    int *newSymbolFrequency = new int[size]{0};
    char newSymbol = 'a';
    while (!fin.eof())
    {
        newSymbol = fin.get();
        if (!fin.eof())
        {
            ++newSymbolFrequency[newSymbol];
            newSymbols[newSymbol] = newSymbol;
        }
    }
    for (int i = 0; i < size; ++i)
    {
        if (newSymbolFrequency[i] > 0)
        {
            symbols[numberOfSymbols] = newSymbols[i];
            symbolFrequency[numberOfSymbols] = newSymbolFrequency[i];
            ++numberOfSymbols;
        }
    }
    delete[] newSymbols;
    delete[] newSymbolFrequency;
}

void buildAHuffmanTree(HuffmanTree *tree, char *symbols, int *symbolFrequency, int numberOfSymbols)
{
    List *list = createList();
    for (int i = 0; i < numberOfSymbols; ++i)
    {
        char *newElement = new char[1];
        newElement[0] = symbols[i];
        Node *newNode = new Node {charToString(newElement), symbolFrequency[i], true, nullptr, nullptr};
        delete[] newElement;
        add(list, newNode);
    }
    cout << "The frequency of occurrences of characters:" << endl;
    printList(list);
    while (list->first->next->next)
    {
        Node *firstMinimum = findMinimum(list);
        Node *secondMinimum = findMinimum(list);
        int newCount = firstMinimum->countOfSame + secondMinimum->countOfSame;
        Node *newNode = new Node {nullptr, newCount, false, firstMinimum, secondMinimum};
        add(list, newNode);
    }
    Node *firstMinimum = findMinimum(list);
    Node *secondMinimum = list->first->node;
    int newCount = firstMinimum->countOfSame + secondMinimum->countOfSame;
    Node *newNode = new Node {nullptr, newCount, false, firstMinimum, secondMinimum};
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
        else
        {
            deleteString(currentCode);
        }
    }
    char left[2] = {'0', '\0'};
    char right[2] = {'1', '\0'};
    String *toLeft = charToString(left);
    String *toRight = charToString(right);
    if (node->right)
    {
        getCodes(node->right, codes, concatenation(clone(currentCode), toRight));
        deleteString(toRight);
    }
    if (node->left)
    {
        getCodes(node->left, codes, concatenation(currentCode, toLeft));
        deleteString(toLeft);
    }
}

String **getCodes(HuffmanTree *huffmanTree)
{
    String **codes = new String*[size]{nullptr};
    String *currentCode = nullptr;
    getCodes(huffmanTree->root, codes, currentCode);
    return codes;
}

void printCode(String **codes, ifstream &fin)
{
    char newSymbol = 'a';
    while (!fin.eof())
    {
        newSymbol = fin.get();
        if (!fin.eof())
        {
            printString(codes[newSymbol]);
        }
    }
    cout << endl;
}

void deleteCodes(String **codes)
{
    for (int i = 0; i < size; ++i)
    {
        if (codes[i])
        {
            deleteString(codes[i]);
        }
    }
    delete[] codes;
}
