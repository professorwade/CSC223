package week14n15.dfs;

class PrintVertexVisitor implements VertexVisitor {
    public void visit(Vertex vertex) {
        System.out.print(vertex.label + " ");
    }
}