namespace Kruskal;

using System.Collections.Generic;

public class Kruskal
{
	private List<Edge>[] graph;
	private List<Edge> edgeList;
	private int n;

	public Kruskal(int[,] graph)
	{
		n = graph.Length;
		this.graph = new List<Edge>[graph.Length];
		this.edgeList = new List<Edge>();

		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				if (graph[i, j] == -1)
					continue;

				this.edgeList.Add(new Edge(i, j, graph[i, j]));
				this.graph[i].Add(new Edge(i, j, graph[i, j]));
			}
		}
	}

	public int FindMSTWeight() 
	{
		DSU dsu = new DSU(n);

		edgeList.Sort();

		int resultWeight = 0;
		foreach (var edge in edgeList) 
		{
			if (!dsu.JoinSets(edge.From, edge.To))
				resultWeight += edge.Weight;
		}

		return resultWeight;
	}
}
