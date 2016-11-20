package visualizer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

import generator.MazeGenerator;
import maze.Maze;
import maze.Wall;

public class MazeVisualizer {
	
	private Maze maze;
	private boolean[][] cells;
	
	private int size;
	private int gridSize;
	
	private JFrame mainFrame;
	private MazePanel mazeDisplay;
	
	public MazeVisualizer(Maze maze) {
		this.maze = maze;
		size = maze.getSize();
		gridSize = 2 * size + 1;
		
		cells = new boolean[gridSize][gridSize];
		buildMaze();
		
		mainFrame = new JFrame();
		mazeDisplay = new MazePanel(size, size, cells);
		
		
		initializeUI();
	}
		
	/**
	 * Initializes the main components of the user interface
	 */
	private void initializeUI() {
		mainFrame.setSize(750, 750);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(mazeDisplay);
	}
	
	/**
	 * Initializes the outer border of the grid to be obstacle cells
	 */
	private void buildMaze() {
		
		// Initialize outer wall to be an obstacle
		
		for (int i = 0; i < gridSize; i++) {
			cells[0][i] = true;
			cells[gridSize - 1][i] = true;
			
			cells[i][0] = true;
			cells[i][gridSize - 1] = true;
		}
			
		for (Wall w : maze.getWalls()) {
			
			// Fill in edge here
			int xRow = (w.x()) / size;
			int xCol = (w.x()) % size;
			int xActualRow = xRow * 2 + 1;
			int xActualCol = xCol * 2 + 1;
			
			int yRow = (w.y()) / size;
			int yCol = (w.y()) % size;
			int yActualRow = yRow * 2 + 1;
			int yActualCol = yCol * 2 + 1;
			
			int rowOffset = yActualRow - xActualRow;
			int colOffset = yActualCol - xActualCol;
			
			if (rowOffset != 0) {
				cells[xActualRow + 1][xActualCol - 1] = true;
				cells[xActualRow + 1][xActualCol] = true;
				cells[xActualRow + 1][xActualCol + 1] = true;
			} else if (colOffset != 0) {
				cells[xActualRow - 1][xActualCol + 1] = true;
				cells[xActualRow][xActualCol + 1] = true;
				cells[xActualRow + 1][xActualCol + 1] = true;
			}
		}
		
		
		// Entrance and exit to remain open
		cells[0][1] = false;
		cells[gridSize - 1][gridSize - 2] = false;
	}
	
	/**
	 * Solves the maze and sends the path to the visualizer
	 */
	private void solve() {
		// TODO
	}
	
	/**
	 * Starts the simulation
	 */
	public void run() {
		mainFrame.setVisible(true);
	}
}
