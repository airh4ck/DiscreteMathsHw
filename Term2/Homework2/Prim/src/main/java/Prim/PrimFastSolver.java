package Prim;

import utils.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PrimFastSolver extends PrimSolver {
	private static final int INF = 1_000_000_000 + 23;
	private static final int START_VERTICE = 0;

	public PrimFastSolver(int[][] graph) {
		super(graph);
	}

	public int[][] getMST() {
		boolean[] used = new boolean[verticesCount];
		int[] minDistance = new int[verticesCount];
		int[] parent = new int[verticesCount];
		PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>();

		Arrays.fill(parent, -1);
		Arrays.fill(minDistance, INF);
		minDistance[START_VERTICE] = 0;
		priorityQueue.add(new Pair<Integer, Integer>(START_VERTICE, 0));

		int[][] mstGraph = createEmptyGraph(verticesCount);

		for (int i = 0; i < verticesCount; i++) {
			if (priorityQueue.isEmpty())
				throw new IllegalArgumentException("Given graph isn't connected");

			var currentVertice = priorityQueue.poll().second();
			if (parent[currentVertice] != -1) {
				mstGraph[currentVertice][parent[currentVertice]] = graph[currentVertice][parent[currentVertice]];
				mstGraph[parent[currentVertice]][currentVertice] = graph[parent[currentVertice]][currentVertice];
			}

			used[currentVertice] = true;
			for (int j = 0; j < verticesCount; j++) {
				if (!used[j] && graph[currentVertice][j] != -1 && graph[currentVertice][j] < minDistance[j]) {
					priorityQueue.remove(new Pair<>(minDistance[j], j));
					minDistance[j] = graph[currentVertice][j];
					parent[j] = currentVertice;
					priorityQueue.add(new Pair<>(minDistance[j], j));
				}
			}
		}

		return mstGraph;
	}
}
