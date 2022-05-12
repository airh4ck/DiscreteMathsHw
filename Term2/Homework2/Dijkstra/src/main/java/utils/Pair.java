package utils;

public class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K, V>> {
	private K first;
	private V second;

	public Pair(K first, V second) {
		this.first = first;
		this.second = second;
	}

	public K first() {
		return this.first;
	}

	public V second() {
		return this.second;
	}

	@Override
	public int compareTo(Pair<K, V> other) {
		if (!this.first.equals(other.first))
			return this.first.compareTo(other.first);
		return this.second.compareTo(other.second);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Pair<? extends Comparable<K>, ? extends Comparable<V>> other) {
			return this.first.equals(other.first) && this.second.equals(other.second);
		}

		return false;
	}
}