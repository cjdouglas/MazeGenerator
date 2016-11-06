package maze;

import java.util.ArrayList;
import java.util.Random;

import generator.WeightedQuickUnion;
import generator.Wall;

public class Maze {
	WeightedQuickUnion wqu;
	int size;
	
	ArrayList<Wall> walls;
	Random gen;
	
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
			
			int offset = i * size + 1;			
			for (int j = 0; j < size; j++) {
				
				if (i != size - 1) {
					walls.add(new Wall(offset + j, offset + j + size));
					if (j != size - 1) {
						walls.add(new Wall(offset + j, offset + j + 1));
					}
				} else {
					walls.add(new Wall(offset + j, offset + j + size));
				}
			}
		}
	}
	
	/**
	 * Generates a maze based on the given size
	 */
	public void generate() {
		while (wqu.size() > 1) {			
			int index = gen.nextInt(walls.size());
			Wall wall = walls.get(index); // Wall which we will remove and union
			walls.remove(index);
			
			wqu.union(wall.x(), wall.y());
		}
	}
}
