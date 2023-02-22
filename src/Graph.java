import java.util.ArrayList;

public class Graph implements GraphADT {
    private final ArrayList<NodeADT> nodes;
    private final ArrayList<EdgeADT> edges;

    public Graph(ArrayList<NodeADT> nodes, ArrayList<EdgeADT> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public void calcRoute() {}

    public int getDegree() {}

    public ArrayList<NodeADT> getAdjacent() {}

    public boolean isAdjacentTo() {}

    public int getPosition() {}

    public int getWeight() {}
}
