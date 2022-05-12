package Kuhn;

import java.util.ArrayList;
import java.util.Arrays;

public class Kuhn {
	private ArrayList<Integer>[] graph;
	private int verticesCount;
	private int[] used;
	private int[] matching;
	private int timer;

	public Kuhn(ArrayList<Integer>[] graph) {
		this.graph = graph;
		this.verticesCount = graph.length;
		this.used = new int[verticesCount];
		this.matching = new int[verticesCount];
		this.timer = 1;
	}

	private boolean dfs(int v) {
		if (used[v] == timer)
			return false;

		used[v] = timer;
		for (var to : graph[v]) {
			if (matching[to] == -1 || dfs(matching[to])) {
				matching[to] = v;
				return true;
			}
		}

		return false;
	}

	public ArrayList<Integer> getMatching() {
		Arrays.fill(matching, -1);
		boolean foundMatching = false;
		for (int i = 0; i < verticesCount; i++, timer++)
			foundMatching |= dfs(i);

		if (!foundMatching)
			return null;
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < verticesCount; i++) {
			result.add(matching[i]);
		}

		return result;
	}
}
