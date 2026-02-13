package week5.array_based;

import java.io.PrintStream;

class ArrayList implements ListADT {
    private int length;
    private int[] array;

    public ArrayList() {
        this(1);
    }

    public ArrayList(int initArrayLength) {
        array = new int[initArrayLength];
        length = 0;
    }

    @Override
    public void append(int item) {
        // Resize if the array is full
        if (array.length == length) {
            resize(length * 2);
        }

        // Insert the new item at the end
        array[length] = item;

        // Increment length to reflect the added item
        length++;
    }

    @Override
    public void prepend(int item) {
        // Resize if the array is full
        if (array.length == length) {
            resize(length * 2);
        }

        // Shift all array items to the right, starting from the
        // last index and moving down to the first index
        for (int i = length; i > 0; i--) {
            array[i] = array[i - 1];
        }

        // Insert the new item at index 0
        array[0] = item;

        // Increment length to reflect the added item
        length++;
    }

    @Override
    public boolean insertAfter(int existingItem, int newItem) {
        // Get the existing item's index
        int existingItemIndex = search(existingItem);

        if (existingItemIndex == -1) {
            return false; // not found
        }

        insertAt(existingItemIndex + 1, newItem);
        return true;
    }

    public void insertAt(int index, int newItem) {
        // Resize if the array is full
        if (array.length == length) {
            resize(length * 2);
        }

        // Shift all array items to the right, starting from the
        // last index and moving down to the given index
        for (int i = length; i > index; i--) {
            array[i] = array[i - 1];
        }

        // Insert the new item at the index
        array[index] = newItem;

        // Increment length to reflect the inserted item
        length++;
    }

    @Override
    public boolean remove(int itemToRemove) {
        int itemIndex = search(itemToRemove);
        if (itemIndex >= 0) {
            removeAt(itemIndex);
            return true;
        }

        return false; // not found
    }

    public void removeAt(int index) {
        // Make sure the index is valid for the current array
        if (index >= 0 && index < length) {
            // Shift down all items after the given index
            for (int i = index; i < length - 1; i++) {
                array[i] = array[i + 1];
            }

            // Update length to reflect the removed item
            length--;
        }
    }

    public int search(int item) {
        // Iterate through the entire array
        for (int i = 0; i < length; i++) {
            // Return the current index immediately if item is found
            if (array[i] == item) {
                return i;
            }
        }

        return -1;  // not found
    }

    @Override
    public boolean contains(int item) {
        return search(item) != -1;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void print(PrintStream out) {
        if (length > 0) {
            // Print first item without separator
            out.print(array[0]);
        }

        // Print remaining items, each preceded by the separator
        for (int i = 1; i < length; i++) {
            out.print(", " + array[i]);
        }
        out.println();
    }

    private void resize(int newAllocationSize) {
        int[] newArray = new int[newAllocationSize];
        for (int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }
}