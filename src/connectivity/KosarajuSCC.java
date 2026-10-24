package connectivity;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KosarajuSCC {
    public List<List<Integer>> sccList;
    public long operations;

    public KosarajuSCC(Graph graph) {
        sccList = new ArrayList<>();
        operations = 0;

        boolean[] visited = new boolean[graph.V];
        List<Integer> finishOrder = new ArrayList<>();

        for (int i = 0; i < graph.V; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, finishOrder);
            }
        }

        Graph transposed = new Graph(graph.V, true);
        for (Edge e : graph.allEdges) {
            operations++;
            transposed.addEdge(e.dest, e.source, e.weight);
        }

        visited = new boolean[graph.V];
        Collections.reverse(finishOrder);

        for (int v : finishOrder) {
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                dfsCollect(transposed, v, visited, component);
                sccList.add(component);
            }
        }
    }

    private void dfs(Graph graph, int v, boolean[] visited, List<Integer> finishOrder) {
        visited[v] = true;
        operations++;
        for (Edge edge : graph.adj.get(v)) {
            operations++;
            if (!visited[edge.dest]) {
                dfs(graph, edge.dest, visited, finishOrder);
            }
        }
        finishOrder.add(v);
    }

    private void dfsCollect(Graph graph, int v, boolean[] visited, List<Integer> component) {
        visited[v] = true;
        operations++;
        component.add(v);
        for (Edge edge : graph.adj.get(v)) {
            operations++;
            if (!visited[edge.dest]) {
                dfsCollect(graph, edge.dest, visited, component);
            }
        }
    }
}