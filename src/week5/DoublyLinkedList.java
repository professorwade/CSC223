package week5;

import java.io.PrintStream;

class DoublyLinkedNode {
    public int data;
    public DoublyLinkedNode next;
    public DoublyLinkedNode previous;

    public DoublyLinkedNode(int data) {
        this.data = data;
        next = null;
        previous = null;
    }
}

class DoublyLinkedList implements ListADT {
    private DoublyLinkedNode head;
    private DoublyLinkedNode tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    @Override
    public void append(int item) {
        appendNode(new DoublyLinkedNode(item));
    }

    public void appendNode(DoublyLinkedNode newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
    }

    @Override
    public void prepend(int item) {
        prependNode(new DoublyLinkedNode(item));
    }

    public void prependNode(DoublyLinkedNode newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
    }

    @Override
    public boolean insertAfter(int currentItem, int newItem) {
        DoublyLinkedNode currentNode = search(currentItem);
        if (currentNode != null) {
            DoublyLinkedNode newNode = new DoublyLinkedNode(newItem);
            insertNodeAfter(currentNode, newNode);
            return true;
        }
        return false; // currentItem not found
    }

    public void insertNodeAfter(DoublyLinkedNode currentNode,
                                DoublyLinkedNode newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else if (currentNode == tail) {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        else {
            DoublyLinkedNode successor = currentNode.next;
            newNode.next = successor;
            newNode.previous = currentNode;
            currentNode.next = newNode;
            successor.previous = newNode;
        }
    }

    @Override
    public boolean remove(int itemToRemove) {
        DoublyLinkedNode nodeToRemove = search(itemToRemove);
        if (nodeToRemove != null) {
            removeNode(nodeToRemove);
            return true;
        }

        return false; // not found
    }

    public void removeNode(DoublyLinkedNode currentNode) {
        DoublyLinkedNode successor = currentNode.next;
        DoublyLinkedNode predecessor = currentNode.previous;

        if (successor != null) {
            successor.previous = predecessor;
        }
        if (predecessor != null) {
            predecessor.next = successor;
        }

        if (currentNode == head) {
            head = successor;
        }
        if (currentNode == tail) {
            tail = predecessor;
        }
    }

    public DoublyLinkedNode search(int dataValue) {
        DoublyLinkedNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.data == dataValue) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }

        return null;
    }

    @Override
    public boolean contains(int item) {
        return search(item) != null;
    }

    @Override
    public void print(PrintStream out) {
        DoublyLinkedNode node = head;
        if (node != null) {
            // Head node is not preceded by separator
            out.print(node.data);
            node = node.next;
        }

        while (node != null) {
            out.print(", " + node.data);
            node = node.next;
        }
        out.println();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getLength() {
        int length = 0;
        DoublyLinkedNode node = head;
        while (node != null) {
            // Increment length and advance to next node
            length++;
            node = node.next;
        }
        return length;
    }
}
