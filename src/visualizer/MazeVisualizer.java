package visualizer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import generator.MazeGenerator;
import maze.Maze;
import maze.Wall;

public class MazeVisualizer implements KeyListener {
	
	private Maze maze;
	private boolean[][] cells;
	
	private int size;
	private int gridSize;
	
	private JFrame mainFrame;
	private MazePanel mazeDisplay;
	private Point userLoc;
	
	public MazeVisualizer(Maze maze) {
		this.maze = maze;
		size = maze.getSize();
		gridSize = 2 * size + 1;
		
		cells = new boolean[gridSize][gridSize];
		buildMaze();
		
		mainFrame = new JFrame();
		mazeDisplay = new MazePanel(size, size, cells);	
		userLoc = new Point(0, 1);
		
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
		mainFrame.addKeyListener(this);
		mazeDisplay.setUserLoc(userLoc);
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
	
	public boolean isValid(int x, int y) {
		return (x > 0 && x < gridSize && y > 0 && y < gridSize && !cells[x][y]);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 's') {
			if (isValid(userLoc.x + 1, userLoc.y)) {
				System.out.println("valid");
				userLoc.setLocation(userLoc.x + 1, userLoc.y);
				mazeDisplay.setUserLoc(userLoc);
			}
		} else if (e.getKeyChar() == 'w') {
			if (isValid(userLoc.x - 1, userLoc.y)) {
				System.out.println("valid");
				userLoc.setLocation(userLoc.x - 1, userLoc.y);
				mazeDisplay.setUserLoc(userLoc);
			}
		} else if (e.getKeyChar() == 'a') {
			if (isValid(userLoc.x, userLoc.y - 1)) {
				System.out.println("valid");
				userLoc.setLocation(userLoc.x, userLoc.y - 1);
				mazeDisplay.setUserLoc(userLoc);
			}	
		} else if (e.getKeyChar() == 'd') {
			if (isValid(userLoc.x, userLoc.y + 1)) {
				System.out.println("valid");
				userLoc.setLocation(userLoc.x, userLoc.y + 1);
				mazeDisplay.setUserLoc(userLoc);
			}			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
