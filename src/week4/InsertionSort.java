package week4;

public class InsertionSort {
    private static void insertionSort(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            int j = i;
            while (j > 0 && numbers[j] < numbers[j - 1]) {
                int temp = numbers[j];
                numbers[j] = numbers[j - 1];
                numbers[j - 1] = temp;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 94, 97, 45, 98, 70, 38, 72, 99};
        System.out.println("Original array: " + java.util.Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("Sorted array:   " + java.util.Arrays.toString(arr));
    }

}
