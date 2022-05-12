package Dijkstra;

import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Dijkstra {
	private static final int INF = Integer.MAX_VALUE;

	private int[] parent;
	private int[] distance;

	protected ArrayList<Pair<Integer, Integer>>[] graph;
	protected int verticesCount;

	public Dijkstra(int[][] graph) {
		this.verticesCount = graph.length;
		this.parent = new int[verticesCount];
		this.distance = new int[verticesCount];
		this.graph = (ArrayList<Pair<Integer, Integer>>[]) new ArrayList[verticesCount];

		for (int i = 0; i < verticesCount; i++) {
			if (graph[i].length != verticesCount)
				throw new IllegalArgumentException("Graph should be given as an adjacency matrix");

			this.graph[i] = new ArrayList<>();
			for (int j = 0; j < verticesCount; j++) {
				if (graph[i][j] != -1)
					this.graph[i].add(new Pair<>(j, graph[i][j]));
			}
		}
	}

	public int[] getShortestDistances(int start) {
		boolean[] used = new boolean[verticesCount];

		Arrays.fill(distance, INF);
		Arrays.fill(parent, -1);

		distance[start] = 0;
		for (int i = 0; i < verticesCount; i++) {
			int currentVertice = -1;
			for (int j = 0; j < verticesCount; j++) {
				if (!used[j] && (currentVertice == -1 || distance[j] < distance[currentVertice])) {
					currentVertice = j;
				}
			}

			used[currentVertice] = true;
			for (var to : graph[currentVertice]) {
				if (!used[to.first()] && distance[to.first()] > distance[currentVertice] + to.second()) {
					distance[to.first()] = distance[currentVertice] + to.second();
					parent[to.first()] = currentVertice;
				}
			}
		}

		return distance;
	}

	public ArrayList<Integer> getShortestPath(int start, int end) {
		getShortestDistances(start);

		if (distance[end] == INF)
			throw new IllegalArgumentException("There is no path from vertice " + start + " to vertice " + end);

		ArrayList<Integer> result = new ArrayList<>();
		int currentVertice = end;
		while (parent[currentVertice] != -1) {
			result.add(currentVertice);
			currentVertice = parent[currentVertice];
		}
		result.add(start);

		Collections.reverse(result);
		return result;
	}
}
