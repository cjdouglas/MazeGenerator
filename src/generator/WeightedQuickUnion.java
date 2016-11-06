package generator;

public class WeightedQuickUnion {
	private int[] id;
	private int[] size;
	private int uSize;
	
	/**
	 * Initializer for WeightedQuickUnion with a given number of elements
	 * @param N The number of nodes to associate with this WeightedQuickUnion
	 */
	public WeightedQuickUnion(int N) {
		id = new int[N];
		size = new int[N];
		uSize = N;

		// Initialize each square with its own unique id and size of 1
		
		for (int i = 0; i < N; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}
	
	/**
	 * Returns the index of the root for the given node index while
	 * redirecting all child node's to point to the parent for faster access
	 * @param i The index of the node to find the root for
	 * @return The index of the root for the given node index
	 */
	private int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		
		return i;
	}
	
	public int find(int index) {
		return root(index);
	}
	
	/**
	 * Combines the connect components for the given nodes by attaching
	 * the smaller tree as a child of the larger tree's parent
	 * @param p Index of the first node
	 * @param q Index of the second node
	 */
	public void union(int firstNode, int secondNode) {
		int i = root(firstNode);
		int j = root(secondNode);
		
		if (i == j) {
			return; // Return if they are already in the same connected component
		}
		
		if (size[i] < size[j]) {
			id[i] = id[j];
			size[j] += size[i];
		} else {
			id[j] = id[i];
			size[i] += size[j];
		}
		uSize--; 
	}
	
	/**
	 * Returns true/false whether two nodes are in the same connected component
	 * @param p Index of the first node
	 * @param q Index of the second node
	 * @return True if the two nodes are in the same connected component, false otherwise
	 */
	public boolean connected(int firstNode, int secondNode) {
		return id[firstNode] == id[secondNode];
	}
	
	/**
	 * Returns the size of this WeightedQuickUnion
	 * @return The Size of this WeightedQuickUnion
	 */
	public int size() {
		return uSize;
	}
}
