package DSU;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DSUTest {
	private static final int N = 10;
	private DSU dsu;

	@BeforeEach
	void setUp() {
		dsu = new DSU(N);
	}

	@Test
	void getInitialParentExpectDisjointSets() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j)
					assertNotEquals(dsu.getParent(i), dsu.getParent(j));
			}
		}
	}

	@Test
	void getInitialParent() {
		for (int i = 0; i < N; i++) {
			assertEquals(i, dsu.getParent(i));
		}
	}

	@Test
	void getParentAfterChangeExpectOneSet() {
		for (int i = 1; i < N; i++) {
			dsu.joinSets(i - 1, i);
		}

		for (int i = 0; i < N; i++) {
			assertEquals(dsu.getParent(0), dsu.getParent(i));
		}
	}

	@Test
	void joinDisjointSetsExpectFalse() {
		assertFalse(dsu.joinSets(0, 1));
	}

	@Test
	void joinJointSetsExpectTrue() {
		assertTrue(dsu.joinSets(0, 0));
	}

	@Test
	void joinSets() {
	}
}