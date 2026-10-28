import pandas as pd
import matplotlib.pyplot as plt
import os

os.makedirs('results/plots', exist_ok=True)

df = pd.read_csv('results/tables/benchmark.csv')

mst_algos = ['Kruskal', 'Prim']
conn_algos = ['TopologicalSort', 'KosarajuSCC']
sp_algos = ['Dijkstra']
sizes = ['Small', 'Medium', 'Large']

def plot_comparison(algos, metric, title, filename):
    data = df[df['Algorithm'].isin(algos)]
    pivot = data.pivot(index='Size', columns='Algorithm', values=metric)
    pivot = pivot.reindex(sizes) # Сортируем по размеру

    ax = pivot.plot(kind='bar', figsize=(8, 6), rot=0)
    plt.title(title)
    plt.ylabel('Time (ms)' if 'Time' in metric else 'Operations Count')
    plt.xlabel('Graph Size')
    plt.grid(axis='y', linestyle='--', alpha=0.7)
    plt.tight_layout()

    plt.savefig(f'results/plots/{filename}')
    plt.close()

plot_comparison(mst_algos, 'AvgTimeMs', 'MST Algorithms: Execution Time', 'mst_time.png')
plot_comparison(mst_algos, 'AvgOperations', 'MST Algorithms: Operations Count', 'mst_ops.png')

plot_comparison(conn_algos, 'AvgTimeMs', 'Connectivity Algorithms: Execution Time', 'conn_time.png')
plot_comparison(conn_algos, 'AvgOperations', 'Connectivity Algorithms: Operations Count', 'conn_ops.png')

plot_comparison(sp_algos, 'AvgTimeMs', 'Dijkstra: Execution Time', 'dijkstra_time.png')
plot_comparison(sp_algos, 'AvgOperations', 'Dijkstra: Operations Count', 'dijkstra_ops.png')

print("The graphs have been successfully generated in the results/plots/ folder.")