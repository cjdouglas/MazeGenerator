package maze;

import java.util.ArrayList;
import java.util.Random;

import generator.WeightedQuickUnion;
import generator.Wall;

public class Maze {
	private WeightedQuickUnion wqu;
	private int size;
	
	private ArrayList<Wall> walls;
	private Random gen;
	
	/**
	 * Initializer for a Maze with a given size
	 * @param N The size for the Maze, N x N
	 */
	public Maze(int N) {
		wqu = new WeightedQuickUnion(N * N);
		size = N;
		
		walls = new ArrayList<>();
		gen = new Random();
		
		createWalls();
	}
	
	/**
	 * Builds all of the walls for this maze in order to be removed
	 * by the generating algorithm which will follow
	 */
	private void createWalls() {
		for (int i = 0; i < size; i++) {
			
			int offset = i * size;			
			for (int j = 0; j < size; j++) {
				
				if (i != size - 1) {
					walls.add(new Wall(offset + j, offset + j + size));
					if (j != size - 1) {
						walls.add(new Wall(offset + j, offset + j + 1));
					}
				} else {					
					if (j != size - 1) {
						walls.add(new Wall(offset + j, offset + j + 1));
					}
				}
			}
		}
	}
	
	/**
	 * Generates a maze based on the given size
	 */
	public void generate() {
		System.out.println(walls.size());
		
		ArrayList<Wall> temp = getWalls();
		
		while (wqu.size() > 1) {			
			int index = gen.nextInt(temp.size());
			Wall wall = temp.get(index); // Wall which we will remove and union
			
			int u = wqu.find(wall.x());
			int v = wqu.find(wall.y());
			
			if (wqu.find(u) != wqu.find(v)) {
				wqu.union(u, v);
				walls.remove(index);
			}
		}
	}
	
	/**
	 * Returns the list of Walls
	 * @return The list of Walls
	 */
	public ArrayList<Wall> getWalls() {
		return walls;
	}
}
