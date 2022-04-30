package Prim;

import java.util.Arrays;

public class PrimSolver {
	private static final int INF = 1_000_000_000 + 23;
	private static final int START_VERTICE = 0;

	protected int verticesCount;
	protected int[][] graph;

	public PrimSolver(int[][] graph) {
		this.graph = graph;
		this.verticesCount = graph.length;

		for (var row : graph) {
			if (row.length != verticesCount) {
				throw new IllegalArgumentException("Graph should be given as an adjacency matrix");
			}
		}
	}

	protected int[][] createEmptyGraph(int vertices) {
		int[][] graph = new int[vertices][vertices];
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++)
				graph[i][j] = -1;
		}

		return graph;
	}

	public int[][] getMST() {
		boolean used[] = new boolean[verticesCount];
		int minDistance[] = new int[verticesCount];
		int parent[] = new int[verticesCount];
		Arrays.fill(minDistance, INF);
		Arrays.fill(parent, -1);
		minDistance[START_VERTICE] = 0;

		int[][] mstGraph = createEmptyGraph(verticesCount);

		for (int i = 0; i < verticesCount; i++) {
			int currentVertice = -1;
			for (int j = 0; j < verticesCount; j++) {
				if (!used[j] && (currentVertice == -1 || minDistance[j] < minDistance[currentVertice])) {
					currentVertice = j;
				}
			}

			if (minDistance[currentVertice] == INF) {
				throw new IllegalArgumentException("Given graph isn't connected");
			}

			used[currentVertice] = true;
			if (parent[currentVertice] != -1) {
				mstGraph[parent[currentVertice]][currentVertice] = graph[parent[currentVertice]][currentVertice];
				mstGraph[currentVertice][parent[currentVertice]] = graph[currentVertice][parent[currentVertice]];
			}
			for (int j = 0; j < verticesCount; j++) {
				if (graph[currentVertice][j] != -1 && graph[currentVertice][j] < minDistance[j]) {
					minDistance[j] = graph[currentVertice][j];
					parent[j] = currentVertice;
				}
			}
		}

		return mstGraph;
	}

	public int getMSTWeight() {
		var mstGraph = getMST();
		int mstWeight = 0;
		for (int i = 0; i < verticesCount; i++) {
			for (int j = 0; j < verticesCount; j++) {
				if (mstGraph[i][j] != -1)
					mstWeight += mstGraph[i][j];
			}
		}

		return mstWeight / 2;
	}
}
