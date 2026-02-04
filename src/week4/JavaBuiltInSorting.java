package week4;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class JavaBuiltInSorting {
    public static void main(String[] args) {
        int[] numbers = { 3, 7, 2, 8, 12, 4, 9, 5 };
        System.out.println("Unsorted array: " + Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));

        ArrayList<String> fruitList = new ArrayList<String>();
        Collections.addAll(fruitList, "grape", "banana", "apple", "strawberry", "blueberry");
        System.out.println("Unsorted list:  " + fruitList.toString());
        Collections.sort(fruitList);
        System.out.println("Sorted list:    " + fruitList.toString());
    }
}

