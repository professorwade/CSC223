package week14n15.graphclass;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Create a new Graph object
        Graph flightGraph = new Graph();

        // Add vertices and edges representing plane flights
        Vertex vertexA = flightGraph.addVertex("Tokyo");
        Vertex vertexB = flightGraph.addVertex("New York");
        Vertex vertexC = flightGraph.addVertex("London");
        Vertex vertexD = flightGraph.addVertex("Sydney");
        flightGraph.addUndirectedEdge(vertexA, vertexB, 6743);
        flightGraph.addUndirectedEdge(vertexA, vertexC, 5941);
        flightGraph.addUndirectedEdge(vertexA, vertexD, 4863);
        flightGraph.addUndirectedEdge(vertexB, vertexC, 3425);
        flightGraph.addUndirectedEdge(vertexB, vertexD, 9868);
        flightGraph.addUndirectedEdge(vertexC, vertexD, 10562);

        // Show the graph's vertices and edges
        for (Vertex vertex : flightGraph.getVertices()) {
            System.out.println("Location: " + vertex.label);

            // Show outgoing edges (flights from location)
            System.out.printf("  Flights from %s:%n", vertex.label);
            for (Edge outgoingEdge : flightGraph.getEdgesFrom(vertex)) {
                System.out.printf(
                        "   - %s to %s, %d miles%n",
                        vertex.label,
                        outgoingEdge.toVertex.label,
                        (int)outgoingEdge.weight);
            }

            // Show incoming edges (flights to location)
            System.out.printf("  Flights to %s:%n", vertex.label);
            for (Edge incomingEdge : flightGraph.getEdgesTo(vertex)) {
                System.out.printf(
                        "   - %s to %s, %d miles%n",
                        incomingEdge.fromVertex.label,
                        vertex.label,
                        (int)incomingEdge.weight);
            }
        }
    }
}
