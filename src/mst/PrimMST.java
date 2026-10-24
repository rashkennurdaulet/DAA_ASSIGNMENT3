package mst;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimMST {
    public double totalWeight;
    public List<Edge> mstEdges;
    public boolean isConnected;
    public long operations;

    public PrimMST(Graph graph) {
        totalWeight = 0;
        mstEdges = new ArrayList<>();
        operations = 0;

        if (graph.V == 0) {
            isConnected = true;
            return;
        }

        boolean[] visited = new boolean[graph.V];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        visit(graph, 0, visited, pq);

        while (!pq.isEmpty() && mstEdges.size() < graph.V - 1) {
            Edge edge = pq.poll();
            operations++;

            if (visited[edge.source] && visited[edge.dest]) continue;

            mstEdges.add(edge);
            totalWeight += edge.weight;

            if (!visited[edge.source]) visit(graph, edge.source, visited, pq);
            if (!visited[edge.dest]) visit(graph, edge.dest, visited, pq);
        }

        isConnected = (mstEdges.size() == graph.V - 1);
    }

    private void visit(Graph graph, int v, boolean[] visited, PriorityQueue<Edge> pq) {
        visited[v] = true;
        for (Edge edge : graph.adj.get(v)) {
            operations++;
            if (!visited[edge.dest]) {
                pq.add(edge);
                operations++;
            }
        }
    }
}