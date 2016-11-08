package maze;

import java.util.ArrayList;

public class Maze {
	private int size;	
	private ArrayList<Wall> walls;
	
	/**
	 * Initializer for a Maze with a given size
	 * @param N The size for the Maze, N x N
	 */
	public Maze(int N) {
		size = N;		
		walls = new ArrayList<>();
		
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
	 * Removes a wall from the given index
	 * @param index
	 */
	public void removeWall(int index) {
		walls.remove(index);
	}
	
	/**
	 * Rebuilds this maze to be N x N
	 * @param N Dimension of the maze to rebuild
	 */
	public void rebuild(int N) {
		walls.clear();
		size = N;
		createWalls();
	}
		
	/**
	 * Returns the list of Walls
	 * @return The list of Walls
	 */
	public ArrayList<Wall> getWalls() {
		return walls;
	}
}
