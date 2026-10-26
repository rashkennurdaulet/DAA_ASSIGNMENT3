import graph.Graph;
import mst.KruskalMST;
import mst.PrimMST;
import connectivity.KosarajuSCC;
import connectivity.TopologicalSort;
import shortestpath.Dijkstra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Experiment {
    private static final int[][] SIZES = {{10, 30}, {100, 500}, {1000, 5000}};
    private static final String[] SIZE_NAMES = {"Small", "Medium", "Large"};
    private static final int RUNS = 5;
    private final Random random = new Random(42);

    public void runAllExperiments(String csvFilePath) {
        new File("results/tables").mkdirs();
        new File("results/plots").mkdirs();

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath))) {
            writer.println("Size,Vertices,Edges,Algorithm,AvgTimeMs,AvgOperations");

            for (int s = 0; s < SIZES.length; s++) {
                int V = SIZES[s][0];
                int E = SIZES[s][1];
                String sizeName = SIZE_NAMES[s];

                System.out.println("Running experiments for " + sizeName + " graph (" + V + "V, " + E + "E)...");

                long kruskalTime = 0, kruskalOps = 0;
                long primTime = 0, primOps = 0;
                long topoTime = 0, topoOps = 0;
                long sccTime = 0, sccOps = 0;
                long dijkstraTime = 0, dijkstraOps = 0;

                for (int r = 0; r < RUNS; r++) {
                    Graph undirected = generateGraph(V, E, false);

                    long start = System.nanoTime();
                    KruskalMST kruskal = new KruskalMST(undirected);
                    kruskalTime += (System.nanoTime() - start);
                    kruskalOps += kruskal.operations;

                    start = System.nanoTime();
                    PrimMST prim = new PrimMST(undirected);
                    primTime += (System.nanoTime() - start);
                    primOps += prim.operations;

                    Graph directed = generateGraph(V, E, true);

                    start = System.nanoTime();
                    TopologicalSort topo = new TopologicalSort(directed);
                    topoTime += (System.nanoTime() - start);
                    topoOps += topo.operations;

                    start = System.nanoTime();
                    KosarajuSCC scc = new KosarajuSCC(directed);
                    sccTime += (System.nanoTime() - start);
                    sccOps += scc.operations;

                    start = System.nanoTime();
                    Dijkstra dijkstra = new Dijkstra(directed, 0);
                    dijkstraTime += (System.nanoTime() - start);
                    dijkstraOps += dijkstra.operations;
                }

                writeRow(writer, sizeName, V, E, "Kruskal", kruskalTime, kruskalOps);
                writeRow(writer, sizeName, V, E, "Prim", primTime, primOps);
                writeRow(writer, sizeName, V, E, "TopologicalSort", topoTime, topoOps);
                writeRow(writer, sizeName, V, E, "KosarajuSCC", sccTime, sccOps);
                writeRow(writer, sizeName, V, E, "Dijkstra", dijkstraTime, dijkstraOps);
            }
            System.out.println("Experiments finished! Data saved to " + csvFilePath);
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }

    private void writeRow(PrintWriter writer, String sizeName, int V, int E, String algo, long totalTimeNs, long totalOps) {
        double avgTimeMs = (totalTimeNs / (double) RUNS) / 1_000_000.0;
        long avgOps = totalOps / RUNS;
        writer.printf(java.util.Locale.US, "%s,%d,%d,%s,%.4f,%d%n", sizeName, V, E, algo, avgTimeMs, avgOps);
    }

    private Graph generateGraph(int V, int E, boolean isDirected) {
        Graph g = new Graph(V, isDirected);
        for (int i = 0; i < V - 1; i++) {
            g.addEdge(i, i + 1, 1.0 + random.nextDouble() * 99.0);
        }

        int remainingEdges = E - (V - 1);
        for (int i = 0; i < remainingEdges; i++) {
            int u = random.nextInt(V);
            int v = random.nextInt(V);
            if (u != v) {
                g.addEdge(u, v, 1.0 + random.nextDouble() * 99.0);
            }
        }
        return g;
    }
}