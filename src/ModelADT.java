import java.util.*;

public interface ModelADT {
    int getPosition(NodeADT x);
    int getWeight(EdgeADT x);
    HashMap<NodeADT, ArrayList<EdgeADT>> calcRoute(NodeADT x, NodeADT y);
    int getDegree(NodeADT x);
    ArrayList<NodeADT> getAdjacent(NodeADT x);
    boolean isAdjacentTo(NodeADT x, NodeADT y);
}

