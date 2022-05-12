package Dinitz;

import utils.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Dinitz {
	private static final int INF = Integer.MAX_VALUE;

	private int[] distance;
	private int[] used;
	private int verticesCount;
	private ArrayList<Integer>[] graph;
	private ArrayList<Edge> edgeList;
	private int timer;

	public Dinitz(ArrayList<Edge>[] graph) {
		this.verticesCount = graph.length;
		this.used = new int[verticesCount];
		this.distance = new int[verticesCount];
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

	private boolean bfs(int s, int t) {
		Arrays.fill(distance, INF);
		distance[0] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);

		while (!queue.isEmpty()) {
			int currentVertice = queue.poll();

			for (int index : graph[currentVertice]) {
				var currentEdge = edgeList.get(index);

				if (currentEdge.flow() - currentEdge.capacity() == 0)
					continue;

				if (distance[currentEdge.to()] > distance[currentVertice] + 1) {
					distance[currentEdge.to()] = distance[currentVertice] + 1;
					queue.add(currentEdge.to());
				}
			}
		}

		return distance[t] != INF;
	}

	private int dfs(int v, int t, int delta) {
		if (used[v] == timer)
			return 0;

		if (v == t) {
			return delta;
		}

		used[v] = timer;
		for (int index : graph[v]) {
			var currentEdge = edgeList.get(index);
			if (
					currentEdge.capacity() == 0 ||
					currentEdge.flow() >= currentEdge.capacity() ||
					distance[currentEdge.to()] != distance[v] + 1
			)
				continue;

			int newDelta = dfs(currentEdge.to(), t, Math.min(delta, currentEdge.capacity() - currentEdge.flow()));

			if (newDelta > 0) {
				edgeList.set(index, new Edge(currentEdge.from(), currentEdge.to(), currentEdge.capacity(), currentEdge.flow() + newDelta));
				var reverseEdge = edgeList.get(index ^ 1);
				edgeList.set(index ^ 1, new Edge(reverseEdge.from(), reverseEdge.to(), reverseEdge.capacity(), reverseEdge.flow() - newDelta));

				return newDelta;
			}
		}

		return 0;
	}

	public int findMaxFlow(int s, int t) {
		while (bfs(s, t)) {
			dfs(s, t, INF);
			timer++;
		}

		int result = 0;
		for (var index : graph[s])
			result += edgeList.get(index).flow();

		return result;
	}
}
