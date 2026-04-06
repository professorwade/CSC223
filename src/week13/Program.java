package week13;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        int[] numbers = { 10, 2, 5, 18, 22 };

        // Add all numbers to the heap
        for (int number : numbers) {
            maxHeap.insert(number);
            System.out.printf("   --> array: %s\n", maxHeap.getHeapArrayString());
        }

        while (maxHeap.getHeapSize() > 0) {
            int removedValue = maxHeap.remove();
            System.out.printf("   --> removed %d, array: %s\n", removedValue,
                    maxHeap.getHeapArrayString());
        }
    }
}
