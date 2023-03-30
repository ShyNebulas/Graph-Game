package com.graph.cs308group8;

import java.io.File;
import java.util.*;

public class Model implements ModelADT {
    private final File file;
    private final TreeMap<NodeADT, LinkedHashSet<EdgeADT>> graph = new TreeMap<>();

    public Model(File file) {
        this.file = file;
        createGraph();
    }

    private Optional<NodeADT> findMatchingNode(NodeADT x) {
        return this.graph.keySet().stream().filter(node -> node.equals(x)).findFirst();
    }

    private void createGraph() {
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                // Current line in text file
                final String[] line = scanner.nextLine().replaceAll("\\D+"," ").split(" ");
                for(int i = 0; i < 2; i++) {
                    Edge edge = new Edge(new Node(Integer.parseInt(line[1 - i])), Integer.parseInt(line[2]));
                    // Finds matching object
                    final NodeADT node = new Node(Integer.parseInt(line[i]));
                    final Optional<NodeADT> inGraph = this.findMatchingNode(node);
                    // Adds to existing edges
                    LinkedHashSet<EdgeADT> edges = inGraph.isPresent() ? this.graph.get(inGraph.get()) : new LinkedHashSet<>();
                    edges.add(edge);
                    this.graph.put(inGraph.orElse(node), edges);
                }
            }
            scanner.close();
        } catch (Exception error) { error.printStackTrace(); }
    }

    public TreeMap<NodeADT, LinkedHashSet<EdgeADT>> getGraph() { return this.graph; }

    public PairADT<?, ?> calcRoute(NodeADT start, NodeADT destination) {
        TreeMap<NodeADT, Integer> distances = new TreeMap<>();
        TreeMap<NodeADT, NodeADT> previousNodes = new TreeMap<>();
        PriorityQueue<NodeADT> pq = new PriorityQueue<>(Comparator.comparingInt(value -> distances.getOrDefault(value, Integer.MAX_VALUE)));

        distances.put(start, 0);
        pq.offer(start);

        while (!pq.isEmpty()){
            NodeADT current = pq.poll();

            if (current.equals(destination)){
                break;
            }

            for (EdgeADT edge : this.graph.get(current)){
                Edge castedEdge = (Edge) edge;
                NodeADT nextNode = castedEdge.destination();
                int distanceFromStart = distances.get(current) + castedEdge.weight();

                if (distances.containsKey(nextNode)){
                    int distStartToNext = distances.get(nextNode);
                    if (distanceFromStart < distStartToNext){
                        distances.put(nextNode, distanceFromStart);
                        previousNodes.put(nextNode, current);
                        pq.remove(nextNode);
                        pq.offer(nextNode);
                    }
                } else {
                    distances.put(nextNode, distanceFromStart);
                    previousNodes.put(nextNode, current);
                    pq.offer(nextNode);
                }
            }
        }

        ArrayList<NodeADT> shortestPath = new ArrayList<>();
        NodeADT current = destination;

        while(previousNodes.containsKey(current)){
            shortestPath.add(current);
            current = previousNodes.get(current);
        }

        if(shortestPath.isEmpty()){
            return null;
        }

        shortestPath.add(start);
        Collections.reverse(shortestPath);
        int destDistance = distances.get(destination);

        return new Pair<>(destDistance, shortestPath);
    }

    public void printGraph() {
        for(Map.Entry<NodeADT, LinkedHashSet<EdgeADT>> entry : this.graph.entrySet()) {
            System.out.printf("Key: %s; Values: %s\n", entry.getKey(), entry.getValue());
        }
    }
}
