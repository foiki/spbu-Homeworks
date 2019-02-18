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
    int *symbolFrequency = new int[size]{0};
    int numberOfSymbols = 0;
    findSymbolFrequencyInFile(symbols, symbolFrequency, fin, numberOfSymbols);
    HuffmanTree *huffmanTree = createTree();
    buildAHuffmanTree(huffmanTree, symbols, symbolFrequency, numberOfSymbols);
    printInABCFormat(huffmanTree);
    String **codes = getCodes(huffmanTree);
    fin.close();
    fin.clear();
    fin.open("File.txt");
    printCode(codes, fin);
    deleteCodes(codes);
    deleteTree(huffmanTree);
    delete[] symbols;
    delete[] symbolFrequency;
    return 0;
}
