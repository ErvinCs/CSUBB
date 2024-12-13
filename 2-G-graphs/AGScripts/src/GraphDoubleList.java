import java.util.*;

public class GraphDoubleList implements IGraph{
    private List<Set<Integer>> outSets;
    private List<Set<Integer>> inSets;
    private Integer noVertices;
    private Integer noEdges;

    // -------------------- Seminar 3 --------------------
    public GraphDoubleList() {
        outSets = new ArrayList<>();
        inSets = new ArrayList<>();
        this.noVertices = 0;
        this.noEdges = 0;
    }

    public Integer addVertex() {
        this.outSets.add(new HashSet<>());
        this.inSets.add(new HashSet<>());
        return outSets.size();
    }

    public void addEdge(Integer from, Integer to) {
        this.outSets.get(from).add(to);
        this.inSets.get(to).add(from);
    }
    // ---------------------------------------------------

    public GraphDoubleList(Integer noVertices, Integer noEdges) {
        outSets = new ArrayList<>();
        inSets = new ArrayList<>();
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
        return inSets.get(v);
    }

    private void generateGraph(Integer noVertices, Integer noEdges) {
        Random rand = new Random();

        for(int i = 0; i < noVertices; i++) {
            this.outSets.add(new HashSet<>());
            this.inSets.add(new HashSet<>());
        }

        int i = 0;
        while (i < noEdges) {
            int v1 = rand.nextInt(noVertices);
            int v2= rand.nextInt(noVertices);

            if (this.existsEdge(v1, v2)) {
                continue;
            }

            this.outSets.get(v1).add(v2);
            this.inSets.get(v2).add(v1);
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
