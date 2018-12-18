#pragma once
#include <iostream>

using namespace std;

struct Node
{
    int x;
    int y;
};

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
bool isSamePoint(Node *first, Node *second);
void remove(Queue *queue, Node *node);
void printQueue(Queue *queue);
bool isQueueEmpty(Queue *queue);
bool exist(Queue *queue, Node *node);
