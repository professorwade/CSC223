package week14n15.floydwarshall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

class ShortestPathMatrix {
    private HashMap<Vertex, HashMap<Vertex, Double>> matrix;
    private ArrayList<Vertex> vertices;

    public ShortestPathMatrix(Collection<Vertex> allVertices) {

        // Create an ArrayList from the collection of vertices
        vertices = new ArrayList(allVertices);

        // Sort vertices by label so that print()'s output is easy to read
        vertices.sort((Vertex v1, Vertex v2) -> v1.label.compareTo(v2.label));

        matrix = new HashMap<>();

        // Initialize matrix entries to infinity
        for (Vertex fromVertex : allVertices) {
            HashMap<Vertex, Double> row = new HashMap<>();
            for (Vertex toVertex : allVertices) {
                row.put(toVertex, Double.POSITIVE_INFINITY);
            }

            matrix.put(fromVertex, row);
        }
    }

    public double get(Vertex fromVertex, Vertex toVertex) {
        return matrix.get(fromVertex).get(toVertex);
    }

    public void set(Vertex fromVertex, Vertex toVertex, double distance) {
        matrix.get(fromVertex).put(toVertex, distance);
    }

    public void print() {
        // This method assumes a simple square matrix, where each entry is either
        // an integer in the range [-99, 99], or infinity. Each vertex's label is
        // also assumed to be a single character.

        // First print column headers
        System.out.printf("   ");
        for (Vertex vertex : vertices) {
            System.out.printf("  %s ", vertex.label);
        }

        System.out.println();

        for (Vertex fromVertex : vertices) {
            System.out.printf("%s [ ", fromVertex.label);
            for (Vertex toVertex : vertices) {
                double entry = get(fromVertex, toVertex);

                // Special case if entry is infinity
                if (Double.isInfinite(entry)) {
                    System.out.print("inf ");
                }
                else {
                    // Space before entry only if non-negative
                    if (entry >= 0) {
                        System.out.print(" ");
                    }

                    // Print the entry itself, followed by a space
                    System.out.printf("%d ", (int)entry);

                    // One more space if the entry is a single digit
                    if (entry > -10 && entry < 10) {
                        System.out.print(" ");
                    }
                }
            }

            System.out.println("]");
        }
    }
}