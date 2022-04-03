using NUnit.Framework;

namespace KruskalLibraryTest;

public class EdgeTest
{
    private Kruskal.Edge firstEdge;
    private Kruskal.Edge secondEdge;

    [SetUp]
    public void Setup()
    {
        firstEdge = new Kruskal.Edge(1, 5, 1200);
        secondEdge = new Kruskal.Edge(2, 3, 124);
    }

    [Test]
    public void TestComparatorExpectOne() 
    {
        Assert.AreEqual(1, firstEdge.CompareTo(secondEdge));
    }

    [Test]
    public void TestComparatorExpectZero() 
    {
        secondEdge = new Kruskal.Edge(2, 3, 1200);
        Assert.AreEqual(0, firstEdge.CompareTo(secondEdge));
    }

    [Test]
    public void TestComparatorExpectMinusOne() 
    {
        Assert.AreEqual(-1, secondEdge.CompareTo(firstEdge));
    }
}