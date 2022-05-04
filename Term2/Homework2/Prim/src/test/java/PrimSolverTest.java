import Prim.PrimSolver;
import Prim.PrimFastSolver;
import org.junit.jupiter.api.Test;
import utils.Edge;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrimSolverTest {

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

		PrimSolver primSolver = new PrimSolver(adjacencyMatrixToAdjacencyList(graph));
		var mstGraph = primSolver.getMST();
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

		PrimSolver primSolver = new PrimSolver(adjacencyMatrixToAdjacencyList(graph));
		assertEquals(23, primSolver.getMSTWeight());
	}

	@Test
	void getMSTFastPrim() {
		int[][] graph = new int[][]{
				{-1, 1, -1, -1, 9, -1, -1},
				{1, -1, 7, -1, -1, -1, 8},
				{-1, 7, -1, 4, -1, -1, 10},
				{-1, -1, 4, -1, 3, 5, -1},
				{9, -1, -1, 3, -1, 2, -1},
				{-1, -1, -1, 5, 2, -1, 6},
				{-1, 8, 10, -1, -1, 6, -1}
		};

		PrimSolver primSolver = new PrimFastSolver(adjacencyMatrixToAdjacencyList(graph));
		var mstGraph = primSolver.getMST();
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
			assertEquals(expected[i], mstGraph[i]);
		}
	}

	@Test
	void getMSTWeightFastPrim() {
		int[][] graph = new int[][]{
				{-1, 1, -1, -1, 9, -1, -1},
				{1, -1, 7, -1, -1, -1, 8},
				{-1, 7, -1, 4, -1, -1, 10},
				{-1, -1, 4, -1, 3, 5, -1},
				{9, -1, -1, 3, -1, 2, -1},
				{-1, -1, -1, 5, 2, -1, 6},
				{-1, 8, 10, -1, -1, 6, -1}
		};

		PrimSolver primSolver = new PrimFastSolver(adjacencyMatrixToAdjacencyList(graph));
		assertEquals(23, primSolver.getMSTWeight());
	}
}