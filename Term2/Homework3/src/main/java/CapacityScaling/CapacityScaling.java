package CapacityScaling;

import utils.Edge;

import java.util.ArrayList;

public class CapacityScaling {
	private static final int INF = 1_000_000_000 + 23;

	private int[] used;
	private int verticesCount;
	private ArrayList<Integer>[] graph;
	private ArrayList<Edge> edgeList;
	private int timer;

	public CapacityScaling(ArrayList<Edge>[] graph) {
		this.verticesCount = graph.length;
		this.used = new int[verticesCount];
		this.graph = (ArrayList<Integer>[]) new ArrayList[verticesCount];
		this.edgeList = new ArrayList<>();
		this.timer = 1;

		for (int i = 0; i < verticesCount; i++) {
			this.graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < verticesCount; i++) {
			for (var edge : graph[i]) {
				this.graph[edge.from()].add(edgeList.size());
				this.edgeList.add(new Edge(edge.from(), edge.to(), edge.capacity(), 0));
				this.graph[edge.to()].add(edgeList.size());
				this.edgeList.add(new Edge(edge.to(), edge.from(), 0, 0));
			}
		}
	}

	private int dfs(int v, int t, int delta, int scale) {
		if (used[v] == timer)
			return 0;

		if (v == t) {
			return delta;
		}

		used[v] = timer;
		for (int index : graph[v]) {
			var currentEdge = edgeList.get(index);
			if (
					currentEdge.capacity() - currentEdge.flow() == 0 ||
					Math.abs(currentEdge.capacity() - currentEdge.flow()) < scale
			)
				continue;

			int newDelta = dfs(currentEdge.to(), t, Math.min(delta, currentEdge.capacity() - currentEdge.flow()), scale);

			if (newDelta > 0) {
				edgeList.set(index, new Edge(currentEdge.from(), currentEdge.to(), currentEdge.capacity(), currentEdge.flow() + newDelta));
				var reverseEdge = edgeList.get(index ^ 1);
				edgeList.set(index ^ 1, new Edge(reverseEdge.from(), reverseEdge.to(), reverseEdge.capacity(), reverseEdge.flow() - newDelta));

				return newDelta;
			}
		}
		return 0;
	}

	private int getMaxCapacity() {
		var result = 0;
		for (var edge : edgeList)
			result = Math.max(result, edge.capacity());
		return result;
	}

	public int findMaxFlow(int s, int t) {
		int maxCapacity = getMaxCapacity();

		int k = 1;
		while ((k << 1) <= maxCapacity)
			k <<= 1;

		for (; k > 0; k >>= 1, timer++) {
			while (dfs(s, t, INF, k) != 0)
				timer++;
		}

		int result = 0;
		for (var index : graph[s])
			result += edgeList.get(index).flow();

		return result;
	}
}
