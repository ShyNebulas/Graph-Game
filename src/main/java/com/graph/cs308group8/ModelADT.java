package com.graph.cs308group8;

import java.util.*;

public interface ModelADT {
    PairADT<?, ?> calcRoute(NodeADT start, NodeADT destination);
    TreeMap<NodeADT, LinkedHashSet<EdgeADT>> getGraph();
    void printGraph();
}
