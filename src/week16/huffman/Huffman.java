package week16.huffman;

import java.util.HashMap;
import java.util.PriorityQueue;

class Huffman {

    public HashMap<Character,Integer> buildFrequencyTable(
            String inputString) {
        HashMap<Character,Integer> table = new HashMap<Character,Integer>();
        for (char stringChar : inputString.toCharArray()) {
            if (table.containsKey(stringChar)) {
                table.put(stringChar, table.get(stringChar) + 1);
            }
            else {
                table.put(stringChar, 1);
            }
        }
        return table;
    }

    void getCodes(HuffmanTreeNode node, String prefix,
                  HashMap<Character,String> output) {

        if (null == node.getLeftChild()) {
            output.put(node.getCharacter(), prefix);
        }
        else {
            getCodes(node.getLeftChild(), prefix + "0", output);
            getCodes(node.getRightChild(), prefix + "1", output);
        }
    }

    HuffmanTreeNode buildTree(HashMap<Character, Integer> table, String inputString) {
        // Make a priority queue of nodes
        PriorityQueue<HuffmanTreeNode> nodes;
        nodes = new PriorityQueue<HuffmanTreeNode>(
                new HuffmanNodeFrequencyComparator());
        for (var item : table.entrySet()) {
            HuffmanTreeNode newLeaf = new HuffmanTreeNode(
                    item.getKey(), item.getValue());
            nodes.add(newLeaf);
        }
        // Make parent nodes up to the root
        while (nodes.size() > 1) {
            // Dequeue two lowest priority nodes
            HuffmanTreeNode left = nodes.poll();
            HuffmanTreeNode right = nodes.poll();
            // Build and enqueue parent for the two nodes
            nodes.add(new HuffmanTreeNode(left, right));
        }
        return nodes.peek();
    }

    public String compress(HashMap<Character, String> codes, HuffmanTreeNode root, String inputString) {
        // Build the compressed result
        String result = "";
        for (char c : inputString.toCharArray()) {
            result += codes.get(c);
        }

        return result;
    }

    public static String decompress(String compressedString,
                                    HuffmanTreeNode treeRoot) {
        HuffmanTreeNode node = treeRoot;
        String result = "";
        for (char bitChar : compressedString.toCharArray()) {
            // Go left or right based on bitChar value
            if ('0' == bitChar) {
                node = node.getLeftChild();
            }
            else {
                node = node.getRightChild();
            }

            // If the node is a leaf, add the character to the
            // decompressed result and go back to the root node
            if (null == node.getLeftChild()) {
                result += node.getCharacter();
                node = treeRoot;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String msg = "The quick brown fox jumps over the lazy dog.";
        /*String msg = "Four score and seven years ago our fathers brought forth on this continent, a new nation, " +
                "conceived in Liberty, and dedicated to the proposition that all men are created equal. " +
                "Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and " +
                "so dedicated, can long endure. We are met on a great battle-field of that war. We have come to dedicate " +
                "a portion of that field, as a final resting place for those who here gave their lives that that nation" +
                " might live. It is altogether fitting and proper that we should do this. " +
                "But, in a larger sense, we can not dedicate -- we can not consecrate -- we can not hallow -- this ground." +
                " The brave men, living and dead, who struggled here, have consecrated it, far above our poor power to" +
                " add or detract. The world will little note, nor long remember what we say here, but it can never" +
                " forget what they did here. It is for us the living, rather, to be dedicated here to the unfinished" +
                " work which they who fought here have thus far so nobly advanced. It is rather for us to be here" +
                " dedicated to the great task remaining before us -- that from these honored dead we take increased" +
                " devotion to that cause for which they gave the last full measure of devotion -- that we here highly" +
                " resolve that these dead shall not have died in vain -- that this nation, under God, shall have a new" +
                " birth of freedom -- and that government of the people, by the people, for the people, shall not perish" +
                " from the earth.";*/

        Huffman huffman = new Huffman();

        // First build the frequency table
        HashMap<Character,Integer> table = huffman.buildFrequencyTable(msg);

        // Next build the tree
        HuffmanTreeNode tree = huffman.buildTree(table, msg);

        // Next build table of codes
        HashMap<Character, String> codes = new HashMap<Character, String>();
        huffman.getCodes(tree, "", codes);

        // compress the message
        String compressed = huffman.compress(codes, tree, msg);

        System.out.println("Original message: " + msg);

        // Display compressed bit string
        System.out.println("Compressed: " + compressed);

        // Decompress the message using the same tree
        String decompressed = huffman.decompress(compressed, tree);

        System.out.println("Decompressed: " + decompressed);
        System.out.println("Decompressed == original message: " + (decompressed.equals(msg)));

        double originalmsg_len = decompressed.length();
        double compressedmsg_len = compressed.length() / 8; // in bits so divide by 8
        System.out.println("Compression ratio: " + (originalmsg_len/compressedmsg_len));
    }
}