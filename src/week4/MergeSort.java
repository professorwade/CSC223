package week4;

public class MergeSort {
    static void merge(int[] numbers, int leftFirst, int leftLast,
               int rightLast) {
        int mergedSize = rightLast - leftFirst + 1;
        int[] mergedNumbers = new int[mergedSize];
        int mergePos = 0;
        int leftPos = leftFirst;
        int rightPos = leftLast + 1;

        while (leftPos <= leftLast && rightPos <= rightLast) {
            if (numbers[leftPos] <= numbers[rightPos]) {
                mergedNumbers[mergePos] = numbers[leftPos];
                leftPos++;
            }
            else {
                mergedNumbers[mergePos] = numbers[rightPos];
                rightPos++;
            }
            mergePos++;
        }

        while (leftPos <= leftLast) {
            mergedNumbers[mergePos] = numbers[leftPos];
            leftPos++;
            mergePos++;
        }

        while (rightPos <= rightLast) {
            mergedNumbers[mergePos] = numbers[rightPos];
            rightPos++;
            mergePos++;
        }
        for (mergePos = 0; mergePos < mergedSize; mergePos++) {
            numbers[leftFirst + mergePos] = mergedNumbers[mergePos];
        }
    }

    private static void mergeSort(int[] numbers, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            // Find the midpoint in the partition
            int mid = (startIndex + endIndex) / 2;

            // Recursively sort left and right partitions
            mergeSort(numbers, startIndex, mid);
            mergeSort(numbers, mid + 1, endIndex);

            // Merge left and right partition in sorted order
            merge(numbers, startIndex, mid, endIndex);
        }
    }

    static void displayArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Unsorted
        int[] array1 = { 33, 18, 78, 64, 45, 32, 70, 11, 27 };

        // Sorted in ascending order
        int[] array2 = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };

        // Sorted in descending order
        int[] array3 = { 99, 88, 77, 66, 55, 44, 33, 22, 11 };

        System.out.println("Demo 1 - Unsorted array:");
        displayArray(array1);
        mergeSort(array1, 0, array1.length - 1);
        displayArray(array1);
        System.out.println();

        System.out.println("Demo 2 - Array sorted in ascending order:");
        displayArray(array2);
        mergeSort(array2, 0, array2.length - 1);
        displayArray(array2);
        System.out.println();

        System.out.println("Demo 3 - Array sorted in descending order:");
        displayArray(array3);
        mergeSort(array3, 0, array3.length - 1);
        displayArray(array3);
    }

}
