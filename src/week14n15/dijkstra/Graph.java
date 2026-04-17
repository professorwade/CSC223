package week14n15.dijkstra;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

class Graph {
    // Maps a vertex to an ArrayList of all edges that start from that vertex
    private HashMap<Vertex, ArrayList<Edge>> fromEdges;

    // Maps a vertex to an ArrayList of all edges that go to that vertex
    private HashMap<Vertex, ArrayList<Edge>> toEdges;

    public Graph() {
        fromEdges = new HashMap<Vertex, ArrayList<Edge>>();
        toEdges = new HashMap<Vertex, ArrayList<Edge>>();
    }

    public Vertex addVertex(String newVertexLabel) {
        // Create the new Vertex object
        Vertex newVertex = new Vertex(newVertexLabel);

        // Every vertex must exist as a key in both maps
        fromEdges.put(newVertex, new ArrayList<Edge>());
        toEdges.put(newVertex, new ArrayList<Edge>());

        return newVertex;
    }

    public Edge addDirectedEdge(Vertex fromVertex, Vertex toVertex) {
        // Use 1.0 as the default edge weight
        return addDirectedEdge(fromVertex, toVertex, 1.0);
    }

    public Edge addDirectedEdge(Vertex fromVertex, Vertex toVertex, double weight) {
        // Don't add the same edge twice
        if (hasEdge(fromVertex, toVertex)) {
            return null;
        }

        // Create the Edge object
        Edge newEdge = new Edge(fromVertex, toVertex, weight);

        // Add the edge to the appropriate list in both maps
        fromEdges.get(fromVertex).add(newEdge);
        toEdges.get(toVertex).add(newEdge);

        return newEdge;
    }

    public Edge[] addUndirectedEdge(Vertex vertexA, Vertex vertexB) {
        // Use 1.0 as the default edge weight
        return addUndirectedEdge(vertexA, vertexB, 1.0);
    }

    public Edge[] addUndirectedEdge(Vertex vertexA, Vertex vertexB, double weight) {
        Edge edge1 = addDirectedEdge(vertexA, vertexB, weight);
        Edge edge2 = addDirectedEdge(vertexB, vertexA, weight);
        Edge[] result = { edge1, edge2 };
        return result;
    }

    // Returns the collection of edges with the specified fromVertex
    public Collection<Edge> getEdgesFrom(Vertex fromVertex) {
        return fromEdges.get(fromVertex);
    }

    // Returns the collection of edges with the specified toVertex
    public Collection<Edge> getEdgesTo(Vertex toVertex) {
        return toEdges.get(toVertex);
    }

    // Returns a vertex with a matching label, or null if no such vertex exists
    public Vertex getVertex(String vertexLabel) {
        // Search the collection of vertices for a vertex with a matching label
        for (Vertex vertex : getVertices()) {
            if (vertex.label.equals(vertexLabel)) {
                return vertex;
            }
        }
        return null;
    }

    // Returns the collection of all of this graph's vertices
    public Collection<Vertex> getVertices() {
        return fromEdges.keySet();
    }

    // Returns true if this graph has an edge from fromVertex to toVertex
    public boolean hasEdge(Vertex fromVertex, Vertex toVertex) {
        if (!fromEdges.containsKey(fromVertex)) {
            // fromVertex is not in this graph
            return false;
        }

        // Search the list of edges for an edge that goes to toVertex
        ArrayList<Edge> edges = fromEdges.get(fromVertex);
        for (Edge edge : edges) {
            if (edge.toVertex == toVertex) {
                return true;
            }
        }

        return false;
    }

    public HashMap<Vertex, PathVertexInfo> dijkstraShortestPath(Vertex startVertex) {
        // Create the HashMap for vertex information
        HashMap<Vertex, PathVertexInfo> info = new HashMap<>();

        // Put all graph vertices in both the info HashMap and the PriorityQueue
        // of unvisited vertices
        PriorityQueue<PathVertexInfo> unvisited = new PriorityQueue<>();
        for (Vertex vertex : getVertices()) {
            PathVertexInfo vertexInfo = new PathVertexInfo(vertex);
            unvisited.add(vertexInfo);
            info.put(vertex, vertexInfo);
        }

        // startVertex has a distance of 0 from itself
        info.get(startVertex).distance = 0.0;

        // Iterate through all vertices in the priority queue
        while (unvisited.size() > 0) {
            // Get info about the vertex with the shortest distance from startVertex
            PathVertexInfo currentInfo = unvisited.peek();
            unvisited.remove();

            // Check potential path lengths from the current vertex to all neighbors
            for (Edge edge : getEdgesFrom(currentInfo.vertex)) {
                Vertex adjacentVertex = edge.toVertex;
                double alternativePathDistance = currentInfo.distance + edge.weight;

                // If a shorter path from startVertex to adjacentVertex is found,
                // update adjacentVertex's distance and predecessor
                PathVertexInfo adjacentInfo = info.get(adjacentVertex);
                if (alternativePathDistance < adjacentInfo.distance) {
                    unvisited.remove(adjacentInfo);
                    adjacentInfo.distance = alternativePathDistance;
                    adjacentInfo.predecessor = currentInfo.vertex;
                    unvisited.add(adjacentInfo);
                }
            }
        }

        return info;
    }

    public static String getShortestPath(Vertex startVertex, Vertex endVertex,
                                         HashMap<Vertex, PathVertexInfo> infoMap) {
        // Start from endVertex and build the path in reverse.
        String path = "";
        Vertex currentVertex = endVertex;
        while (currentVertex != startVertex) {
            path = " -> " + currentVertex.label + path;
            currentVertex = infoMap.get(currentVertex).predecessor;
        }
        path = startVertex.label + path;
        return path;
    }
}