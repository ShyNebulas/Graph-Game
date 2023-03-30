package com.graph.cs308group8;

public interface ModelADT {
    PairADT<?, ?> calcRoute(NodeADT start, NodeADT destination);
    void printGraph();
}
