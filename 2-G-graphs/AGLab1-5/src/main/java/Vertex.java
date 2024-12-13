import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a vertex identified by an ID in a directed graph.
 * Holds references to all its inbound & outbound edges.
 */
public class Vertex implements Comparable{
    private int vID;
    private Set<Edge> in;
    private Set<Edge> out;
    private int degreeIn;
    private int degreeOut;

    /**
     * Creates a new vertex with the given id and no associated edges
     * @param id - integer
     */
    public Vertex(int id) {
        vID = id;
        in = new LinkedHashSet<>();
        degreeIn = 0;
        out = new LinkedHashSet<>();
        degreeOut = 0;
    }

    /**
     * @return Stream of inbound edges
     */
    public Stream<Edge> inEdges(){
        return in.stream();
    }

    /**
     * @return Stream of outbound edges
     */
    public Stream<Edge> outEdges(){
        return out.stream();
    }

    /**
     * Adds an edge to the set of inbound edges
     * @param edge - inbound Edge
     */
    public void addInEdge(Edge edge) {
        in.add(edge);
        degreeIn += 1;
    }

    /**
     * Adds an edge to the set of outbound edges
     * @param edge - outbound edge
     */
    public void addOutEdge(Edge edge) {
        out.add(edge);
        degreeOut += 1;
    }

    /**
     * Removes an edge from the set of inbound edges
     * @param edge - inbound edge
     */
    public void removeInEdge(Edge edge) {
        in.remove(edge);
        degreeIn -= 1;
    }

    /**
     * Removes an edge from the set of outbound edges
     * @param edge = outbound edge
     */
    public void removeOutEdge(Edge edge) {
        out.remove(edge);
        degreeOut -= 1;
    }

    /**
     * @return int - ID of the vertex
     */
    public int getvID() {
        return this.vID;
    }

    public void setvID(int vid) { this.vID = vid; }

    /**
     * @return int - the inbound degree of the vertex (number of inbound edges)
     */
    public int getInDegree() {
        return degreeIn;
    }

    /**
     * @return int - the outbound degree of the vertex (number of outbound edges)
     */
    public int getOutDegree() {
        return degreeOut;
    }

    /**
     * @return Set - the set of inbound edges
     */
    public Set<Edge> getInEdges() {
        return in;
    }

    /**
     * @return Set - the set of outbound edges
     */
    public Set<Edge> getOutEdges() {
        return out;
    }

    /**
     * @return Set - the set of vertices which form outbound edges with this vertex
     */
    public Set<Vertex> getOutVertices()
    {
        Set<Vertex> outVerts = out.stream()
                .map(e -> e.getV2())
                .collect(Collectors.toSet());
        return outVerts;
    }

    /**
     * @return Set - the set of vertices which form inbound edges to this vertex
     */
    public Set<Vertex> getInVertices()
    {
        Set<Vertex> inVerts = in.stream()
                .map(e -> e.getV1())
                .collect(Collectors.toSet());
        return inVerts;
    }


    @Override
    public String toString() {
        return "Vertex{ID=" + vID + ", " +
                "In-Degree=" + degreeIn + ", " +
                "Out-Degree=" + degreeOut +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        Vertex other = (Vertex)obj;
        return this.getvID() == other.getvID();
    }

    @Override
    public int compareTo(Object obj) {
        Vertex other = (Vertex)obj;
        if (this.getvID() < other.getvID())
            return -1;
        else if (this.getvID() > other.getvID())
            return 1;
        else return 0;
    }
}
