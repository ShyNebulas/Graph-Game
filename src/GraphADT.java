import java.util.ArrayList;

public interface GraphADT {
    void calcRoute();
    int getDegree();
    ArrayList<NodeADT> getAdjacent();
    boolean isAdjacentTo();
    int getPosition();
    int getWeight();
}

