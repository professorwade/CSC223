package week10.trie;

import java.util.HashMap;

class TrieNode {
    private HashMap<Character, TrieNode> children;

    public TrieNode() {
        children = new HashMap<>();
    }

    // Creates and adds a new child node corresponding to the character.
    public TrieNode addNewChild(char character) {
        TrieNode newNode = new TrieNode();
        children.put(character, newNode);
        return newNode;
    }

    // Deletes all descendant nodes by clearing the children map.
    public void deleteDescendants() {
        children.clear();
    }

    // Returns the child node corresponding to the character. If no
    // such child exists then null is returned.
    TrieNode getChild(char character) {
        return children.get(character);
    }

    // Returns this TrieNode's number of children.
    int getChildCount() {
        return children.size();
    }

    // Removes the child node corresponding to the character. Returns true if
    // the child is found and removed, false otherwise.
    boolean removeChild(char character) {
        return children.remove(character) == null;
    }
}