using NUnit.Framework;

namespace KruskalLibraryTest;

public class DSUTest
{
	private const int n = 10;
	private Kruskal.DSU dsu;
    [SetUp]
    public void Setup()
    {
		dsu = new Kruskal.DSU(n);
	}

	[Test]
	public void TestGetInitialParentExpectFalse() 
	{
		bool flag = false;
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++)
			{
				if (i != j)
				{
					Assert.AreNotEqual(dsu.GetParent(i), dsu.GetParent(j));
				}
			}
		}
	}

	[Test]
	public void TestGetInitialParentExpectTrue()
	{
		for (int i = 0; i < n; i++) {
			Assert.AreEqual(i, dsu.GetParent(i));
		}
	}

	[Test]
	public void TestGetParentAfterChangeExpectTrue() 
	{
		for (int i = 1; i < n; i++) 
		{
			dsu.JoinSets(i - 1, i);
		}

		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++)
			{
				Assert.AreEqual(dsu.GetParent(i), dsu.GetParent(j));
			}
		}
	}

	[Test]
	public void TestJoinTestsExpectFalse() 
	{
		Assert.IsFalse(dsu.JoinSets(0, 1));
	}

	[Test]
	public void TestJoinTestsExpectTrue() 
	{
		Assert.IsTrue(dsu.JoinSets(0, 0));
	}
}