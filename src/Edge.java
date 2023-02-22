public class Edge implements EdgeADT {
    private final int weight;
    private final int start;
    private final int destination;

    public Edge(int weight, int start, int destination) {
        this.weight = weight;
        this.start = start;
        this.destination = destination;
    }

    public int getWeight() { return this.weight; }
    public int getStart() { return this.start; }
    public int getDestination() { return this.destination; }
}
