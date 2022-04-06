namespace Kruskal;

using System.Collections.Generic;

public class Kruskal
{
	private List<Edge>[] graph;
	private List<Edge> edgeList;
	private int n;

	public Kruskal(int[,] graph)
	{
		n = graph.GetLength(0);

		if (n != graph.GetLength(1)) 
		{
			throw new ArgumentException("Graph should be given as a square matrix");
		}

		this.graph = new List<Edge>[n];
		this.edgeList = new List<Edge>();

		for (int i = 0; i < n; i++) 
		{
			this.graph[i] = new List<Edge>();
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

	public int[,] FindMST() 
	{
		int[,] result = new int[n, n];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				result[i, j] = -1;
			}
		}

		DSU dsu = new DSU(n);

		edgeList.Sort();

		foreach (var edge in edgeList) 
		{
			if (!dsu.JoinSets(edge.From, edge.To))
				result[edge.From, edge.To] = result[edge.To, edge.From] = edge.Weight;
		}
		return result;
	}
}
