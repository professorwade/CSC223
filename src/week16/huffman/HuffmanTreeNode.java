package week16.huffman;

class HuffmanTreeNode {
    private HuffmanTreeNode leftChild;
    private HuffmanTreeNode rightChild;
    private char character;
    private int frequency;

    // Constructs an internal node with the specified children. The frequency is
    // assigned with the sum of the child frequencies. The character is assigned
    // with '\0'.
    public HuffmanTreeNode(HuffmanTreeNode leftChildNode,
                           HuffmanTreeNode rightChildNode) {

        leftChild = leftChildNode;
        rightChild = rightChildNode;
        character = '\0';

        // Determine this node's frequency by summing child frequencies
        frequency = leftChild.getFrequency() + rightChild.getFrequency();
    }

    // Constructs a leaf node with the specified character and frequency
    HuffmanTreeNode(char leafCharacter, int leafFrequency) {
        leftChild = null;
        rightChild = null;
        character = leafCharacter;
        frequency = leafFrequency;
    }

    char getCharacter() {
        return character;
    }

    // Returns this node's left child, or null if this node is a leaf.
    HuffmanTreeNode getLeftChild() {
        return leftChild;
    }

    // Returns this node's right child, or null if this node is a leaf.
    HuffmanTreeNode getRightChild() {
        return rightChild;
    }

    // Returns this node's frequency. If this node is a leaf, the frequency is
    // the leaf node's character frequency. If this node is internal, the
    // frequency is the sum of both child frequencies.
    int getFrequency() {
        return frequency;
    }
}
