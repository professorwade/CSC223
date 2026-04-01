package week12;

public class Program {
    public static void main(String[] args) {

        // Declare keys to insert and keys to subsequently remove
        int[] keysToInsert = { 10, 20, 5, 22, 15, 47, 19, 3, 12, 18 };
        int[] keysToRemove = {
                12, // Removing 12 causes a right rotation at node 10
                20,
                30  // 30 is not in the tree, so removeKey() will return false
        };

        boolean showTreeAfterEachInsertion = false;

        // Create an empty RedBlackTree
        RedBlackTree tree = new RedBlackTree();

        // Insert keys
        for (int key : keysToInsert) {
            tree.insertKey(key);

            if (showTreeAfterEachInsertion) {
                System.out.println("Tree after inserting "+ key + ":");
                System.out.println(RBTPrint.treeToString(tree.getRoot()));
                System.out.println();
            }
        }

        // Print the tree after all inserts are complete.
        System.out.println("Tree after initial insertions:");
        System.out.println(RBTPrint.treeToString(tree.getRoot()));
        System.out.println();

        // Remove keys
        for (int key : keysToRemove) {
            if (tree.removeKey(key)) {
                System.out.println("Removed key " + key + ":");
                System.out.println(RBTPrint.treeToString(tree.getRoot()));
                System.out.println();
            }
            else {
                System.out.println("Failed to remove key " + key + " (not found)");
            }
        }
    }
}