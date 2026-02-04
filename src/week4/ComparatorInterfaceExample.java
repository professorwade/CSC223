package week4;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class ComparatorInterfaceExample {


    static class InventoryItem {
        public String name;
        public double price;
        public int numberInStock;

        public InventoryItem(String itemName, double itemPrice, int numberOfItemsInStock) {
            name = itemName;
            price = itemPrice;
            numberInStock = numberOfItemsInStock;
        }

        @Override
        public String toString() {
            return String.format("%s - $%.2f (%d in stock)", name, price, numberInStock);
        }
    }

    static class InventoryItemNameComparer implements Comparator<InventoryItem> {
        public int compare(InventoryItem item1, InventoryItem item2) {
            return item1.name.compareTo(item2.name);
        }
    }

    static class InventoryItemPriceComparer implements Comparator<InventoryItem> {
        public int compare(InventoryItem item1, InventoryItem item2) {
            if (item1.price > item2.price) {
                return 1;
            }
            else if (item1.price < item2.price) {
                return -1;
            }
            return 0;
        }
    }

    static class InventoryItemNumberInStockComparer implements Comparator<InventoryItem> {
        public int compare(InventoryItem item1, InventoryItem item2) {
            return item1.numberInStock - item2.numberInStock;
        }
    }

    static public class InventoryItemSorting {
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

            // Sort with each comparator
            ArrayList<Comparator<InventoryItem>> comparators = new ArrayList<Comparator<InventoryItem>>();
            comparators.add(new InventoryItemNameComparer());
            comparators.add(new InventoryItemPriceComparer());
            comparators.add(new InventoryItemNumberInStockComparer());
            String[] sortTypes = { "name", "price", "number in stock" };
            int i = 0;
            for (Comparator<InventoryItem> comparator : comparators) {
                Collections.sort(items, comparator);
                System.out.printf("List sorted by item %s:\n", sortTypes[i++]);
                for (InventoryItem item : items) {
                    System.out.println(item.toString());
                }
                System.out.println();
            }
        }
    }
}
