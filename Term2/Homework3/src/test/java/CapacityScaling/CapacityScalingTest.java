package CapacityScaling;

import EdmondsKarp.EdmondsKarp;
import FordFulkerson.FordFulkerson;
import org.junit.jupiter.api.Test;
import utils.Edge;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CapacityScalingTest {
	private ArrayList<Edge>[] edgeListToAdjacencyList(ArrayList<Edge> edgeList) {
		int vertices = 0;

		for (var edge : edgeList)
			vertices = Math.max(vertices, Math.max(edge.to(), edge.from()) + 1);

		ArrayList<Edge>[] adjacencyList = new ArrayList[vertices];
		for (int i = 0; i < vertices; i++)
			adjacencyList[i] = new ArrayList<>();

		for (var edge : edgeList) {
			adjacencyList[edge.from()].add(edge);
		}

		return adjacencyList;
	}

	@Test
	void findMaxFlow() {
		ArrayList<Edge>[] graph = (ArrayList<Edge>[]) new ArrayList[] {
				new ArrayList(Arrays.asList(new Edge(0, 1, 3, 0), new Edge(0, 2, 5, 0))),
				new ArrayList(Arrays.asList(new Edge(1, 3, 5, 0))),
				new ArrayList(Arrays.asList(new Edge(2, 1, 4, 0), new Edge(2, 4, 2, 0))),
				new ArrayList(Arrays.asList(new Edge(3, 4, 6, 0), new Edge(3, 5, 5, 0))),
				new ArrayList(Arrays.asList(new Edge(4, 5, 7, 0))),
				new ArrayList<>()
		};

		CapacityScaling solver = new CapacityScaling(graph);
		assertEquals(7, solver.findMaxFlow(0, 5));
	}

	@Test
	void findMaxFlowBigGraph() {
		ArrayList<Edge> edges = new ArrayList<>() {
			{
				add(new Edge(0, 1, 6, 0));
				add(new Edge(1, 5, 4, 0));
				add(new Edge(5, 9, 5, 0));
				add(new Edge(9, 13, 6, 0));
				add(new Edge(0, 2, 6, 0));
				add(new Edge(1, 2, 3, 0));
				add(new Edge(5, 2, 9, 0));
				add(new Edge(2, 6, 4, 0));
				add(new Edge(6, 5, 8, 0));
				add(new Edge(9, 6, 10, 0));
				add(new Edge(6, 10, 5, 0));
				add(new Edge(10, 9, 8, 0));
				add(new Edge(10, 13, 9, 0));
				add(new Edge(2, 3, 4, 0));
				add(new Edge(7, 6, 8, 0));
				add(new Edge(11, 10, 8, 0));
				add(new Edge(6, 3, 10, 0));
				add(new Edge(10, 7, 12, 0));
				add(new Edge(0, 3, 8, 0));
				add(new Edge(3, 7, 5, 0));
				add(new Edge(7, 11, 5, 0));
				add(new Edge(11, 13, 7, 0));
				add(new Edge(0, 4, 9, 0));
				add(new Edge(3, 4, 3, 0));
				add(new Edge(4, 8, 6, 0));
				add(new Edge(3, 8, 10, 0));
				add(new Edge(8, 7, 7, 0));
				add(new Edge(8, 12, 7, 0));
				add(new Edge(7, 12, 12, 0));
				add(new Edge(12, 11, 7, 0));
				add(new Edge(12, 13, 6, 0));
			}};

		CapacityScaling solver = new CapacityScaling(edgeListToAdjacencyList(edges));
		assertEquals(26, solver.findMaxFlow(0, 13));
	}
}