package Kruskal;

import DSU.DSU;
import utils.Edge;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {
	protected ArrayList<Edge>[] graph;
	protected ArrayList<Edge> edgeList;
	private int n;

	public Kruskal(int[][] graph) {
		n = graph.length;
		this.graph = (ArrayList<Edge>[]) new ArrayList[n];
		this.edgeList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (graph[i].length != n)
				throw new IllegalArgumentException("Graph must be given in a form of adjacency matrix");

			this.graph[i] = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if (graph[i][j] == -1)
					continue;

				this.edgeList.add(new Edge(i, j, graph[i][j]));
				this.graph[i].add(new Edge(i, j, graph[i][j]));
			}
		}
	}

	private ArrayList<Edge>[] createEmptyGraph(int n) {
		ArrayList<Edge>[] result = (ArrayList<Edge>[]) new ArrayList[n];
		for (var row : result)
			row = new ArrayList<>();

		return result;
	}

	private boolean isConnected(DSU dsu) {
		for (int i = 0; i < n; i++) {
			if (dsu.getParent(i) != dsu.getParent(0))
				return false;
		}

		return true;
	}

	public ArrayList<Edge>[] findMST() {
		DSU dsu = new DSU(n);

		ArrayList<Edge>[] mstGraph = createEmptyGraph(n);

		edgeList.sort(Edge::compareTo);
		for (var edge : edgeList) {
			if (!dsu.joinSets(edge.getFrom(), edge.getTo())) {
				mstGraph[edge.getFrom()].add(edge);
				mstGraph[edge.getTo()].add(edge);
			}
		}

		if (!isConnected(dsu)) {
			throw new IllegalArgumentException("Given graph isn't connected");
		}

		return mstGraph;
	}

	public int findMSTWeight() {
		var mstGraph = findMST();
		int resultWeight = 0;

		for (var vertice : mstGraph) {
			for (var edge : vertice)
				resultWeight += edge.getWeight();
		}

		return resultWeight / 2;
	}
}
