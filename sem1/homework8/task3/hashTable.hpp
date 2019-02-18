#pragma once
#include "string.hpp"

struct HashTableArray
{
    String *word;
    int countOfSameWords;
    int countOfTests;
};

struct HashTable
{
    HashTableArray **bucket;
    int countOfWords = 0;
};

HashTable *createHashTable();
void deleteTable(HashTable *table);
int hashFunction(char *string);
void addNewWord(HashTable *table, String *newWord, int position, int countOfTests);
HashTableArray *quadraticSample(HashTable *table, String *string, int position, int &countOfTests, int &newPosition);
bool exist(HashTable *table, String *string, int position);
void newWordProcessing(HashTable *table, char *newWord);
int maximumTestes(HashTable *table);
void wordsWithMaxTests(HashTable *table, int maxTestes);
double averageValueOfTests(HashTable *table);
void printResults(HashTable *table);
