package testing;

import org.junit.*;

import generator.WeightedQuickUnion;

public class UnionTest { 
	
	@Test
	public void unionTest() {
		WeightedQuickUnion union = new WeightedQuickUnion(9);
		union.union(1, 8);
		union.union(2, 8);
		union.union(3, 2);
		
		Assert.assertEquals(true, union.connected(1, 8));
		Assert.assertEquals(true, union.connected(2, 8));
		Assert.assertEquals(true, union.connected(3, 2));
		Assert.assertEquals(false, union.connected(5, 8));
		Assert.assertEquals(6, union.size());
		
		union.union(5, 8);
		union.union(6, 7);
		
		Assert.assertEquals(true, union.connected(5, 8));
		Assert.assertEquals(true, union.connected(6, 7));
		Assert.assertEquals(false, union.connected(6, 8));
		Assert.assertEquals(false, union.connected(6, 1));
		Assert.assertEquals(4, union.size());
		
		union.union(5, 6);
		union.union(4, 5);
		union.union(0, 8);
		
		Assert.assertEquals(1, union.size());
	}
	
}