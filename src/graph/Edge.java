package graph;

public class Edge implements Comparable<Edge> {
    public int source;
    public int dest;
    public double weight;

    public Edge(int source, int dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }
}