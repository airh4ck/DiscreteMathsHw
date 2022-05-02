package EdmondsKarp;

import FordFulkerson.FordFulkerson;
import org.junit.jupiter.api.Test;
import utils.Edge;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EdmondsKarpTest {

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

		EdmondsKarp solver = new EdmondsKarp(graph);
		assertEquals(7, solver.findMaxFlow(0, 5));
	}
}