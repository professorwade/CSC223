package week10.bst;

public class Program {
    public static void main(String[] args) {
        // The following values are inserted in order to build the tree
        int[] valuesToInsert = { 3, 10, 7, 2, 8, 4, 9, 5, 1, 6 };

        // Then the following values are removed from the tree
        int[] valuesToRemove = { 5, 3 };

        BinarySearchTree tree = new BinarySearchTree();

        // Insert values
        for (int value : valuesToInsert) {
            tree.insertKey(value);
        }

        // Show the tree
        System.out.println("Initial tree:");
        System.out.println(BSTPrint.treeToString(tree.getRoot()));

        // Remove values
        for (int valueToRemove : valuesToRemove) {
            System.out.println();
            if (tree.remove(valueToRemove)) {
                System.out.println("Tree after removing " + valueToRemove + ":");
                System.out.println(BSTPrint.treeToString(tree.getRoot()));
            }
            else {
                System.out.println("Key " + valueToRemove + " not found");
            }
        }
    }
}