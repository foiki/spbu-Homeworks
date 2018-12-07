#pragma once
#include <fstream>
#include "string.hpp"
#include "list.hpp"

using namespace std;

void fileRead(List *list, ifstream &fin);
void killMostDangerous(List *list, int toKill);
void exilePeople(List *list, int toExile);
