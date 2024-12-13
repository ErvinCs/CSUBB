import java.util.*;

public class GraphList implements IGraph{
    private List<Set<Integer>> outSets;
    private Integer noVertices;
    private Integer noEdges;

    // -------------------- Empty --------------------
    /**
     * Empty Stub
     */
    public Integer addVertex() {
        return 0;
    }
    /**
     * Empty Stub
     */
    public void addEdge(Integer from, Integer to) {

    }
    // -----------------------------------------------

    public GraphList(Integer noVertices, Integer noEdges) {
        outSets = new ArrayList<>();
        this.noVertices = noVertices;
        this.noEdges = noEdges;
        generateGraph(noVertices, noEdges);
    }

    public Integer getNoEdges() {
        return noEdges;
    }

    public Integer getNoVertices() {
        return noVertices;
    }

    public boolean existsEdge(Integer v1, Integer v2)  {
        Optional<Integer> edge = outSets.get(v1).stream()
                .filter(i -> i == v2)
                .findAny();
        return edge.isPresent();
    }

    public Set<Integer> parseNOut(Integer v) {
        return outSets.get(v);
    }

    public Set<Integer> parseNIn(Integer v) {
        Set<Integer> inbound = new HashSet<>();
        for(int i = 0; i < outSets.size(); i++) {
            Iterator<Integer> iterator = outSets.get(i).iterator();
            while(iterator.hasNext()) {
                Integer j = iterator.next();
                if (v == j)
                    inbound.add(j);
            }
        }
        return inbound;
    }

    private void generateGraph(Integer noVertices, Integer noEdges) {
        Random rand = new Random();

        for(int i = 0; i < noVertices; i++) {
            this.outSets.add(new HashSet<>());
        }

        int i = 0;
        while (i < noEdges) {
            int v1 = rand.nextInt(noVertices);
            int v2= rand.nextInt(noVertices);

            if (this.existsEdge(v1, v2)) {
                continue;
            }

            this.outSets.get(v1).add(v2);
            i++;
        }
    }

    @Override
    public String toString() {
        String str = "Graph{\n";
        int setCount = 0;
        for(Set<Integer> set : outSets) {
            str += "\t" + setCount + ":{ ";
            Iterator<Integer> iterator = set.iterator();
            while (iterator.hasNext()) {
                str += iterator.next() + " ";
            }
            str += "}\n";
            setCount++;
        }
        str += "}";
        return str;
    }
}
