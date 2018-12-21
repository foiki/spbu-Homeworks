#pragma once
#include <iostream>
#include "string.h"

const int maxLength = 1000000;
const int intMax = 2147483647;
const int prime = 13;

using namespace std;

void countNewHashOfString(char *line, long length, long &hash);
int hashFunction(string line);
char *subLineCopy(char *to, char *from, long begin, long number);
bool isEqual(char *string, char *subString);
bool rabinKarpAlgorithm(char *string, char *subString);
