#include "huffman.hpp"

HuffmanTree *decodeText(ifstream &fin)
{
    HuffmanTree *tree = createTree();
    tree->root = decode(fin);
    return tree;
}

Node *decode(ifstream &fin)
{
    char newSymbol = fin.get();
    if (newSymbol == '{')
    {
        newSymbol = fin.get();
        char newString[1] = {newSymbol};
        Node *newNode = new Node {charToString(newString), 0, true};
        return newNode;
    }
    Node *nodeLeft = decode(fin);
    fin.get();
    Node *nodeRight = decode(fin);
    fin.get();
    return new Node {nullptr, 0, false, nullptr, nodeLeft, nodeRight};
}

char printDecode(Node *node, ifstream &fin)
{
    if (node->isLeaf)
    {
        fin.get();
    }
    while (!node->isLeaf && !fin.eof())
    {
        char newSymbol = fin.get();
        if (newSymbol == '0')
        {
            node = node->left;
        }
        else if (newSymbol == '1')
        {
            node = node->right;
        }
    }
    if (node->isLeaf)
    {
        return node->symbols->elements[0];
    }
    return '\n';
}

void printDecode(HuffmanTree *huffmanTree, ifstream &fin)
{
    fin.get(); // read '\n'
    fin.get();
    while (!fin.eof())
    {
        cout << printDecode(huffmanTree->root, fin);
    }
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
