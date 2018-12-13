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
    char *text = new char[maxSize];
    fin.getline(text, maxSize);
    fin.close();
    int numberOfSymbols = 0;
    textRead(symbols, numberOfEachSymbol, text, numberOfSymbols);
    HuffmanTree *huffmanTree = createTree();
    huffmanAlgorithm(huffmanTree, symbols, numberOfEachSymbol, numberOfSymbols);
    printInABCFormat(huffmanTree);
    String **codes = getCodes(huffmanTree);
    printCode(codes, text);
    deleteCodes(codes);
    deleteTree(huffmanTree);
    delete[] symbols;
    delete[] numberOfEachSymbol;
    delete[] text;
    return 0;
}
