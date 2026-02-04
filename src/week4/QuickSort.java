package week4;

public class QuickSort {
    static int partition(int[] numbers, int lowIndex, int highIndex) {
        // Pick middle element as the pivot
        int midpoint = lowIndex + (highIndex - lowIndex) / 2;
        int pivot = numbers[midpoint];

        boolean done = false;
        while (!done) {
            while (numbers[lowIndex] < pivot) {
                lowIndex++;
            }
            while (pivot < numbers[highIndex]) {
                highIndex--;
            }

            // If lowIndex and highIndex have met or crossed each
            // other, then partitioning is done
            if (lowIndex >= highIndex) {
                done = true;
            }
            else {
                // Swap numbers[lowIndex] and numbers[highIndex]
                int temp = numbers[lowIndex];
                numbers[lowIndex] = numbers[highIndex];
                numbers[highIndex] = temp;

                // Update lowIndex and highIndex
                lowIndex++;
                highIndex--;
            }
        }
        return highIndex;
    }

    static void displayArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }


    static void quicksort(int[] numbers, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) {
            return;
        }

        int lowEndIndex = partition(numbers, lowIndex, highIndex);
        quicksort(numbers, lowIndex, lowEndIndex);
        quicksort(numbers, lowEndIndex + 1, highIndex);
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
        quicksort(array1, 0, array1.length - 1);
        displayArray(array1);
        System.out.println();

        System.out.println("Demo 2 - Array sorted in ascending order:");
        displayArray(array2);
        quicksort(array2, 0, array2.length - 1);
        displayArray(array2);
        System.out.println();

        System.out.println("Demo 3 - Array sorted in descending order:");
        displayArray(array3);
        quicksort(array3, 0, array3.length - 1);
        displayArray(array3);
    }
}
