#include <iostream>
#include "huffman.hpp"

using namespace std;

int main()
{
    ifstream fin("File.txt");
    if (!fin.is_open())
    {
        cout << "File 'File.txt' not found!" << endl;
        return 0;
    }
    char *symbols = new char[size];
    int *numberOfEachSymbol = new int[size]{0};
    int numberOfSymbols = 0;
    textRead(symbols, numberOfEachSymbol, fin, numberOfSymbols);
    HuffmanTree *huffmanTree = createTree();
    huffmanAlgorithm(huffmanTree, symbols, numberOfEachSymbol, numberOfSymbols);
    printInABCFormat(huffmanTree);
    String **codes = getCodes(huffmanTree);
    for (int i = 0; i < size; ++i)
    {
        if (codes[i])
        {
            cout << (char)i << " ";
            printString(codes[i]);
            cout << endl;
        }
    }
    printCode(codes, fin);
    deleteTree(huffmanTree);
    delete[] symbols;
    delete[] numberOfEachSymbol;
    return 0;
}
