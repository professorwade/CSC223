package week14n15.bfs;

import java.util.ArrayList;

class ArrayListVertexVisitor implements VertexVisitor {
    public ArrayList<Vertex> visitedVertices;

    public ArrayListVertexVisitor() {
        visitedVertices = new ArrayList<Vertex>();
    }

    public void visit(Vertex vertex) {
        visitedVertices.add(vertex);
    }
}