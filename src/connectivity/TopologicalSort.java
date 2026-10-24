package connectivity;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort {
    public List<Integer> order;
    public boolean hasCycle;
    public long operations;

    public TopologicalSort(Graph graph) {
        order = new ArrayList<>();
        hasCycle = false;
        operations = 0;

        int[] state = new int[graph.V];

        for (int i = 0; i < graph.V; i++) {
            if (state[i] == 0) {
                dfs(graph, i, state);
                if (hasCycle) break;
            }
        }

        if (!hasCycle) {
            Collections.reverse(order);
        } else {
            order.clear();
        }
    }

    private void dfs(Graph graph, int v, int[] state) {
        state[v] = 1;
        operations++;

        for (Edge edge : graph.adj.get(v)) {
            operations++;
            int neighbor = edge.dest;
            if (state[neighbor] == 1) {
                hasCycle = true;
                return;
            } else if (state[neighbor] == 0) {
                dfs(graph, neighbor, state);
            }
        }
        state[v] = 2;
        order.add(v);
    }
}