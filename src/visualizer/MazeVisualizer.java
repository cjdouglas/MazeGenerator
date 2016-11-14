package visualizer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

import generator.MazeGenerator;
import maze.Maze;

public class MazeVisualizer {
	
	private Maze maze;
	private boolean[][] cells;
	
	private int size;
	private int gridSize;
	
	public MazeVisualizer(Maze maze) {
		this.maze = maze;
		size = maze.getSize();
		gridSize = 2 * size + 1;
		
		cells = new boolean[gridSize][gridSize];
		
		setup();
	}
	
	private void setup() {
		
		// Initialize outer wall to be an obstacle
		
		for (int i = 0; i < gridSize; i++) {
			cells[0][i] = true;
			cells[gridSize - 1][i] = true;
			
			cells[i][0] = true;
			cells[gridSize - 1][i] = true;
		}
		
		
	}
}
