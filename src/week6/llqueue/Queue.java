package week6.llqueue;

import java.io.PrintStream;

// Node to store an item in a linked-list-based queue
class QueueNode {
    public int data;
    public QueueNode next;

    public QueueNode(int dataValue) {
        this(dataValue, null);
    }

    public QueueNode(int dataValue, QueueNode nextNode) {
        data = dataValue;
        next = nextNode;
    }
}

// Linked-list-based queue
class Queue implements QueueADT {
    private QueueNode front;
    private QueueNode end;

    public Queue() {
        front = null;
        end = null;
    }

    @Override
    public void enqueue(int newData) {
        QueueNode newNode = new QueueNode(newData);

        // Append newNode to the end of the linked list
        if (front == null) {
            front = newNode;
        }
        else {
            end.next = newNode;
        }

        end = newNode;
    }

    @Override
    public int dequeue() {
        // Copy front node's data
        int dequeuedItem = front.data;

        // Remove front node
        front = front.next;

        // If empty, assign end with null
        if (front == null) {
            end = null;
        }

        // Return dequeued item
        return dequeuedItem;
    }

    @Override
    public int peek() {
        return front.data;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public int getLength() {
        int length = 0;

        QueueNode node = front;
        while (node != null) {
            length++;
            node = node.next;
        }

        return length;
    }

    @Override
    public void print(PrintStream out) {
        QueueNode node = front;
        if (node != null) {
            // Print first item with no separator
            out.print(node.data);
            node = node.next;
        }

        while (node != null) {
            out.print(", " + node.data);
            node = node.next;
        }
        out.println();
    }
}