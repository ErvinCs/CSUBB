/**
 * Represents an Edge in a directed graph.
 * It is an outbound edge with regards to Vertex 1 and inbound with regards to Vertex 2.
 * Holds an associated weight (or cost).
 */
public class Edge implements Comparable {
    private Vertex v1;
    private Vertex v2;
    private int weight;

    /**
     * Creates a new Edge directed from Vertex 1 to Vertex 2
     * @param v1 - Vertex 1
     * @param v2 - Vertex 2
     * @param weight - associated cost/weight of travel
     */
    public Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    /**
     * @return Vertex - for which the Edge is outbound
     */
    public Vertex getV1() {
        return v1;
    }

    /**
     * @return Vertex - for which the Edge is inbound
     */
    public Vertex getV2() {
        return v2;
    }

    /**
     * @return int - associated weight/cost of travel
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Used to update the associated weight/cost of travel
     * @param weight - int
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{Vertex1=" + v1.getvID() + ", Vertex2=" + v2.getvID() + ", Weight=" + weight + "}";
    }

    @Override
    public boolean equals(Object obj) {
        Edge other = (Edge)obj;
        return other.getV1() == this.getV1() && other.getV2() == this.getV2();
    }

    @Override
    public int compareTo(Object obj) {
        Edge other = (Edge)obj;
        if (this.getWeight() < other.getWeight())
            return -1;
        else if (this.getWeight() > other.getWeight())
            return 1;
        else return 0;
    }
}
