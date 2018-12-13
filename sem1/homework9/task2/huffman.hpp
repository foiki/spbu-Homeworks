#pragma once
#include <fstream>
#include "list.hpp"

using namespace std;

const int size = 256;

void textRead(char *symbols, int *numberOfEachSymbol, char *text, int &number);
void huffmanAlgorithm(HuffmanTree *tree, char *symbols, int *numberOfEachSymbol, int numberOfSymbols);
void getCodes(Node *node, String **codes, String *currentCode);
String **getCodes(HuffmanTree *huffmanTree);
void printCode(String **codes, char *text);
void deleteCodes(String **codes);
