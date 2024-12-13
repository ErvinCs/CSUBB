public class Main {
    public static int[][] initMatrix(int size) {
        int[][] paths = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j ++) {
                paths[i][j] = 0;
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        int size = 5;
        int[][] testPaths = initMatrix(size);
        //Cycle
        testPaths[0][1] = 1;
        testPaths[1][2] = 1;
        testPaths[2][3] = 1;
        testPaths[3][4] = 1;
        testPaths[4][0] = 1;
        //Other
        testPaths[1][3] = 1;
        testPaths[1][4] = 1;
        testPaths[2][4] = 1;
        testPaths[0][4] = 1;
        Graph testGraph = new Graph(size, testPaths);

        System.out.println(testGraph.toString() + '\n');

        CycleSearcher searcher = new CycleSearcher(testGraph);

        long startTime, stopTime, elapsedTime;
        startTime = System.currentTimeMillis();

        searcher.run();

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Time: " + elapsedTime + "ms");
    }

}
