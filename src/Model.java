import java.io.File;
import java.util.*;

public class Model implements ModelADT {
    private final File file;
    private HashMap<NodeADT, LinkedHashSet<EdgeADT>> graph = new HashMap<>();

    public Model(File file) { this.file = file; }

    private Optional<NodeADT> findMatchingNode(NodeADT x) {
        return this.graph.keySet().stream().filter(node -> node.equals(x)).findFirst();
    }

    public void createGraph() {
        HashMap<NodeADT, LinkedHashSet<EdgeADT>> graph = new HashMap<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                // Current line in text file
                final String[] line = scanner.nextLine().replaceAll("\\D+"," ").split(" ");
                EdgeADT edge = new Edge(Integer.parseInt(line[2]), new Node(Integer.parseInt(line[1])));
                // Finds matching object
                final NodeADT newKey = new Node(Integer.parseInt(line[0]));
                final Optional<NodeADT> matchingNode = this.findMatchingNode(newKey);
                // Adds to existing edges
                LinkedHashSet<EdgeADT> edges = matchingNode.isPresent() ? this.graph.get(matchingNode.get()) : new LinkedHashSet<>();
                edges.add(edge);
                this.graph.put(matchingNode.orElse(newKey), edges);
            }
            scanner.close();
        } catch (Exception error) { error.printStackTrace(); }
    }

    public void printGraph() {
        for(Map.Entry<NodeADT, LinkedHashSet<EdgeADT>> entry : this.graph.entrySet()) {
            System.out.printf("Key: %s; Values: %s\n", entry.getKey(), entry.getValue());
        }
    }

    public int findDegree(NodeADT x) {
        return (this.findMatchingNode(x).isPresent()) ? this.graph.get(this.findMatchingNode(x).get()).size() : -1;
    }







}
