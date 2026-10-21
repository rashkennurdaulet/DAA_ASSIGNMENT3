package dsu;

public class DSU {
    private int[] parent;
    private int[] rank;
    private int components;
    private long operations;

    public DSU(int size) {
        parent = new int[size];
        rank = new int[size];
        components = size;
        operations = 0;

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) {
        operations++;
        if (parent[i] == i) {
            return i;
        }
        parent[i] = find(parent[i]);
        return parent[i];
    }

    public boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        operations++;
        if (rootI != rootJ) {
            if (rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++;
            }
            components--;
            return true;
        }
        return false;
    }

    public int getComponents() {
        return components;
    }

    public long getOperations() {
        return operations;
    }

    public void resetOperations() {
        operations = 0;
    }
}