package week5.sorting;

import java.io.PrintStream;

class SinglyLinkedNode {
    public int data;
    public SinglyLinkedNode next;

    public SinglyLinkedNode(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList implements ListADT {
    private SinglyLinkedNode head;
    private SinglyLinkedNode tail;

    public SinglyLinkedList() {
        head = null;
        tail = null;
    }

    @Override
    public void append(int item) {
        appendNode(new SinglyLinkedNode(item));
    }

    public void appendNode(SinglyLinkedNode newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    @Override
    public void prepend(int item) {
        prependNode(new SinglyLinkedNode(item));
    }

    public void prependNode(SinglyLinkedNode newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.next = head;
            head = newNode;
        }
    }

    @Override
    public boolean insertAfter(int currentItem, int newItem) {
        SinglyLinkedNode currentNode = search(currentItem);
        if (currentNode != null) {
            SinglyLinkedNode newNode = new SinglyLinkedNode(newItem);
            insertNodeAfter(currentNode, newNode);
            return true;
        }
        return false; // currentItem not found
    }

    public void insertNodeAfter(SinglyLinkedNode currentNode, SinglyLinkedNode newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else if (currentNode == tail) {
            tail.next = newNode;
            tail = newNode;
        }
        else {
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
    }

    @Override
    public boolean remove(int item) {
        // Traverse to the node with data equal to item,
        // keeping track of the previous node in the process
        SinglyLinkedNode previous = null;
        SinglyLinkedNode current = head;
        while (current != null) {
            if (current.data == item) {
                removeNodeAfter(previous);
                return true;
            }

            // Advance to next node
            previous = current;
            current = current.next;
        }

        // Not found
        return false;
    }

    public void removeNodeAfter(SinglyLinkedNode currentNode) {
        if (currentNode == null && head != null) {
            // Special case: remove head
            SinglyLinkedNode succeedingNode = head.next;
            head = succeedingNode;
            if (head == null) {
                // Last item was removed
                tail = null;
            }
        }
        else if (currentNode.next != null) {
            SinglyLinkedNode succeedingNode = currentNode.next.next;
            currentNode.next = succeedingNode;
            if (succeedingNode == null) {
                // Remove tail
                tail = currentNode;
            }
        }
    }

    public SinglyLinkedNode findInsertionPosition(int dataValue) {
        SinglyLinkedNode positionA = null;
        SinglyLinkedNode positionB = head;
        while (positionB != null && dataValue > positionB.data) {
            positionA = positionB;
            positionB = positionB.next;
        }
        return positionA;
    }

    @Override
    public void sort() {
        SinglyLinkedNode previousNode = head;
        SinglyLinkedNode currentNode = head.next;

        while (currentNode != null) {
            SinglyLinkedNode nextNode = currentNode.next;
            SinglyLinkedNode position = findInsertionPosition(currentNode.data);

            if (position == previousNode) {
                previousNode = currentNode;
            }
            else {
                removeNodeAfter(previousNode);
                if (position == null) {
                    prependNode(currentNode);
                }
                else {
                    insertNodeAfter(position, currentNode);
                }
            }

            currentNode = nextNode;
        }
    }

    public SinglyLinkedNode searchRecursive(int key) {
        return searchRecursive(key, head);
    }

    public SinglyLinkedNode searchRecursive(int key, SinglyLinkedNode node) {
        if (node != null) {
            if (node.data == key) {
                return node;
            }
            return searchRecursive(key, node.next);
        }
        return null;
    }


    public SinglyLinkedNode search(int dataValue) {
        SinglyLinkedNode currentNode = head;
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
        SinglyLinkedNode node = head;
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
        SinglyLinkedNode node = head;
        while (node != null) {
            // Increment length and advance to next node
            length++;
            node = node.next;
        }
        return length;
    }
}

