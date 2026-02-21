package week7;

import java.util.HashMap;

class Program {
    public static void main(String[] args) {

        // Create the HashMap, add some items, and print the size
        HashMap<String, Double> products = new HashMap();
        products.put("Toothpaste", 4.50);
        products.put("Paper towels", 8.00);
        products.put("Hot sauce", 3.25);
        System.out.println("Map has " + products.size() + " items");

        // Display all items
        System.out.println("All items:");
        products.forEach((item, price) -> System.out.println("  " + item + " -> " + price));

        // Declare an item to search for
        String itemName = "Paper towels";

        // Search for the item using containsKey()
        System.out.print("Map contains \"" + itemName + "\"? ");
        if (products.containsKey(itemName)) {
            System.out.println("Yes");

            // Get the item's price
            Double price = products.get(itemName);

            // Print the price
            System.out.printf("Price of \"" + itemName + "\" is $%.2f\n", price);
        }
        else {
            System.out.println("No");
        }

        // Remove "Paper towels"
        System.out.println("Removing \"" + itemName + "\"");
        products.remove(itemName);

        // Print new size and do a search for the removed item
        System.out.println("Map has " + products.size() + " items");
        System.out.print("Map contains \"" + itemName + "\"? ");
        System.out.println(products.containsKey(itemName) ? "Yes" : "No");

        // Change item to "Toothpaste"
        itemName = "Toothpaste";

        // Show the current price
        System.out.printf("Price of \"" + itemName + "\" (before update): $%.2f\n",
                products.get(itemName));

        // Update the price and show again
        products.put(itemName, 3.50);
        System.out.printf("Price of \"" + itemName + "\" (after update): $%.2f\n",
                products.get(itemName));
    }
}