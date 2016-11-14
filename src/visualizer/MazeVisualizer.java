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
	
	private JFrame mainFrame;
	private JPanel mazeDisplay;
	
	public MazeVisualizer(Maze maze) {
		this.maze = maze;
		size = maze.getSize();
		gridSize = 2 * size + 1;
		
		cells = new boolean[gridSize][gridSize];
		
		mainFrame = new JFrame();
		mazeDisplay = new JPanel();
		
		initializeUI();
		initializeGrid();
	}
		
	/**
	 * Initializes the main components of the user interface
	 */
	private void initializeUI() {
		mainFrame.setSize(750,  750);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * Initializes the outer border of the grid to be obstacle cells
	 */
	private void initializeGrid() {
		
		// Initialize outer wall to be an obstacle
		
		for (int i = 0; i < gridSize; i++) {
			cells[0][i] = true;
			cells[gridSize - 1][i] = true;
			
			cells[i][0] = true;
			cells[gridSize - 1][i] = true;
		}
	}
	
	/**
	 * Starts the simulation
	 */
	public void run() {
		mainFrame.setVisible(true);
	}
}
