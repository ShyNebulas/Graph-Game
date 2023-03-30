package com.graph.cs308group8;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ModelADT model = new Model(new File("src/main/java/com/graph/cs308group8/data.txt"));
        model.printGraph();
        System.out.println(model.calcRoute(new Node(0), new Node(1)));
    }
}
