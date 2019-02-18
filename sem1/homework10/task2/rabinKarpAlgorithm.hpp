#pragma once
#include <iostream>
#include "string.h"

const int maxLength = 1000000;
const int intMax = 2147483647;
const int prime = 13;

using namespace std;

void countNewHashOfString(char *line, long begin, long length, long &hash);
int hashFunction(char *line, long begin, long length);
bool isEqual(char *string, char *subString);
bool rabinKarpAlgorithm(char *string, char *subString);
