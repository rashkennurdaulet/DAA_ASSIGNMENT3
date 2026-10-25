package shortestpath;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public double[] distTo;
    public int[] edgeTo;
    public long operations;

    private static class Node implements Comparable<Node> {
        int vertex;
        double dist;

        Node(int v, double d) {
            this.vertex = v;
            this.dist = d;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.dist, other.dist);
        }
    }

    public Dijkstra(Graph graph, int source) {
        distTo = new double[graph.V];
        edgeTo = new int[graph.V];
        operations = 0;

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        Arrays.fill(edgeTo, -1);
        distTo[source] = 0.0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0.0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            operations++;
            int u = current.vertex;

            if (current.dist > distTo[u]) continue;

            for (Edge edge : graph.adj.get(u)) {
                operations++;
                int v = edge.dest;
                double newDist = distTo[u] + edge.weight;

                if (newDist < distTo[v]) {
                    distTo[v] = newDist;
                    edgeTo[v] = u;
                    pq.add(new Node(v, newDist));
                    operations++;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public List<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        for (int x = v; x != -1; x = edgeTo[x]) {
            path.add(x);
        }
        Collections.reverse(path);
        return path;
    }
}