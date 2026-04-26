package week16.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Item {
    public double weight;
    public double value;
    public double fraction;

    Item(double itemWeight, double itemValue) {
        weight = itemWeight;
        value = itemValue;
        fraction = 1.0;
    }
}

class ItemRatioComparator implements Comparator<Item> {
    public int compare(Item item1, Item item2) {
        double item1Ratio = item1.value / item1.weight;
        double item2Ratio = item2.value / item2.weight;
        if (item1Ratio < item2Ratio) {
            return 1;
        }
        else if (item1Ratio > item2Ratio) {
            return -1;
        }
        return 0;
    }
}

class Knapsack {
    public static ArrayList<Item> fractionalKnapsack(Item[] availableItems, double maxWeight) {
        // Sort the items in descending order based on value
        Arrays.sort(availableItems, new ItemRatioComparator());

        // Initialize an ArrayList to hold items
        ArrayList<Item> knapsackItems = new ArrayList<Item>();

        double remaining = maxWeight;
        for (Item item : availableItems) {
            // Check if the full item can fit into the knapsack or only a fraction
            if (item.weight <= remaining) {
                // Add full item
                knapsackItems.add(item);
                remaining -= item.weight;
            }
            else if (remaining > 0) {
                // Add a fractional part of the item
                item.fraction = remaining / item.weight;
                knapsackItems.add(item);
                break;
            }
        }

        return knapsackItems;
    }
}

public class FractionalKnapsackDemo {
    public static void main(String[] args) {
        // The knapsack's max weight
        double maxWeight = 35.0;

        // Create an array of available items
        Item[] availableItems = {
                new Item(6.0, 25.0),
                new Item(8.0, 42.0),
                new Item(12.0, 60.0),
                new Item(18.0, 95.0)
        };

        ArrayList<Item> knapsackItems = Knapsack.fractionalKnapsack(availableItems, maxWeight);

        // Show the knapsack items
        System.out.println("Items in knapsack:");
        int i = 1;
        double sumWeight = 0.0;
        double sumValue = 0.0;
        for (Item item : knapsackItems) {
            sumWeight += item.weight * item.fraction;
            sumValue += item.value * item.fraction;
            System.out.printf("%d: %.2f of weight %.2f, value %.2f%n", i,
                    item.fraction, item.weight, item.value * item.fraction);
            i++;
        }

        System.out.println();
        System.out.printf("Total weight of items in knapsack: %d%n", (int) sumWeight);
        System.out.printf("Total value of items in knapsack: %d%n", (int) sumValue);
    }
}