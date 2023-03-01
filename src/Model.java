import java.util.*;

public class Model implements ModelADT {
    private final ArrayList<NodeADT> nodes;
    private final ArrayList<EdgeADT> edges;

    public Model(ArrayList<NodeADT> nodes, ArrayList<EdgeADT> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public int getPosition(NodeADT x) { return x.getPosition(); }
    public int getWeight(EdgeADT x) { return x.getWeight(); }

    //TODO: Implement
    public HashMap<NodeADT, ArrayList<EdgeADT>> calcRoute(NodeADT x, NodeADT y) { return new HashMap<NodeADT, ArrayList<EdgeADT>>(); }

    //TODO: Implement
    public int getDegree(NodeADT x) { return 0; }

    //TODO: Implement
    public ArrayList<NodeADT> getAdjacent(NodeADT x) { return new ArrayList<NodeADT>(); }

    //TODO: Implement
    public boolean isAdjacentTo(NodeADT x, NodeADT y) { return false; }
}
