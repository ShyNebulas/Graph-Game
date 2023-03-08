import java.util.Objects;
public class Node implements NodeADT {
    private final int position;

    public Node(int position) { this.position = position; }

    public int getPosition() { return this.position; }

    @Override
    public boolean equals(Object object) {
        // Self check
        if (object == this) { return true; }
        // Null Check, type check & cast
        if (object == null || object.getClass() != getClass()) { return false; }
        // Field Comparison
        return Objects.equals(this.position, ((Node) object).getPosition());
    }

    @Override
    public String toString() { return String.format("Node %d", this.getPosition()); }
}
