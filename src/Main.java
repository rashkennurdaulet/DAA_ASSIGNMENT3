import graph.Graph;
import mst.KruskalMST;
import mst.PrimMST;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5, false);
        graph.addEdge(0, 1, 2.0);
        graph.addEdge(0, 3, 6.0);
        graph.addEdge(1, 2, 3.0);
        graph.addEdge(1, 3, 8.0);
        graph.addEdge(1, 4, 5.0);
        graph.addEdge(2, 4, 7.0);
        graph.addEdge(3, 4, 9.0);

        System.out.println("--- Task 1: Minimum Spanning Tree ---");

        KruskalMST kruskal = new KruskalMST(graph);
        System.out.println("Kruskal Weight: " + kruskal.totalWeight + " | Connected: " + kruskal.isConnected);

        PrimMST prim = new PrimMST(graph);
        System.out.println("Prim Weight: " + prim.totalWeight + " | Connected: " + prim.isConnected);

        if (Math.abs(kruskal.totalWeight - prim.totalWeight) < 1e-9) {
            System.out.println("SUCCESS: Both algorithms returned the same MST weight!");
        }
    }
}