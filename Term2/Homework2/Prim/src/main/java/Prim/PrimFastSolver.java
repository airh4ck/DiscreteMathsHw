package Prim;

import utils.Edge;
import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class PrimFastSolver extends PrimSolver {
	private static final int INF = 1_000_000_000 + 23;
	private static final int START_VERTICE = 0;

	public PrimFastSolver(ArrayList<Edge>[] graph) {
		super(graph);
	}

	@Override
	public ArrayList<Edge>[] getMST() {
		int[] minDistance = new int[verticesCount];
		Edge[] parent = new Edge[verticesCount];
		PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>();

		Arrays.fill(parent, new Edge(-1, -1, -1));
		Arrays.fill(minDistance, INF);
		minDistance[START_VERTICE] = 0;
		priorityQueue.add(new Pair<Integer, Integer>(START_VERTICE, 0));

		var mstGraph = createEmptyGraph(verticesCount);

		for (int i = 0; i < verticesCount; i++) {
			if (priorityQueue.isEmpty())
				throw new IllegalArgumentException("Given graph isn't connected");

			var currentVertice = priorityQueue.poll().second();
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

			minDistance[currentVertice] = 0;
			for (var edge : graph[currentVertice]) {
				if (edge.getWeight() < minDistance[edge.getTo()]) {
					priorityQueue.remove(new Pair<>(minDistance[edge.getTo()], edge.getTo()));
					minDistance[edge.getTo()] = edge.getWeight();
					parent[edge.getTo()] = edge;
					priorityQueue.add(new Pair<>(minDistance[edge.getTo()], edge.getTo()));
				}
			}
		}

		return mstGraph;
	}
}
