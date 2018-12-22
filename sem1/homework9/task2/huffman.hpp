#pragma once
#include <fstream>
#include "list.hpp"

using namespace std;

const int size = 256;

void findSymbolFrequencyInFile(char *symbols, int *numberOfEachSymbol, ifstream &fin, int &number);
void buildAHuffmanTree(HuffmanTree *tree, char *symbols, int *numberOfEachSymbol, int numberOfSymbols);
void getCodes(Node *node, String **codes, String *currentCode);
String **getCodes(HuffmanTree *huffmanTree);
void printCode(String **codes, ifstream &fin);
void deleteCodes(String **codes);
