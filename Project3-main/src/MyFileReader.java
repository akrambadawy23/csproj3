import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {
    public static EdgeWeightedDigraph readGraphFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int numVertices = readIntegerLine(reader);
            System.out.println("Number of vertices: " + numVertices);
            int numEdges = readIntegerLine(reader);
            System.out.println("Number of edges: " + numEdges);
            EdgeWeightedDigraph graph = new EdgeWeightedDigraph(numVertices);
            readEdges(reader, graph);
            return graph;
        }
    }

    private static int readIntegerLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            throw new IOException("Unexpected end of file while reading integer.");
        }
        return Integer.parseInt(line.trim());
    }

    private static void readEdges(BufferedReader reader, EdgeWeightedDigraph graph) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim(); // Normalize the line by removing leading and trailing whitespace
            String[] parts = line.split("\\s+");
            if (parts.length != 3) {
                System.err.println("Invalid edge format: " + line);
                continue; // Skip this line and continue to the next one
            }
            try {
                int start = Integer.parseInt(parts[0]);
                int end = Integer.parseInt(parts[1]);
                double weight = Double.parseDouble(parts[2]);
                graph.addEdge(new DirectedEdge(start, end, weight));
            } catch (NumberFormatException e) {
                System.err.println("Invalid edge format: " + line);
            }
        }
    }
}