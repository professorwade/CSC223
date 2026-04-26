package week16.heuristics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Item {
    public double weight;
    public double value;

    Item(double itemWeight, double itemValue) {
        weight = itemWeight;
        value = itemValue;
    }
}

class ItemValueComparator implements Comparator<Item> {
    public int compare(Item item1, Item item2) {
        if (item1.value < item2.value) {
            return 1;
        }
        else if (item1.value > item2.value) {
            return -1;
        }
        return 0;
    }
}

class Knapsack {
    public static ArrayList<Item> knapsack01(Item[] availableItems, double maxWeight) {
        // Sort the items in descending order based on value
        Arrays.sort(availableItems, new ItemValueComparator());

        // Initialize an ArrayList to hold items
        ArrayList<Item> knapsackItems = new ArrayList<Item>();

        double remaining = maxWeight;
        for (Item item : availableItems) {
            if (item.weight <= remaining) {
                knapsackItems.add(item);
                remaining -= item.weight;
            }
            else {
                break;
            }
        }

        return knapsackItems;
    }
}

public class Knapsack01Demo {
    public static void main(String[] args) {
        // The knapsack's max weight
        double maxWeight = 40.0;

        // Create an array of available items
        Item[] availableItems = {
                new Item(6.0, 25.0),
                new Item(8.0, 42.0),
                new Item(12.0, 60.0),
                new Item(18.0, 95.0)
        };

        ArrayList<Item> knapsackItems = Knapsack.knapsack01(availableItems, maxWeight);

        // Show the knapsack items
        System.out.println("Items in knapsack:");
        int i = 1;
        double sumWeight = 0.0;
        double sumValue = 0.0;
        for (Item item : knapsackItems) {
            sumWeight += item.weight;
            sumValue += item.value;
            System.out.printf("%d: weight %d, value %d%n", i, (int)item.weight,
                    (int)item.value);
            i++;
        }
        System.out.println();

        System.out.printf("Total weight of items in knapsack: %d%n", (int) sumWeight);
        System.out.printf("Total value of items in knapsack: %d%n", (int) sumValue);
    }
}