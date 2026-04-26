package week16.huffman;

class HuffmanCompressedString {
    public String uncompressed;
    public String compressed;
    public HuffmanTreeNode root;

    public HuffmanCompressedString(String uncompressed, String compressed,
                                   HuffmanTreeNode root) {

        this.uncompressed = uncompressed;
        this.compressed = compressed;
        this.root = root;
    }
}
