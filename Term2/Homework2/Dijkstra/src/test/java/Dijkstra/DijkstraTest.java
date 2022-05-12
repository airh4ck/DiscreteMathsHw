package Dijkstra;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

	@Test
	void getShortestDistances() {
		Dijkstra solver = new Dijkstra(new int[][]{
				{-1, 2, -1, 1, -1},
				{2, -1, 4, -1, 5},
				{-1, 4, -1, 3, 1},
				{1, -1, 3, -1, -1},
				{-1, 5, 1, -1, -1}
		});

		var expected = new int[] {0, 2, 4, 1, 5};
		var actual = solver.getShortestDistances(0);

		for (int i = 0; i < expected.length; i++)
			assertEquals(expected[i], actual[i]);
	}

	@Test
	void getShortestDistancesNotConnectedGraph() {
		int INF = 1_000_000_000 + 23;
		Dijkstra solver = new Dijkstra(new int[][]{
				{-1, 2, -1, 1, -1, -1, -1, -1},
				{2, -1, 4, -1, 5, -1, -1, -1},
				{-1, 4, -1, 3, 1, -1, -1, -1},
				{1, -1, 3, -1, -1, -1, -1, -1},
				{-1, 5, 1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, 1, -1},
				{-1, -1, -1, -1, -1, 1, -1, 2},
				{-1, -1, -1, -1, -1, -1, 2, -1}
		});

		var expected = new int[] {0, 2, 4, 1, 5, INF, INF, INF};
		var actual = solver.getShortestDistances(0);

		for (int i = 0; i < expected.length; i++)
			assertEquals(expected[i], actual[i]);
	}

	@Test
	void getShortestPath() {
		Dijkstra solver = new Dijkstra(new int[][]{
				{-1, 2, -1, 1, -1, -1, -1, -1},
				{2, -1, 4, -1, 5, -1, -1, -1},
				{-1, 4, -1, 3, 1, -1, -1, -1},
				{1, -1, 3, -1, -1, -1, -1, -1},
				{-1, 5, 1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, 1, -1},
				{-1, -1, -1, -1, -1, 1, -1, 2},
				{-1, -1, -1, -1, -1, -1, 2, -1}
		});

		assertEquals(
				Arrays.asList(0, 3, 2, 4),
				solver.getShortestPath(0, 4)
		);
	}

	@Test
	void getShortestPathVerticesFromDifferentConnectedComponents() {
		Dijkstra solver = new Dijkstra(new int[][]{
				{-1, 2, -1, 1, -1, -1, -1, -1},
				{2, -1, 4, -1, 5, -1, -1, -1},
				{-1, 4, -1, 3, 1, -1, -1, -1},
				{1, -1, 3, -1, -1, -1, -1, -1},
				{-1, 5, 1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, 1, -1},
				{-1, -1, -1, -1, -1, 1, -1, 2},
				{-1, -1, -1, -1, -1, -1, 2, -1}
		});

		assertThrows(IllegalArgumentException.class, () -> solver.getShortestPath(0, 6));
	}
}