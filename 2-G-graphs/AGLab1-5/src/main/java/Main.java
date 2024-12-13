import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static void printMenu() {
        StringBuilder strBuilder = new StringBuilder("Menu\n");
        strBuilder.append("\t0. Exit\n");
        strBuilder.append("\t1. Number of vertices\n");
        strBuilder.append("\t2. Set of vertices\n");
        strBuilder.append("\t3. Check Edge existence between 2 vertices\n");
        strBuilder.append("\t4. In-Out degree of a vertex\n");
        strBuilder.append("\t5. Set of edges\n");
        strBuilder.append("\t6. Inbound edges of a vertex\n");
        strBuilder.append("\t7. Update edge information\n");
        strBuilder.append("\t8. Add edge\n");
        strBuilder.append("\t9. Add vertex\n");
        strBuilder.append("\t10. Remove edge\n");
        strBuilder.append("\t11. Remove vertex\n");
        strBuilder.append("\t12. Save graph\n");
        strBuilder.append("\t13. Load graph\n");
        strBuilder.append("\t14. Generate graph\n");
        strBuilder.append("\t15. Shortest path\n");
        strBuilder.append("\t16. Lowest cost path\n");
        strBuilder.append("\t17. Highest cost path (DAG)\n");
        strBuilder.append("\t18. Detect a cycle (Undirected Graph)");
        System.out.println(strBuilder);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Graph graph = new Graph("res/ex.txt");
            Controller controller = new Controller(graph);
            boolean isRunning = true;
            int option;

            int v1, v2, answer, weight, noVertices, noEdges;
            Vertex vertex1, vertex2;
            Edge edge;
            String filename;
            while(isRunning) {
                printMenu();
                System.out.println("Enter option: ");
                option = scanner.nextInt();
                try {
                    switch (option) {
                        case 0:
                            //Exit
                            isRunning = false;
                            break;
                        case 1:
                            //Number of vertices
                            System.out.println("Number of Vertices: " + controller.getNoVertices());
                            break;
                        case 2:
                            // Parse set of vertices
                            controller.vertices()
                                    .forEach(v -> System.out.println(v.toString()));
                            break;
                        case 3:
                            // Check edge existence
                            System.out.println("Give vertex #1 id: ");
                            v1 = scanner.nextInt();
                            System.out.println("Give vertex #2 id: ");
                            v2 = scanner.nextInt();
                            edge = controller.existsEdge(v1, v2);
                            System.out.println("Edge found: " + edge.toString());
                            break;
                        case 4:
                            // In-Out degree of a vertex
                            System.out.println("Give vertex id: ");
                            v1 = scanner.nextInt();
                            vertex1 = controller.getVertexById(v1);
                            System.out.println("In-degree=" + vertex1.getInDegree() + " | " + "Out-degree=" + vertex1.getOutDegree());
                            break;
                        case 5:
                            // Parse set of edges
                            controller.getEdges()
                                    .forEach(e -> System.out.println(e.toString()));
                            break;
                        case 6:
                            // Inbound edges of a vertez
                            System.out.println("Give vertex id: ");
                            v1 = scanner.nextInt();
                            controller.getVertexById(v1)
                                    .inEdges()
                                    .forEach(e -> System.out.println(e.toString()));
                            break;
                        case 7:
                            // Update edge information
                            answer = -1;
                            System.out.println("Give edge vertex1: ");
                            v1 = scanner.nextInt();
                            System.out.println("Give edge vertex2: ");
                            v2 = scanner.nextInt();
                            edge = controller.getEdge(v1, v2);
                            System.out.println(edge.toString());
                            while (answer != 0 || answer != 1) {
                                System.out.println("Update(1/0): ");
                                answer = scanner.nextInt();
                                if (answer == 1) {
                                    System.out.println("Give new weight: ");
                                    weight = scanner.nextInt();
                                    edge.setWeight(weight);
                                    // Leave this break here
                                    break;
                                } else {
                                    System.out.println("Invalid input!");
                                }
                            }
                            break;
                        case 8:
                            // Add edge
                            System.out.println("Give vertex1 id: ");
                            v1 = scanner.nextInt();
                            vertex1 = controller.getVertexById(v1);
                            System.out.println("Give vertex2 id: ");
                            v2 = scanner.nextInt();
                            vertex2 = controller.getVertexById(v2);
                            System.out.println("Give weight");
                            weight = scanner.nextInt();
                            edge = new Edge(vertex1, vertex2, weight);
                            controller.addEdge(edge);
                            break;
                        case 9:
                            // Add vertex
                            System.out.println("Give vertex id: ");
                            v1 = scanner.nextInt();
                            vertex1 = new Vertex(v1);
                            controller.addVertex(vertex1);
                            break;
                        case 10:
                            // Remove edge
                            System.out.println("Give edge vertex1: ");
                            v1 = scanner.nextInt();
                            System.out.println("Give edge vertex2: ");
                            v2 = scanner.nextInt();
                            edge = controller.getEdge(v1, v2);
                            controller.removeEdge(edge);
                            break;
                        case 11:
                            // Remove vertex
                            System.out.println("Give vertex id: ");
                            v1 = scanner.nextInt();
                            vertex1 = controller.getVertexById(v1);
                            controller.removeVertex(vertex1);
                            break;
                        case 12:
                            // Save graph
                            System.out.println("Name file: ");
                            filename = scanner.next();
                            Graph.saveGraph(graph, filename);
                            break;
                        case 13:
                            System.out.println("Enter file name: ");
                            filename = "res/";
                            filename += scanner.next();
                            controller.setGraph(Graph.readGraph(filename));
                            graph = controller.getGraph();
                            break;
                        case 14:
                            // Generate graph
                            System.out.println("Enter number of vertices: ");
                            noVertices = scanner.nextInt();
                            if (noVertices <= 0) {
                                System.out.println("Invalid number of vertices!");
                                break;
                            }
                            System.out.println("Enter number of edges: ");
                            noEdges = scanner.nextInt();
                            if (noEdges < 0) {
                                System.out.println("Invalid number of edges! ");
                            }
                            controller.setGraph(Graph.generateGraph(noVertices, noEdges));
                            break;
                        case 15:
                            // Shortest path
                            System.out.println("Give vertex1 id: ");
                            v1 = scanner.nextInt();
                            System.out.println("Give vertex2 id: ");
                            v2 = scanner.nextInt();
                            Util.shortestPath(controller.getGraph(), v1, v2).forEach(v -> {
                                System.out.println(v.toString());
                            });
                            break;
                        case 16:
                            // Lowest cost path
                            System.out.println("Give vertex1 id: ");
                            v1 = scanner.nextInt();
                            System.out.println("Give vertex2 id: ");
                            v2 = scanner.nextInt();
                            Util.dijkstraLowestCostPath(controller.getGraph(), v1, v2).forEach(v -> {
                                System.out.println(v.toString());
                            });
                            break;
                        case 17:
                            // Highest cost path
                            System.out.println("Give vertex1 id: ");
                            v1 = scanner.nextInt();
                            System.out.println("Give vertex2 id: ");
                            v2 = scanner.nextInt();
                            Graph.HighestCostPath(graph, v1, v2).forEach(v -> {
                                System.out.println(v.toString());
                            });
                            break;
                        case 18:
                            // Cycle detection - Undirected Graph
                            Util.FindHamiltonianCycle(graph).forEach(v -> {
                                System.out.println(v.toString());
                            });
                            break;
                    }
                } catch (IllegalStateException | IOException | InputMismatchException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
