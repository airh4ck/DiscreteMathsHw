package Kruskal;

import org.junit.jupiter.api.Test;
import utils.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class KruskalTest {
	private static final String RESOURCES_PATH = "src/test/resources/";

	private ArrayList<Edge>[] adjacencyMatrixToAdjacencyList(int[][] graph) {
		ArrayList<Edge>[] result = new ArrayList[graph.length];

		for (int i = 0; i < graph.length; i++) {
			result[i] = new ArrayList<>();
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] != -1)
					result[i].add(new Edge(i, j, graph[i][j]));
			}
		}

		return result;
	}

	private ArrayList<Edge>[] readGraphFromFile(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(RESOURCES_PATH + file));
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		ArrayList<Edge>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int w = scanner.nextInt();

			var edgeToAdd = new Edge(u, v, w);
			if (!graph[u].contains(edgeToAdd))
				graph[u].add(edgeToAdd);
			if (!graph[v].contains(edgeToAdd.getReverseEdge()))
				graph[v].add(edgeToAdd.getReverseEdge());
		}

		return graph;
	}

	@Test
	void getMST() {
		int[][] graph = new int[][]{
				{-1, 1, -1, -1, 9, -1, -1},
				{1, -1, 7, -1, -1, -1, 8},
				{-1, 7, -1, 4, -1, -1, 10},
				{-1, -1, 4, -1, 3, 5, -1},
				{9, -1, -1, 3, -1, 2, -1},
				{-1, -1, -1, 5, 2, -1, 6},
				{-1, 8, 10, -1, -1, 6, -1}
		};

		Kruskal kruskalSolver = new Kruskal(adjacencyMatrixToAdjacencyList(graph));
		var mstGraph = kruskalSolver.getMST();
		var expected = adjacencyMatrixToAdjacencyList(
				new int[][] {
						{-1, 1, -1, -1, -1, -1, -1},
						{1, -1, 7, -1, -1, -1, -1},
						{-1, 7, -1, 4, -1, -1, -1},
						{-1, -1, 4, -1, 3, -1, -1},
						{-1, -1, -1, 3, -1, 2, -1},
						{-1, -1, -1, -1, 2, -1, 6},
						{-1, -1, -1, -1, -1, 6, -1}
				}
		);

		for (int i = 0; i < 7; i++) {
			mstGraph[i].sort(
					(a, b) -> {
						if (a.getFrom() != b.getFrom())
							return Integer.compare(a.getFrom(), b.getFrom());
						return Integer.compare(a.getTo(), b.getTo());
					}
			);
			assertEquals(expected[i], mstGraph[i]);
		}
	}

	@Test
	void getMSTWeight() {
		int[][] graph = new int[][]{
				{-1, 1, -1, -1, 9, -1, -1},
				{1, -1, 7, -1, -1, -1, 8},
				{-1, 7, -1, 4, -1, -1, 10},
				{-1, -1, 4, -1, 3, 5, -1},
				{9, -1, -1, 3, -1, 2, -1},
				{-1, -1, -1, 5, 2, -1, 6},
				{-1, 8, 10, -1, -1, 6, -1}
		};

		Kruskal kruskalSolver = new Kruskal(adjacencyMatrixToAdjacencyList(graph));
		assertEquals(23, kruskalSolver.getMSTWeight());
	}

	@Test
	void getMSTBigGraph() throws IOException {
		var graph = readGraphFromFile("bigGraph.txt");

		Kruskal kruskalSolver = new Kruskal(graph);

		var mstGraph = kruskalSolver.getMST();
		var expected = readGraphFromFile("bigGraphCorrectOutput.txt");
		for (int i = 0; i < graph.length; i++) {
			mstGraph[i].sort(
					(a, b) -> {
						if (a.getFrom() != b.getFrom())
							return Integer.compare(a.getFrom(), b.getFrom());
						return Integer.compare(a.getTo(), b.getTo());
					}
			);
			expected[i].sort(
					(a, b) -> {
						if (a.getFrom() != b.getFrom())
							return Integer.compare(a.getFrom(), b.getFrom());
						return Integer.compare(a.getTo(), b.getTo());
					}
			);
			assertEquals(expected[i], mstGraph[i]);
		}
	}

	@Test
	void getMSTWeightBigGraph() throws FileNotFoundException {
		var graph = readGraphFromFile("bigGraph.txt");
		Kruskal kruskalSolver = new Kruskal(graph);
		assertEquals(63498, kruskalSolver.getMSTWeight());
	}

	@Test
	void getMSTDisconnectedGraph() {
		int[][] graph = new int[][]{
				{-1, 2, -1, -1, -1},
				{2, -1, 3, -1, -1},
				{-1, 3, -1, -1, -1},
				{-1, -1, -1, -1, 5},
				{-1, -1, -1, 5, -1}
		};

		Kruskal kruskalSolver = new Kruskal(adjacencyMatrixToAdjacencyList(graph));
		assertThrows(IllegalArgumentException.class, kruskalSolver::getMST);
	}

	@Test
	void getMSTWeightDisconnectedGraph() {
		int[][] graph = new int[][]{
				{-1, 2, -1, -1, -1},
				{2, -1, 3, -1, -1},
				{-1, 3, -1, -1, -1},
				{-1, -1, -1, -1, 5},
				{-1, -1, -1, 5, -1}
		};

		Kruskal kruskalSolver = new Kruskal(adjacencyMatrixToAdjacencyList(graph));
		assertThrows(IllegalArgumentException.class, kruskalSolver::getMSTWeight);
	}
}