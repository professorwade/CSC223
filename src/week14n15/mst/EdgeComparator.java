package week14n15.mst;

import java.util.Comparator;

class EdgeComparator implements Comparator<Edge> {
    public int compare(Edge edge1, Edge edge2) {
        if (edge1.weight > edge2.weight) {
            return 1;
        }
        else if (edge1.weight < edge2.weight) {
            return -1;
        }
        return 0;
    }
}