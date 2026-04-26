package week16.huffman;
import java.util.Comparator;

class HuffmanNodeFrequencyComparator implements Comparator<HuffmanTreeNode> {
    @Override
    public int compare(HuffmanTreeNode lhs, HuffmanTreeNode rhs) {
        return lhs.getFrequency() - rhs.getFrequency();
    }
}