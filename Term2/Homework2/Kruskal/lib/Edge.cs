namespace Kruskal;

public class Edge : IComparable
{
	internal int From {get; init;}

	internal int To {get; init;}

	internal int Weight {get;}

	public Edge(int from, int to, int weight) 
	{
		this.From = from;
		this.To = to;
		this.Weight = weight;
	}

	public int CompareTo(object? obj) 
	{
		if (obj is not Edge || obj == null)
			return 0;
		
		Edge other = obj as Edge;
		if (this.Weight == other.Weight)
			return 0;
		else if (this.Weight > other.Weight)
			return 1;
		return -1;
	}
}