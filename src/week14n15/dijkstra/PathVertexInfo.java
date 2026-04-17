package week14n15.dijkstra;

class PathVertexInfo implements Comparable<PathVertexInfo> {
    public Vertex vertex;
    public double distance;
    public Vertex predecessor;

    public PathVertexInfo(Vertex vertex) {
        this.vertex = vertex;
        distance = Double.POSITIVE_INFINITY;
        predecessor = null;
    }

    public int compareTo(PathVertexInfo other) {
        if (distance > other.distance) {
            return 1;
        }
        else if (distance < other.distance) {
            return -1;
        }
        return 0;
    }
}