package week10.trie;

class Trie {
    private TrieNode root;
    private int nodesVisited;

    public Trie() {
        root = new TrieNode();
    }

    public final char TERMINAL_NODE = '\0';

    // Returns true if the text string is in this trie, false otherwise.
    // The nodesVisited integer is assigned with the number of nodes
    // visited (not counting the root) during the search.
    public boolean contains(String textString) {
        return search(textString) != null;
    }

    // If the text string is in this trie, the corresponding terminal node is
    // returned. Otherwise null is returned.
    // The nodesVisited integer is assigned with the number of nodes visited
    // (not counting the root) during the search.
    protected TrieNode search(String textString) {
        // Start the search at the root
        TrieNode node = root;

        nodesVisited = 0;

        // Iterate through each character in textString
        for (int i = 0; i < textString.length(); i++) {
            TrieNode child = node.getChild(textString.charAt(i));
            if (child == null) {
                return null;
            }
            nodesVisited++;
            node = child;
        }

        // Only if the node has the null child is the string present
        nodesVisited++;
        return node.getChild(TERMINAL_NODE);
    }

    // Returns the number of nodes visited after a call to search().
    public int getNodesVisited() {
        return nodesVisited;
    }

    public void insert(String textString) {
        // Start the search at the root
        TrieNode node = root;

        // Iterate through each character in textString
        for (int i = 0; i < textString.length(); i++) {
            char character = textString.charAt(i);
            TrieNode child = node.getChild(character);
            if (child == null) {
                // Add a new child
                child = node.addNewChild(character);
            }
            node = child;
        }

        // Create the terminal node if doesn't exist
        if (node.getChild(TERMINAL_NODE) == null) {
            node.addNewChild(TERMINAL_NODE);
        }
    }

    public boolean remove(String textString) {
        return removeRecursive(root, textString, 0);
    }

    protected boolean removeRecursive(TrieNode node, String textString, int charIndex) {

        // Base case: Remainder of string is empty
        if (charIndex == textString.length()) {
            // If textString exists in this trie then node has a terminal child
            if (node.getChild(TERMINAL_NODE) != null) {
                node.removeChild(TERMINAL_NODE);
                return true;
            }
            return false; // textString not found
        }

        // Get the child for the next character in the string
        char character = textString.charAt(charIndex);
        TrieNode child = node.getChild(character);
        if (child == null) {
            return false;
        }

        // Recursively remove the remainder of the string
        boolean result = removeRecursive(child, textString, charIndex + 1);
        // If the recursive removal left the child with no children then remove
        // the child
        if (child.getChildCount() == 0) {
            node.removeChild(character);
        }

        return result;
    }
}