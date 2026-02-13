package week6.llbasedstack;

import java.io.PrintStream;

// Node to store an item in a linked-list-based stack
class StackNode {
    public int data;
    public StackNode next;

    public StackNode(int dataValue, StackNode nextNode) {
        data = dataValue;
        next = nextNode;
    }
}

// Linked-list-based stack
class Stack implements StackADT {
    private StackNode top;

    Stack() {
        top = null;
    }

    @Override
    public void push(int newData) {
        top = new StackNode(newData, top);
    }

    @Override
    public int pop() {
        // Copy top node's data
        int poppedItem = top.data;

        // Remove top node
        top = top.next;

        // Return the popped item
        return poppedItem;
    }

    @Override
    public int peek() {
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int getLength() {
        int count = 0;

        StackNode node = top;
        while (node != null) {
            count++;
            node = node.next;
        }

        return count;
    }

    @Override
    public void print(PrintStream out) {
        StackNode node = top;
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