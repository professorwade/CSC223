package week4;

public class SelectionSort {
    private static void selectionSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {

            // Find index of smallest remaining element
            int indexSmallest = i;
            for (int j = i + 1; j < numbers.length; j++)
            {
                if (numbers[j] < numbers[indexSmallest])
                {
                    indexSmallest = j;
                }
            }

            // Swap numbers[i] and numbers[indexSmallest]
            int temp = numbers[i];
            numbers[i] = numbers[indexSmallest];
            numbers[indexSmallest] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 94, 97, 45, 98, 70, 38, 72, 99};
        System.out.println("Original array: " + java.util.Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("Sorted array:   " + java.util.Arrays.toString(arr));
    }
}
