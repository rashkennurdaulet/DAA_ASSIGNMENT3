package mst;

import dsu.DSU;
import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMST {
    public double totalWeight;
    public List<Edge> mstEdges;
    public boolean isConnected;
    public long operations;

    public KruskalMST(Graph graph) {
        totalWeight = 0;
        mstEdges = new ArrayList<>();
        operations = 0;

        if (graph.V == 0) {
            isConnected = true;
            return;
        }

        List<Edge> edges = new ArrayList<>(graph.allEdges);
        Collections.sort(edges);

        operations += edges.size() * (long)(Math.log(edges.size()) / Math.log(2));

        DSU dsu = new DSU(graph.V);

        for (Edge edge : edges) {
            operations++;
            if (dsu.union(edge.source, edge.dest)) {
                mstEdges.add(edge);
                totalWeight += edge.weight;

                if (mstEdges.size() == graph.V - 1) {
                    break;
                }
            }
        }

        isConnected = (dsu.getComponents() == 1);
        operations += dsu.getOperations();
    }
}