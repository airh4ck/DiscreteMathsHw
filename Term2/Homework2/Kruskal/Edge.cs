namespace Kruskal;

public class Edge 
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

	public int CompareTo(Edge other) 
	{
		if (this.Weight == other.Weight)
			return 0;
		else if (this.Weight > other.Weight)
			return 1;
		return -1;
	}
}