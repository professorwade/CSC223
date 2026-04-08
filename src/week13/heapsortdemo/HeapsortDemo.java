package week13.heapsortdemo;

import java.util.Arrays;

public class HeapsortDemo {
    // Binary max heap percolate down
    static void maxHeapPercolateDown(int nodeIndex, int[] heapArray, int heapSize) {
        int childIndex = 2 * nodeIndex + 1;
        int value = heapArray[nodeIndex];

        while (childIndex < heapSize) {
            // Find the max among the node and all the node's children
            int maxValue = value;
            int maxIndex = -1;
            for (int i = 0; i < 2 && i + childIndex < heapSize; i++) {
                if (heapArray[i + childIndex] > maxValue) {
                    maxValue = heapArray[i + childIndex];
                    maxIndex = i + childIndex;
                }
            }

            if (maxValue == value) {
                return;
            }

            // Swap heapArray[nodeIndex] and heapArray[maxIndex]
            int temp = heapArray[nodeIndex];
            heapArray[nodeIndex] = heapArray[maxIndex];
            heapArray[maxIndex] = temp;

            nodeIndex = maxIndex;
            childIndex = 2 * nodeIndex + 1;
        }
    }

    // Sorts the array of numbers using the heap sort algorithm
    static void heapsort(int[] numbers) {
        // Heapify numbers array
        for (int i = numbers.length / 2 - 1; i >= 0; i--) {
            maxHeapPercolateDown(i, numbers, numbers.length);
        }

        for (int i = numbers.length - 1; i > 0; i--) {
            // Swap numbers[0] and numbers[i]
            int temp = numbers[0];
            numbers[0] = numbers[i];
            numbers[i] = temp;

            maxHeapPercolateDown(0, numbers, i);
        }
    }

    public static void main(String[] args) {
        int[] numbers = { 82, 36, 49, 82, 34, 75, 18, 9, 23 };
        System.out.println("UNSORTED: " + Arrays.toString(numbers));

        heapsort(numbers);
        System.out.println("SORTED:   " + Arrays.toString(numbers));
    }
}

