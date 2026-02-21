package week7.chaininghashtable;

public class Program {
    public static void main(String[] args) {
        String[] keys = {
                "LAX", "IAH", "IAD", "ORD", "SFO",
                "DAL", "NRT", "JFK", "YVR", "LHR"
        };
        String[] values = {
                "Los Angeles", "Houston", "Washington", "Chicago", "San Francisco",
                "Dallas", "Tokyo", "New York", "Vancouver", "London"
        };

        // Create a ChainingHashTable and add all items
        ChainingHashTable<String, String> table = new ChainingHashTable();
        for (int i = 0; i < keys.length; i++) {
            table.insert(keys[i], values[i]);
        }

        // Print the table's buckets
        System.out.println("Buckets:");
        System.out.println(table);

        // Remove some items
        String[] keysToRemove = { "LAX", "ORD" };
        for (String key : keysToRemove) {
            System.out.println("Removing \"" + key + "\"");
            table.remove(key);
        }
        System.out.println();

        // Print again
        System.out.println("Buckets after removals:");
        System.out.println(table);
    }
}