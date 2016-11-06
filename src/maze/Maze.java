package maze;

import generator.WeightedQuickUnion;

public class Maze {
	WeightedQuickUnion wqu;
	int size;
	
	/**
	 * Initializer for a Maze with a given size
	 * @param N The size for the Maze, N x N
	 */
	public Maze(int N) {
		wqu = new WeightedQuickUnion(N);
		size = N;
	}
	
	/**
	 * Generates a maze based on the given size
	 */
	public void generate() {
		// to do
	}
}
