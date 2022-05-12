package Kuhn;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KuhnTest {

	@Test
	void getMatching() {
		ArrayList<Integer>[] graph = new ArrayList[3];
		for (int i = 0; i < 3; i++)
			graph[i] = new ArrayList<>();

		graph[0].addAll(Arrays.asList(0, 1));
		graph[1].add(2);
		graph[2].add(0);

		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2, 0, 1));
		Kuhn kuhn = new Kuhn(graph);
		assertEquals(expected, kuhn.getMatching());
	}
}