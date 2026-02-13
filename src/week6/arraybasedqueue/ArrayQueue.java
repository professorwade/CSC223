package week6.arraybasedqueue;

import java.io.PrintStream;

class ArrayQueue implements QueueADT {
    private int[] array;
    private int frontIndex;
    private int length;
    private int maxLength;

    public ArrayQueue() {
        this(-1);
    }

    public ArrayQueue(int maximumLength) {
        int allocationSize = (0 == maximumLength) ? 0 : 1;
        array = new int[allocationSize];
        length = 0;
        frontIndex = 0;
        maxLength = maximumLength;
    }

    @Override
    public int getLength() {
        return length;
    }

    public int getMaxLength() {
        return maxLength;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int peek() {
        return array[frontIndex];
    }

    @Override
    public boolean enqueue(int item) {
        // If at max length, return false
        if (length == maxLength) {
            return false;
        }

        // Resize if length equals allocation size
        if (length == array.length) {
            resize();
        }

        // Enqueue the item and return true
        int itemIndex = (frontIndex + length) % array.length;
        array[itemIndex] = item;
        length++;
        return true;
    }

    @Override
    public int dequeue() {
        // Get the item at the front of the queue
        int toReturn = array[frontIndex];

        // Decrement length and advance frontIndex
        length--;
        frontIndex = (frontIndex + 1) % array.length;

        // Return the front item
        return toReturn;
    }

    private void resize() {
        // Allocate new array and copy existing items
        int newSize = array.length * 2;
        if (maxLength >= 0 && newSize > maxLength) {
            newSize = maxLength;
        }
        int[] newArray = new int[newSize];
        for (int i = 0; i < length; i++) {
            int itemIndex = (frontIndex + i) % array.length;
            newArray[i] = array[itemIndex];
        }

        // Assign new array and reset frontIndex to 0
        array = newArray;
        frontIndex = 0;
    }

    @Override
    public void print(PrintStream out) {
        if (length > 0) {
            // Print first item with no separator
            out.print(array[frontIndex]);
        }

        // Print remaining items preceded by a comma
        for (int i = 1; i < length; i++) {
            int index = (frontIndex + i) % array.length;
            out.print(", " + array[index]);
        }

        out.println();
    }
}