package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public int V;
    public List<List<Edge>> adj;
    public List<Edge> allEdges;
    public boolean isDirected;

    public Graph(int V, boolean isDirected) {
        this.V = V;
        this.isDirected = isDirected;
        this.adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        this.allEdges = new ArrayList<>();
    }

    public void addEdge(int source, int dest, double weight) {
        Edge edge = new Edge(source, dest, weight);
        adj.get(source).add(edge);
        allEdges.add(edge);

        if (!isDirected) {
            adj.get(dest).add(new Edge(dest, source, weight));
        }
    }
}