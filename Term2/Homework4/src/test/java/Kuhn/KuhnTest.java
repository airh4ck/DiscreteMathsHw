package Kuhn;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KuhnTest {

	@Test
	void getMatching() {
		ArrayList<Integer>[] graph = new ArrayList[6];
		for (int i = 0; i < 6; i++)
			graph[i] = new ArrayList<>();

		graph[0].addAll(Arrays.asList(1, 3));
		graph[1].addAll(Arrays.asList(0, 4));
		graph[2].add(5);
		graph[3].add(0);
		graph[4].add(1);
		graph[5].add(2);

		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3, 4, 5, 0, 1, 2));
		Kuhn kuhn = new Kuhn(graph);
		assertEquals(expected, kuhn.getMatching());
	}
}