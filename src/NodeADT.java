public interface NodeADT {
    int getPosition();
    @Override
    boolean equals(Object object);
    @Override
    String toString();
    int compareTo(NodeADT node);
}
