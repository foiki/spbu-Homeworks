#pragma once
#include <fstream>
#include "list.hpp"

using namespace std;

const int size = 256;

int fileEnd();
void textRead(char *symbols, int *numberOfEachSymbol, ifstream &fin, int &number);
void huffmanAlgorithm(HuffmanTree *tree, char *symbols, int *numberOfEachSymbol, int numberOfSymbols);
void getCodes(Node *node, String **codes, String *currentCode);
String **getCodes(HuffmanTree *huffmanTree);
void printCode(String **codes, ifstream &fin);
