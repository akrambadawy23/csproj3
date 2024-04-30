import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            EdgeWeightedDigraph tenThousandGraph = MyFileReader.readGraphFromFile("10000EWD.txt");
            EdgeWeightedDigraph tinyEWDGraph = MyFileReader.readGraphFromFile("tinyEWD.txt");
            EdgeWeightedDigraph romeGraph = MyFileReader.readGraphFromFile("Rome.txt");

            // Now you have the graph populated from the file
            // Do whatever you want with the graph
            //printGraph(tinyEWDGraph);
            //printGraph(romeGraph);
            //printGraph(tenThousandGraph);

            long startTime, endTime, duration;

            startTime = System.nanoTime();
            DijkstraSP tinyEWDGraphDJK = new DijkstraSP(tinyEWDGraph, 1);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("Single source shortest path from vertex 1 for tinyEWD:");
           // printFromOnePathFromVertice(tinyEWDGraph, tinyEWDGraphDJK);
            System.out.printf("Running Time for tinyEWD: %.3f ms%n", duration / 1000000.0);

            startTime = System.nanoTime();
            DijkstraSP tenThousandGraphDJK = new DijkstraSP(tenThousandGraph, 1);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("Single source shortest path from vertex 1 for tenThousandGraph:");
           // printFromOnePathFromVertice(tenThousandGraph, tenThousandGraphDJK);
            System.out.printf("Running Time for tenThousandGraph: %.3f ms%n", duration / 1000000.0);

            startTime = System.nanoTime();
            DijkstraSP romeGraphDJK = new DijkstraSP(romeGraph, 1);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("Single source shortest path from vertex 1 for romeGraph:");
           // printFromOnePathFromVertice(romeGraph, romeGraphDJK);
            System.out.printf("Running Time for romeGraph: %.3f ms%n", duration / 1000000.0);

            System.out.println("\n\n\n\n\n\nBELLMAN FORD:\n\n\n\n\n");

            startTime = System.nanoTime();
            BellmanFordSP tinyEWDGraphF = new BellmanFordSP(tinyEWDGraph, 1);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("Single source shortest path from vertex 1 for tinyEWD:");
           // printFromOnePathFromVertex2(tinyEWDGraph, tinyEWDGraphF);
            System.out.printf("Running Time for tinyEWD: %.3f ms%n", duration / 1000000.0);




            startTime = System.nanoTime();
            BellmanFordSP tenThousandGraphF = new BellmanFordSP(tenThousandGraph, 1);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("Single source shortest path from vertex 1 for tenThousandGraph:");
          //  printFromOnePathFromVertex2(tenThousandGraph, tenThousandGraphF);
            System.out.printf("Running Time for tenThousandGraph: %.3f ms%n", duration / 1000000.0);

            startTime = System.nanoTime();
            BellmanFordSP romeGraphF = new BellmanFordSP(romeGraph, 1);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("Single source shortest path from vertex 1 for romeGraph:");
           // printFromOnePathFromVertex2(romeGraph, romeGraphF);
            System.out.printf("Running Time for romeGraph: %.3f ms%n", duration / 1000000.0);

            System.out.println("\n\n\n\n\n\nA*:\n\n\n\n\n");

            startTime = System.nanoTime();
            AStar tinyEWDGraphA = new AStar(tinyEWDGraph, 1, v -> getMinWeight(tinyEWDGraph));
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("Single source shortest path from vertex 1 for tinyEWD:");
          //  printFromOnePathFromVertex3(tinyEWDGraph, tinyEWDGraphA);
            System.out.printf("Running Time for tinyEWD: %.3f ms%n", duration / 1000000.0);


            startTime = System.nanoTime();
            AStar tenThousandGraphA = new AStar(tenThousandGraph, 1, v -> 0.0125);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("Single source shortest path from vertex 1 for tenThousandGraph:");
          //  printFromOnePathFromVertex3(tenThousandGraph, tenThousandGraphA);
            System.out.printf("Running Time for tenThousandGraph: %.3f ms%n", duration / 1000000.0);

            startTime = System.nanoTime();
            AStar romeGraphA = new AStar(romeGraph, 1, v -> 2500.00);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("Single source shortest path from vertex 1 for romeGraph:");
           //printFromOnePathFromVertex3(romeGraph, romeGraphA);
            System.out.printf("Running Time for romeGraph: %.3f ms%n", duration / 1000000.0);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void printGraph(EdgeWeightedDigraph graph) {
        for (int v = 0; v < graph.V(); v++) {
            System.out.print(v + ": ");
            for (DirectedEdge edge : graph.adj(v)) {
                int from = edge.from();
                int to = edge.to();
                double weight = edge.weight();
                System.out.print(from + " -> " + to + " (" + weight + "), ");
            }
            System.out.println();
        }
    }

    public static void printFromOnePathFromVertice(EdgeWeightedDigraph selectedGraph, DijkstraSP algorithmObject) {
        for (int v = 0; v < selectedGraph.V(); v++) { // Iterate through the entire graph
            if (algorithmObject.hasPathTo(v)) {
                System.out.printf("%d to %d (%.2f): ", 1, v, algorithmObject.distTo(v));
                Iterable<DirectedEdge> path = algorithmObject.pathTo(v);
                Stack<DirectedEdge> reversedPath = new Stack<>();
                int edgeCount = 1;
                for (DirectedEdge edge : path) {
                    reversedPath.push(edge);
                    edgeCount++;
                }
                while (!reversedPath.isEmpty()) {
                    System.out.print(reversedPath.pop() + " ");
                }
                System.out.println();
            } else {
                System.out.printf("No path from %d to %d\n", 1, v);
            }
        }
    }
    public static double getMinWeight(EdgeWeightedDigraph digraph) {
        double minWeight = Double.POSITIVE_INFINITY;
        for (DirectedEdge e : digraph.edges()) {
            if (e.weight() < minWeight) {
                minWeight = e.weight();
            }
        }
       return minWeight;

    }

    public static double getAvgWeight(EdgeWeightedDigraph digraph) {
        double maxWeight = 0;
        for (DirectedEdge e : digraph.edges()) {
            if (e.weight() > maxWeight) {
                maxWeight = e.weight();
            }
        }
        return maxWeight;

    }
    public static void printFromOnePathFromVertex2(EdgeWeightedDigraph selectedGraph, BellmanFordSP algorithmObject) {
        for (int v = 0; v < selectedGraph.V(); v++) { // Iterate through the entire graph
            if (algorithmObject.hasPathTo(v)) {
                System.out.printf("%d to %d (%.2f): ", 1, v, algorithmObject.distTo(v));
                Iterable<DirectedEdge> path = algorithmObject.pathTo(v);
                Stack<DirectedEdge> reversedPath = new Stack<>();
                int edgeCount = 1;
                for (DirectedEdge edge : path) {
                    reversedPath.push(edge);
                    edgeCount++;
                }
                while (!reversedPath.isEmpty()) {
                    System.out.print(reversedPath.pop() + " ");
                }
                System.out.println();
            } else {
                System.out.printf("No path from %d to %d\n", 1, v);
            }
        }
    }

    public static void printFromOnePathFromVertex3(EdgeWeightedDigraph selectedGraph, AStar algorithmObject) {
        for (int v = 0; v < selectedGraph.V(); v++) { // Iterate through the entire graph
            if (algorithmObject.hasPathTo(v)) {
                System.out.printf("%d to %d (%.2f): ", 1, v, algorithmObject.distTo(v));
                Iterable<DirectedEdge> path = algorithmObject.pathTo(v);
                Stack<DirectedEdge> reversedPath = new Stack<>();
                int edgeCount = 1;
                for (DirectedEdge edge : path) {
                    reversedPath.push(edge);
                    edgeCount++;
                }
                while (!reversedPath.isEmpty()) {
                    System.out.print(reversedPath.pop() + " ");
                }
                System.out.println();
            } else {
                System.out.printf("No path from %d to %d\n", 1, v);
            }
        }


    }

}
