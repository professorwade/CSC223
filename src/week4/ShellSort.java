package week4;

public class ShellSort {
    static void insertionSortInterleaved(int[] numbers, int startIndex, int gap) {
        for (int i = startIndex + gap; i < numbers.length; i += gap) {
            int j = i;
            while (j - gap >= startIndex && numbers[j] < numbers[j - gap]) {
                int temp = numbers[j];
                numbers[j] = numbers[j - gap];
                numbers[j - gap] = temp;
                j -= gap;
            }
        }
    }

    static void shellSort(int[] numbers, int[] gapValues) {
        for (int g = 0; g < gapValues.length; g++) {
            for (int i = 0; i < gapValues[g]; i++) {
                insertionSortInterleaved(numbers, i, gapValues[g]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 94, 97, 45, 98, 70, 38, 72, 99};
        int[] gaps = {4, 2, 1};
        System.out.println("Original array: " + java.util.Arrays.toString(arr));
                shellSort(arr, gaps);
        System.out.println("Sorted array:   " + java.util.Arrays.toString(arr));
    }
}
