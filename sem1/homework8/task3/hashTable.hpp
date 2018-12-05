#pragma once
#include "string.hpp"

struct hashTableArray
{
    String *word;
    int countOfSameWords;
    int countOfTests;
};

struct hashTable
{
    hashTableArray **bucket;
    int countOfWords = 0;
};

hashTable *createHashTable();
void deleteTable(hashTable *table);
int hashFunction(char *string);
void addNewWord(hashTable *table, String *newWord, int position, int countOfTests);
hashTableArray *quadraticSample(hashTable *table, String *string, int position);
bool exist(hashTable *table, String *string, int position);
void newWordProcessing(hashTable *table, char *newWord);
int maximumTestes(hashTable *table);
void wordsWithMaxTests(hashTable *table, int maxTestes);
double averageValueOfTests(hashTable *table);
void printResults(hashTable *table);
