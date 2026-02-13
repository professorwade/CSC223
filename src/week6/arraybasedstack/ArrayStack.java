package week6.arraybasedstack;

import java.io.PrintStream;

class ArrayStack implements StackADT {
    private int[] array;
    int length;
    int maxLength;

    // The default constructor initializes an unbounded stack
    public ArrayStack() {
        this(-1); // call the one-parameter constructor
    }

    public ArrayStack(int maximumLength) {
        array = new int[1];
        length = 0;
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
    public boolean push(int item) {
        // If at max length, return false
        if (length == maxLength) {
            return false;
        }

        // Resize if length equals array's length
        if (length == array.length) {
            resize();
        }

        // Push the item and return true
        array[length] = item;
        length++;
        return true;
    }

    @Override
    public int pop() {
        length--;
        return array[length];
    }

    @Override
    public int peek() {
        return array[length - 1];
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void print(PrintStream out) {
        // Print first item with no separator
        if (length >= 1) {
            out.print(array[length - 1]);
        }

        // Print remaining items until reaching the bottom
        for (int i = length - 2; i >= 0; i--) {
            out.print(", " + array[i]);
        }
        out.println();
    }

    private void resize() {
        // Allocate new array and copy existing items
        int[] newArray = new int[array.length * 2];
        for (int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }

        // Assign new array
        array = newArray;
    }
}