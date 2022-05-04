package Kruskal;

import DSU.DSU;
import utils.Edge;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {
	protected ArrayList<Edge>[] graph;
	protected ArrayList<Edge> edgeList;
	private int n;

	public Kruskal(ArrayList<Edge>[] graph) {
		n = graph.length;
		this.graph = graph;
		this.edgeList = new ArrayList<>();
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
