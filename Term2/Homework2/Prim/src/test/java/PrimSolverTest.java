import Prim.PrimSolver;
import Prim.PrimFastSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimSolverTest {
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

		PrimSolver primSolver = new PrimSolver(graph);
		var mstGraph = primSolver.getMST();
		int[][] expected = new int[][] {
				{-1, 1, -1, -1, -1, -1, -1},
				{1, -1, 7, -1, -1, -1, -1},
				{-1, 7, -1, 4, -1, -1, -1},
				{-1, -1, 4, -1, 3, -1, -1},
				{-1, -1, -1, 3, -1, 2, -1},
				{-1, -1, -1, -1, 2, -1, 6},
				{-1, -1, -1, -1, -1, 6, -1}
		};

		for (int row = 0; row < graph.length; row++) {
			for (int col = 0; col < graph.length; col++) {
				assertEquals(expected[row][col], mstGraph[row][col]);
			}
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

		PrimSolver primSolver = new PrimSolver(graph);
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

		PrimSolver primSolver = new PrimFastSolver(graph);
		var mstGraph = primSolver.getMST();
		int[][] expected = new int[][] {
				{-1, 1, -1, -1, -1, -1, -1},
				{1, -1, 7, -1, -1, -1, -1},
				{-1, 7, -1, 4, -1, -1, -1},
				{-1, -1, 4, -1, 3, -1, -1},
				{-1, -1, -1, 3, -1, 2, -1},
				{-1, -1, -1, -1, 2, -1, 6},
				{-1, -1, -1, -1, -1, 6, -1}
		};

		for (int row = 0; row < graph.length; row++) {
			for (int col = 0; col < graph.length; col++) {
				assertEquals(expected[row][col], mstGraph[row][col]);
			}
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

		PrimSolver primSolver = new PrimFastSolver(graph);
		assertEquals(23, primSolver.getMSTWeight());
	}
}