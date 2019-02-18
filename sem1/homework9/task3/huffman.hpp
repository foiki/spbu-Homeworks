#pragma once
#include <fstream>
#include "list.hpp"

using namespace std;

const int size = 256;

HuffmanTree *decodeText(ifstream &fin);
Node *decode(ifstream &fin);
char printDecode(Node *node, ifstream &fin);
void printDecode(HuffmanTree *huffmanTree, ifstream &fin);
void deleteCodes(String **codes);
