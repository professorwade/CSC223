package week14n15.dijkstra;

import java.util.HashMap;

public class Program {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Vertex vertexA = graph.addVertex("A");
        Vertex vertexB = graph.addVertex("B");
        Vertex vertexC = graph.addVertex("C");
        Vertex vertexD = graph.addVertex("D");
        Vertex vertexE = graph.addVertex("E");
        Vertex vertexF = graph.addVertex("F");
        Vertex vertexG = graph.addVertex("G");

        Vertex[] vertices = { vertexA, vertexB, vertexC, vertexD, vertexE, vertexF, vertexG };

        graph.addUndirectedEdge(vertexA, vertexB, 8);
        graph.addUndirectedEdge(vertexA, vertexC, 7);
        graph.addUndirectedEdge(vertexA, vertexD, 3);
        graph.addUndirectedEdge(vertexB, vertexE, 6);
        graph.addUndirectedEdge(vertexC, vertexD, 1);
        graph.addUndirectedEdge(vertexC, vertexE, 2);
        graph.addUndirectedEdge(vertexD, vertexF, 15);
        graph.addUndirectedEdge(vertexD, vertexG, 12);
        graph.addUndirectedEdge(vertexE, vertexF, 4);
        graph.addUndirectedEdge(vertexF, vertexG, 1);

        // Set starting vertex for shortest paths
        Vertex startVertex = vertexA;

        // Run Dijkstra's algorithm
        HashMap<Vertex, PathVertexInfo> infoMap = graph.dijkstraShortestPath(startVertex);

        // Display shortest path for each vertex from vertexA.
        for (Vertex vertex : vertices) {
            PathVertexInfo info = infoMap.get(vertex);
            if (info.predecessor == null && vertex != vertexA) {
                System.out.printf("A to %s: no path exists%n", vertex.label);
            }
            else {
                System.out.printf("A to %s: %s (total weight: %d)%n", vertex.label,
                        graph.getShortestPath(vertexA, vertex, infoMap), (int)info.distance);
            }
        }
    }
}