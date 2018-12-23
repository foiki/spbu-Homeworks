#pragma once
#include <iostream>
#include "graph.hpp"

using namespace std;

struct QueueElement
{
    Node *node;
    QueueElement *next;
};

struct Queue
{
    QueueElement *first;
};

Queue *createQueue();
void deleteQueue(Queue *queue);
void add(Queue *queue, Node *newNode);
void remove(Queue *queue, Node *node);
void printQueue(Queue *queue);
bool isQueueEmpty(Queue *queue);
bool exist(Queue *queue, Node *node);
