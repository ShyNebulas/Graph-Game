import java.util.*;

public class Model implements ModelADT {
    private final ArrayList<NodeADT> nodes;
    private final ArrayList<EdgeADT> edges;

    public Model(ArrayList<NodeADT> nodes, ArrayList<EdgeADT> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    //TODO: Implement
    public HashMap<NodeADT, ArrayList<EdgeADT>> calcRoute(NodeADT start, NodeADT destination) {
        //using dijkstra's algorithm
//        Node Pos, prev node, shortest distance to start node
//        List<Triple<NodeADT, NodeADT, Integer>> visited = new ArrayList<>();
//        List<Triple<NodeADT, NodeADT, Integer>> pQueue = new ArrayList<>();

        ArrayList<NodeADT> visited = new ArrayList<>();
        ArrayList<NodeADT> pQueue = new ArrayList<>();

        ArrayList<NodeADT> previous = new ArrayList<>();
        ArrayList<Integer> distance = new ArrayList<>();

        ArrayList<NodeADT> path = new ArrayList<>();

        int tempDistance = 0;

        //add all nodes to queue with no set previous and "infinity" distance
        for (int i = 0; i < nodes.size(); i++) {
            pQueue.add(nodes.get(i));
            previous.add(null);
            distance.add(999999);
        }

        //set start node distance to 0
        distance.set(start.getPosition(), 0);

        //while pQueue is not empty
        while (pQueue.size() != 0) {
            //set current to index of node with lowest distance from destination
            int current = nodes.indexOf(Collections.min(distance));

            //create list of nodes adjacent to current
            //List adj = getAdjacent(nodes.get(current));

            //for all adjacent nodes, set tempDistance to weight of edge between current and adjacent

            LinkedHashSet<EdgeADT> edges = this.graph.get(this.findMatchingNode(new Node(current)).get());
            for (EdgeADT edge : edges) {
                tempDistance = distance.get(indexOf(previous.get(current))) + edge.getWeight();
            }

            if (tempDistance < distance.get(j)) {
                distance.set(j, tempDistance);
                previous.set(j, nodes.get(current));
            }


            visited.add(nodes.get(current));
        //drop below here when pQueue is empty
        }

        //if destination is visited after pQueue is empty (if it isn't then there is no route)
        if (visited.contains(destination)) {
            int finalIndex = visited.indexOf(destination);
            int finalDistance = distance.get(finalIndex);
            //work backwards using previous from destination to start adding to front of array to get path
            while (!path.contains(start)) {
                path.add(0, previous.get(finalIndex));
            }
        }

        //not sure how to format this?
        //is there an easy way to get edges if you have the 2 nodes instead of looping through all the edges?
        return new HashMap<NodeADT, ArrayList<EdgeADT>>();
    }

}
