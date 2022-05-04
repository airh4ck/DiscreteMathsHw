package utils;

public class Edge implements Comparable {
	protected int from;
	protected int to;
	protected int weight;

	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Edge || o == null) {
			Edge other = (Edge) o;
			return Integer.compare(this.weight, other.weight);
		}

		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Edge other) {
			return this.from == other.from && this.to == other.to && this.weight == other.weight;
		}

		return false;
	}
}