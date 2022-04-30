package DSU;

public class DSU {
	protected int[] parent;
	protected int[] rank;

	public DSU(int n) {
		parent = new int[n];
		rank = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public int getParent(int v) {
		if (parent[v] == v)
			return v;
		return parent[v] = getParent(parent[v]);
	}

	public boolean joinSets(int v, int u) {
		v = getParent(v);
		u = getParent(u);

		if (u == v)
			return true;

		if (rank[u] < rank[v])
			parent[u] = v;
		else
			parent[v] = u;

		if (rank[u] == rank[v])
			rank[u]++;

		return false;
	}
}
