namespace Kruskal;

public class DSU
{
	private int[] parent;
	private int[] rank;

	public DSU(int n) 
	{
		parent = new int[n];
		rank = new int[n];

		for (int i = 0; i < n; i++) 
		{
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public int GetParent(int v) 
	{
		if (parent[v] == v) 
			return v;
		
		return parent[v] = GetParent(parent[v]);
	}

	public bool JoinSets(int v, int u) 
	{
		u = GetParent(u);
		v = GetParent(v);

		if (u == v)
		{
			return true;
		}

		if (rank[u] < rank[v]) 
		{
			parent[u] = v;
		}
		else
		{
			parent[v] = u;
		}

		if (rank[u] == rank[v])
		{
			rank[u]++;
		}
		
		return false;
	}
}