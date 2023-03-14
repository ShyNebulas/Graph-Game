import java.io.File;
import java.util.*;

public class Model implements ModelADT {
    private final File file;
    private final TreeMap<NodeADT, LinkedHashSet<EdgeADT>> graph = new TreeMap<>();

    public Model(File file) { this.file = file; }

    private Optional<NodeADT> findMatchingNode(NodeADT x) {
        return this.graph.keySet().stream().filter(node -> node.equals(x)).findFirst();
    }

    public void createGraph() {
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                // Current line in text file
                final String[] line = scanner.nextLine().replaceAll("\\D+"," ").split(" ");
                for(int i = 0; i < 2; i++) {
                    EdgeADT edge = new Edge(Integer.parseInt(line[2]), new Node(Integer.parseInt(line[1 - i])));
                    // Finds matching object
                    final NodeADT node = new Node(Integer.parseInt(line[i]));
                    final Optional<NodeADT> inGraph = this.findMatchingNode(node);
                    // Adds to existing edges
                    LinkedHashSet<EdgeADT> edges = inGraph.isPresent() ? this.graph.get(inGraph.get()) : new LinkedHashSet<>();
                    edges.add(edge);
                    this.graph.put(inGraph.orElse(node), edges);
                }
            }
            scanner.close();
        } catch (Exception error) { error.printStackTrace(); }
    }

    public void printGraph() {
        for(Map.Entry<NodeADT, LinkedHashSet<EdgeADT>> entry : this.graph.entrySet()) {
            System.out.printf("Key: %s; Values: %s\n", entry.getKey(), entry.getValue());
        }
    }
    public int getNodeCount() {
        return this.graph.keySet().size();
    }
}
