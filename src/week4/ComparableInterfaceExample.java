package week4;
import java.util.ArrayList;
import java.util.Collections;

public class ComparableInterfaceExample {
    static class InventoryItem implements Comparable<InventoryItem> {
        public String name;
        public double price;
        public int numberInStock;

        public InventoryItem(String itemName, double itemPrice, int numberOfItemsInStock) {
            name = itemName;
            price = itemPrice;
            numberInStock = numberOfItemsInStock;
        }

        @Override
        public int compareTo(InventoryItem other) {
            // Compare names only
            return name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return String.format("%s - $%.2f (%d in stock)", name, price, numberInStock);
        }
    }

    public static void main(String[] args) {
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
        items.add(new InventoryItem("Toothpaste", 5.00, 250));
        items.add(new InventoryItem("Toothbrush", 7.00, 500));
        items.add(new InventoryItem("Gum", 1.50, 100));
        items.add(new InventoryItem("Mints", 2.50, 50));
        items.add(new InventoryItem("Potato chips", 3.00, 40));

        System.out.println("Unsorted list:");
        for (InventoryItem item : items) {
            System.out.println(item.toString());
        }
        System.out.println();
        Collections.sort(items);
        System.out.println("List sorted by item name:");
        for (InventoryItem item : items) {
            System.out.println(item.toString());
        }
    }
}
