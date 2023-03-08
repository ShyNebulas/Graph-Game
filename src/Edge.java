public class Edge implements EdgeADT {
    private final int weight;
    private final NodeADT destination;

    public Edge(int weight, NodeADT destination) {
        this.weight = weight;
        this.destination = destination;
    }

    public int getWeight() { return this.weight; }
    public NodeADT getDestination() { return this.destination; }

    @Override
    public String toString() { return String.format("Edge (%s) %d", this.getDestination(), this.getWeight()); }
}
