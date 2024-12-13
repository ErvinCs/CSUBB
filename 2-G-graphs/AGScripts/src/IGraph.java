import java.util.Set;

public interface IGraph {
    /**
     * Checks the existence of the edge between 2 given vertices
     * @param v1 - Integer - represents a Vertex
     * @param v2 - Integer - represents a Vertex
     * @return boolean - true if there exists an edge between the 2 vertices; false otherwise
     */
    boolean existsEdge(Integer v1, Integer v2);

    /**
     * @param v - Integer - represents a vertex
     * @return Set<Integer> - the set of outbound vertices from Vertex v
     */
    Set<Integer> parseNOut(Integer v);

    /**
     * @param v - Integer - represents a vertex
     * @return Set<Integer> - the set of inbound vertices to Vertex v
     */
    Set<Integer> parseNIn(Integer v);

    /**
     * @return Integer - the number of vertices of the graph
     */
    Integer getNoVertices();

    /**
     * @return Integer - the number of edges of the graph
     */
    Integer getNoEdges();

    /**
     * Add a new Vertex to the graph represented by an Integer
     * @return Integer - Vertex identifier
     */
    Integer addVertex();

    /**
     * Add a new edge to the graph
     * @param from - Vertex of the edge
     * @param to - Vertex of the edge
     */
    void addEdge(Integer from, Integer to);
}
