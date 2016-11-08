package generator;

import java.util.ArrayList;
import java.util.Random;

import maze.Maze;
import maze.Wall;

public class MazeGenerator {
	private Maze maze;
	private WeightedQuickUnion wqu;
	
	private Random gen;
	
	public MazeGenerator(int N) {
		maze = new Maze(N);
		wqu = new WeightedQuickUnion(N * N);
		
		gen = new Random();
		
		generate();
	}
	
	/**
	 * Generates an N by N maze based off the constructor
	 */
	public void generate() {
		ArrayList<Wall> walls = maze.getWalls();
		
		while (wqu.size() > 1) {
			int index = gen.nextInt(walls.size());
			Wall w = walls.get(index);
			
			int u = w.x();
			int v = w.y();
			
			if (wqu.find(u) != wqu.find(v)) {
				wqu.union(u, v);
				maze.removeWall(index);
			}
		}
	}
	
	/**
	 * Rebuilds an N x N maze
	 * @param N Dimension of the maze to rebuild
	 */
	public void rebuild(int N) {
		maze.rebuild(N);
		wqu = new WeightedQuickUnion(N * N);
		generate();
	}
	
	/**
	 * Returns the walls of a minimum spanning tree after
	 * the maze has been generated from the generate() method
	 * @return The walls of an N x N maze represented
	 * by a minimum spanning tree
	 */
	public ArrayList<Wall> getMSTWalls() {
		return maze.getWalls();
	}
}
