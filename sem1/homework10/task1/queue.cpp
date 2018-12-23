#include "queue.hpp"

Queue *createQueue()
{
    return new Queue {nullptr};
}

void deleteQueue(Queue *queue)
{
    QueueElement *current = queue->first;
    while (current)
    {
        QueueElement *nextElement = current->next;
        delete current->node;
        delete current;
        current = nextElement;
    }
    delete queue;
}

void add(Queue *queue, Node *newNode)
{
    queue->first = new QueueElement {newNode, queue->first};
}

void remove(Queue *queue, Node *node)
{
    QueueElement *current = queue->first;
    if (isSamePoint(current->node, node))
    {
        QueueElement *toRemove = current;
        queue->first = current->next;
        delete toRemove;
        return;
    }
    while (current->next && !isSamePoint(current->next->node, node))
    {
        current = current->next;
    }
    if (current->next)
    {
        QueueElement *toRemove = current->next;
        current->next = current->next->next;
        delete toRemove;
    }
}

void printQueue(Queue *queue)
{
    QueueElement *current = queue->first;
    while (current)
    {
        cout << "(" << current->node->x << "," << current->node->y << ")" << endl;
        current = current->next;
    }
}

bool isQueueEmpty(Queue *queue)
{
    return (!queue->first);
}

bool exist(Queue *queue, Node *node)
{
    QueueElement *current = queue->first;
    while (current && !isSamePoint(current->node, node))
    {
        current = current->next;
    }
    if (current)
    {
        return true;
    }
    return false;
}
