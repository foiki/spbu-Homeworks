#include "huffman.hpp"

int main()
{
    ifstream fin("File.txt");
    if (!fin.is_open())
    {
        cout << "File 'File.txt' not found!" << endl;
        return 0;
    }
    HuffmanTree *huffmanTree = decodeText(fin);
    printDecode(huffmanTree, fin);
    fin.close();
    deleteTree(huffmanTree);
}
