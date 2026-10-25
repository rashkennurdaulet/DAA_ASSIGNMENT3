import graph.Graph;
import mst.KruskalMST;
import mst.PrimMST;
import connectivity.TopologicalSort;
import connectivity.KosarajuSCC;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Task 1: Minimum Spanning Tree ---");
        Graph undirectedGraph = new Graph(5, false);
        undirectedGraph.addEdge(0, 1, 2.0);
        undirectedGraph.addEdge(0, 3, 6.0);
        undirectedGraph.addEdge(1, 2, 3.0);
        undirectedGraph.addEdge(1, 3, 8.0);
        undirectedGraph.addEdge(1, 4, 5.0);
        undirectedGraph.addEdge(2, 4, 7.0);
        undirectedGraph.addEdge(3, 4, 9.0);

        KruskalMST kruskal = new KruskalMST(undirectedGraph);
        System.out.println("Kruskal Weight: " + kruskal.totalWeight + " | Connected: " + kruskal.isConnected);
        PrimMST prim = new PrimMST(undirectedGraph);
        System.out.println("Prim Weight: " + prim.totalWeight + " | Connected: " + prim.isConnected);

        System.out.println("\n--- Task 2: Connectivity (Topological Sort & SCC) ---");

        Graph dag = new Graph(6, true);
        dag.addEdge(5, 2, 1.0);
        dag.addEdge(5, 0, 1.0);
        dag.addEdge(4, 0, 1.0);
        dag.addEdge(4, 1, 1.0);
        dag.addEdge(2, 3, 1.0);
        dag.addEdge(3, 1, 1.0);

        TopologicalSort topoSort = new TopologicalSort(dag);
        System.out.println("DAG Topological Order: " + topoSort.order + " | Has Cycle: " + topoSort.hasCycle);

        Graph sccGraph = new Graph(5, true);
        sccGraph.addEdge(0, 2, 1.0);
        sccGraph.addEdge(2, 1, 1.0);
        sccGraph.addEdge(1, 0, 1.0);
        sccGraph.addEdge(0, 3, 1.0);
        sccGraph.addEdge(3, 4, 1.0);

        TopologicalSort cycleCheck = new TopologicalSort(sccGraph);
        System.out.println("Cyclic Graph Topological Order: " + cycleCheck.order + " | Has Cycle: " + cycleCheck.hasCycle);

        KosarajuSCC scc = new KosarajuSCC(sccGraph);
        System.out.println("Strongly Connected Components: " + scc.sccList);

        System.out.println("\n--- Task 3: Shortest Paths (Dijkstra) ---");

        Graph cityMap = new Graph(6, true);
        cityMap.addEdge(0, 1, 4.0);
        cityMap.addEdge(0, 2, 2.0);
        cityMap.addEdge(1, 3, 5.0);
        cityMap.addEdge(2, 1, 1.0);
        cityMap.addEdge(2, 3, 8.0);
        cityMap.addEdge(2, 4, 10.0);
        cityMap.addEdge(3, 4, 2.0);
        cityMap.addEdge(3, 5, 6.0);
        cityMap.addEdge(4, 5, 3.0);

        Graph cityMapWithIsolated = new Graph(7, true);
        for(graph.Edge e : cityMap.allEdges) {
            cityMapWithIsolated.addEdge(e.source, e.dest, e.weight);
        }

        int source = 0;
        shortestpath.Dijkstra dijkstra = new shortestpath.Dijkstra(cityMapWithIsolated, source);

        System.out.println("Shortest paths from District " + source + ":");
        for (int i = 0; i < cityMapWithIsolated.V; i++) {
            if (dijkstra.hasPathTo(i)) {
                System.out.printf("To District %d: Cost = %.1f, Path = %s%n", i, dijkstra.distTo[i], dijkstra.pathTo(i));
            } else {
                System.out.printf("To District %d: UNREACHABLE%n", i);
            }
        }
    }
}