using NUnit.Framework;

namespace KruskalLibraryTest;

public class KruskalTest
{
	private const int n = 10;
	private int[,] graph;
	private Kruskal.Kruskal kruskal;
    [SetUp]
    public void Setup()
    {
		graph = new int[,] 
		{
			{-1, 1, -1, -1, 9, -1, -1},
			{1, -1, 7, -1, -1, -1, 8},
			{-1, 7, -1, 4, -1, -1, 10},
			{-1, -1, 4, -1, 3, 5, -1},
			{9, -1, -1, 3, -1, 2, -1},
			{-1, -1, -1, 5, 2, -1, 6},
			{-1, 8, 10, -1, -1, 6, -1}
		};
		kruskal = new Kruskal.Kruskal(graph);
	}

	[Test]
	public void KruskalWeightTest() 
	{
		Assert.AreEqual(23, kruskal.FindMSTWeight());
	}

	[Test]
	public void KruskalGraphTest() {
		Assert.AreEqual(new int[,]
		{
			{-1, 1, -1, -1, -1, -1, -1},
			{1, -1, 7, -1, -1, -1, -1},
			{-1, 7, -1, 4, -1, -1, -1},
			{-1, -1, 4, -1, 3, -1, -1},
			{-1, -1, -1, 3, -1, 2, -1},
			{-1, -1, -1, -1, 2, -1, 6},
			{-1, -1, -1, -1, -1, 6, -1}
		}, kruskal.FindMST());
	}
}