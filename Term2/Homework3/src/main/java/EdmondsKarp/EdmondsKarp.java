package EdmondsKarp;

import utils.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarp {
	private static final int INF = Integer.MAX_VALUE;

	private int verticesCount;
	private ArrayList<Integer>[] graph;
	private ArrayList<Edge> edgeList;

	public EdmondsKarp(ArrayList<Edge>[] graph) {
		this.verticesCount = graph.length;
		this.graph = (ArrayList<Integer>[]) new ArrayList[verticesCount];
		this.edgeList = new ArrayList<>();

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
		int[] distance = new int[verticesCount];
		int[] parent = new int[verticesCount];
		Arrays.fill(distance, INF);
		Arrays.fill(parent, -1);
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
					parent[currentEdge.to()] = index;
				}
			}
		}

		if (distance[t] == INF)
			return false;

		int currentVertice = t;
		int minimumResidualCapacity = INF;
		while (parent[currentVertice] != -1) {
			minimumResidualCapacity = Math.min(
					minimumResidualCapacity,
					edgeList.get(parent[currentVertice]).capacity() - edgeList.get(parent[currentVertice]).flow()
			);
			currentVertice = edgeList.get(parent[currentVertice]).from();
		}

		currentVertice = t;
		while (parent[currentVertice] != -1) {
			var currentEdge = edgeList.get(parent[currentVertice]);
			var reverseEdge = edgeList.get(parent[currentVertice] ^ 1);
			edgeList.set(parent[currentVertice], new Edge(currentEdge.from(), currentEdge.to(), currentEdge.capacity(), currentEdge.flow() + minimumResidualCapacity));
			edgeList.set(parent[currentVertice] ^ 1, new Edge(reverseEdge.from(), reverseEdge.to(), reverseEdge.capacity(), reverseEdge.flow() - minimumResidualCapacity));

			currentVertice = edgeList.get(parent[currentVertice]).from();
		}

		return true;
	}

	public int findMaxFlow(int s, int t) {
		while (bfs(s, t)) {}

		int result = 0;
		for (var index : graph[s])
			result += edgeList.get(index).flow();

		return result;
	}
}
