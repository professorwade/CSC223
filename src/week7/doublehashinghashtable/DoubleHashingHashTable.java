package week7.doublehashinghashtable;

import java.io.PrintStream;

public class DoubleHashingHashTable<K, V> implements MapADT<K, V> {
    private OpenAddressingBucket<K, V>[] table;

    public DoubleHashingHashTable() {
        // Initialize with an initial capacity of 11
        this(11);
    }

    public DoubleHashingHashTable(int initialCapacity) {
        table = new OpenAddressingBucket[initialCapacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = OpenAddressingBucket.EMPTY_SINCE_START;
        }
    }

    // Returns a non-negative hash code for the specified key.
    private int hash(K key) {
        long keyHash = key.hashCode();

        // Java's hashCode() method may return a negative number
        if (keyHash < 0) {
            keyHash += 2147483648L;
        }

        return (int)keyHash;
    }

    // The secondary hash function. Many different functions can
    // be used here. The function used here is a common one, with
    // different (usually prime number) constants used where the 7 is.
    private int hash2(K key) {
        return 7 - hash(key) % 7;
    }

    // Inserts the specified key/value pair. If the key already exists, the
    // corresponding value is updated. If inserted or updated, true is returned.
    // If not inserted, then false is returned.
    @Override
    public boolean insert(K key, V value) {

        // Get the key's hash code
        int hashCode = hash(key);
        int hashCode2 = hash2(key);

        // First search for the key in the table. If found, update bucket's value.
        for (int i = 0; i < table.length; i++) {
            int bucketIndex = (hashCode + i * hashCode2) % table.length;

            // An empty-since-start bucket implies the key is not in the table
            if (table[bucketIndex].isEmptySinceStart()) {
                break;
            }

            if (!table[bucketIndex].isEmptyAfterRemoval()) {
                // Check if the non-empty bucket has the key
                if (key.equals(table[bucketIndex].key)) {
                    // Update the value
                    table[bucketIndex].value = value;
                    return true;
                }
            }
        }

        // The key is not in the table, so insert into first empty bucket
        for (int i = 0; i < table.length; i++) {
            int bucketIndex = (hashCode + i * hashCode2) % table.length;
            if (table[bucketIndex].isEmpty()) {
                table[bucketIndex] = new OpenAddressingBucket(key, value);
                return true;
            }
        }

        return false; // No empty bucket found
    }

    // Searches for the specified key. If found, the key/value pair is removed
    // from the hash table and true is returned. If not found, false is returned.
    @Override
    public boolean remove(K key) {

        // Get the key's hash code
        int hashCode = hash(key);
        int hashCode2 = hash2(key);

        for (int i = 0; i < table.length; i++) {
            int bucketIndex = (hashCode + i * hashCode2) % table.length;

            // An empty-since-start bucket implies the key is not in the table
            if (table[bucketIndex].isEmptySinceStart()) {
                return false;
            }

            if (!table[bucketIndex].isEmptyAfterRemoval()) {
                // Check if the non-empty bucket has the key
                if (key.equals(table[bucketIndex].key)) {
                    // Remove by setting the bucket to empty-after-removal
                    table[bucketIndex] = OpenAddressingBucket.EMPTY_AFTER_REMOVAL;
                    return true;
                }
            }
        }

        return false; // Key not found
    }

    // Searches for the key, returning the corresponding value if found, null if
    // not found.
    @Override
    public V get(K key) {

        // Get the key's hash code
        int hashCode = hash(key);
        int hashCode2 = hash2(key);

        for (int i = 0; i < table.length; i++) {
            int bucketIndex = (hashCode + i * hashCode2) % table.length;

            // An empty-since-start bucket implies the key is not in the table
            if (table[bucketIndex].isEmptySinceStart()) {
                return null;
            }

            if (!table[bucketIndex].isEmptyAfterRemoval()) {
                // Check if the non-empty bucket has the key
                if (key.equals(table[bucketIndex].key)) {
                    return table[bucketIndex].value;
                }
            }
        }

        return null; // Key not found
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public int getLength() {
        int length = 0;

        // Count how many buckets are not empty
        for (var bucket : table) {
            if (!bucket.isEmpty()) {
                length++;
            }
        }

        return length;
    }

    @Override
    public boolean isEmpty() {
        return getLength() == 0;
    }

    @Override
    public void print(PrintStream out) {
        for (var bucket : table) {
            // Print only non-empty buckets
            if (!bucket.isEmpty()) {
                out.println(bucket.key.toString() + ": " + bucket.value.toString());
            }
        }
    }

    // Returns a string containing contents of each bucket
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < table.length; i++) {
            result.append(i + ": ");

            if (table[i].isEmptySinceStart()) {
                result.append("EMPTY_SINCE_START\n");
            }
            else if (table[i].isEmptyAfterRemoval()) {
                result.append("EMPTY_AFTER_REMOVAL\n");
            }
            else {
                result.append(table[i].key);
                result.append(", ");
                result.append(table[i].value);
                result.append('\n');
            }
        }

        return result.toString();
    }
}