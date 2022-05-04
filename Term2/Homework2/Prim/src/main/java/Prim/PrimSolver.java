package Prim;

import utils.Edge;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimSolver {
	private static final int INF = 1_000_000_000 + 23;
	private static final int START_VERTICE = 0;

	protected int verticesCount;
	protected ArrayList<Edge>[] graph;

	public PrimSolver(ArrayList<Edge>[] graph) {
		this.graph = graph;
		this.verticesCount = graph.length;
	}

	protected ArrayList<Edge>[] createEmptyGraph(int vertices) {
		ArrayList<Edge>[] result = new ArrayList[vertices];
		for (int i = 0; i < vertices; i++)
			result[i] = new ArrayList<>();
		return result;
	}

	public ArrayList<Edge>[] getMST() {
		boolean used[] = new boolean[verticesCount];
		int minDistance[] = new int[verticesCount];
		Edge parent[] = new Edge[verticesCount];
		Arrays.fill(minDistance, INF);
		Arrays.fill(parent, new Edge(-1, -1, -1));
		minDistance[START_VERTICE] = 0;

		var mstGraph = createEmptyGraph(verticesCount);

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
			if (parent[currentVertice].getFrom() != -1) {
				mstGraph[parent[currentVertice].getFrom()].add(parent[currentVertice]);
				mstGraph[currentVertice].add(
						new Edge(
								parent[currentVertice].getTo(),
								parent[currentVertice].getFrom(),
								parent[currentVertice].getWeight()
						)
				);
			}
			for (var edge : graph[currentVertice]) {
				if (edge.getWeight() < minDistance[edge.getTo()]) {
					minDistance[edge.getTo()] = edge.getWeight();
					parent[edge.getTo()] = edge;
				}
			}
		}

		return mstGraph;
	}

	public int getMSTWeight() {
		var mstGraph = getMST();
		int mstWeight = 0;
		for (int i = 0; i < verticesCount; i++) {
			for (var edge : mstGraph[i])
				mstWeight += edge.getWeight();
		}

		return mstWeight / 2;
	}
}
