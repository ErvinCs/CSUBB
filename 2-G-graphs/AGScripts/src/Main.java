public class Main {
    public static void RunSeminar3() {
        IGraph graph = new GraphDoubleList();
        for(int i = 0; i < 5; i++) {
            graph.addVertex();
        }
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(1, 1);
        graph.addEdge(1, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 2);

        System.out.println("Start vertex=4");
        Util.PrintTree(graph, 4);

        System.out.println("Start vertex=0");
        Util.PrintTree(graph, 0);

        System.out.println("Start vertex=1");
        Util.PrintTree(graph, 1);

        System.out.println("Start vertex=2");
        Util.PrintTree(graph, 2);

        System.out.println("Start vertex=3");
        Util.PrintTree(graph, 3);
    }

    public static void RunSeminar2() {
        double start, finish, genTime;
        int n = 100000;
        int m = n*10;
        System.out.println("For n=" + n + ", m=" + m);

        start = System.currentTimeMillis();
        IGraph listGraph = new GraphList(n, m);
        finish = System.currentTimeMillis();
        genTime = finish - start;
        System.out.println("List of neighbours for each vertex - Generation Time: " + genTime + "ms");
        //System.out.println("List of neighbours for each vertex: ");
        //System.out.println(listGraph.toString() + '\n');
        Util.EvalGraph(listGraph, "List of neighbours for each vertex parse");

        start = System.currentTimeMillis();
        IGraph doubleListGraph = new GraphDoubleList(n, m);
        finish = System.currentTimeMillis();
        genTime = finish - start;
        System.out.println("Double list of neighbours for each vertex - Generation time: " + genTime + "ms");
        //System.out.println("Double list of neighbours for each vertex: ");
        //System.out.println(doubleListGraph.toString() + '\n');
        Util.EvalGraph(doubleListGraph, "Double list of neighbours for each vertex parse");

        start = System.currentTimeMillis();
        IGraph matrixGraph = new GraphMatrix(n, m);
        finish = System.currentTimeMillis();
        genTime = finish - start;
        System.out.println("Adjacency matrix - Generation time: " + genTime + "ms");
        //System.out.println("Adjacency matrix: ");
        //System.out.println(matrixGraph.toString() + '\n');
        Util.EvalGraph(matrixGraph, "Adjacency matrix parse");
    }

    public static void main(String[] args) {
        //RunSeminar2();
        RunSeminar3();
    }
}
