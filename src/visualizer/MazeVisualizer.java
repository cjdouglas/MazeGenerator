package visualizer;

import java.awt.Point;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.*;

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
		solve();
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
	 * Initializes the maze 
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
	 * Solves the maze using BFS
	 */
	private void solve() {
		Queue<Point> queue = new LinkedList<>();
		HashMap<Point, Boolean> visited = new HashMap<>(); // Map to store visited points
		HashMap<Point, Point> parents = new HashMap<>(); // Map to store parents of given nodes
		
		Point start = new Point(0, 1); // Set beginning node
		parents.put(start, null); // Add start node's parent as null
		queue.offer(start); // Offer start node to the queue
		
		while (!queue.isEmpty()) {
			Point current = queue.poll();
			visited.put(current, true);
			
			if (current.x == gridSize - 1 && current.y == gridSize - 2) {
				break;
			}
			
			// add all neighbors to queue and add this node as their parent
			Point leftNeighbor = new Point(current.x, current.y - 1);
			Point rightNeighbor = new Point(current.x, current.y + 1);
			Point topNeighbor = new Point(current.x - 1, current.y);
			Point bottomNeighbor = new Point(current.x + 1, current.y);
			
			if (isValid(leftNeighbor.x, leftNeighbor.y) && !cells[leftNeighbor.x][leftNeighbor.y] && !visited.containsKey(leftNeighbor)) {
				visited.put(leftNeighbor, true);
				parents.put(leftNeighbor, current);
				queue.offer(leftNeighbor);
			}
			
			if (isValid(rightNeighbor.x, rightNeighbor.y) && !cells[rightNeighbor.x][rightNeighbor.y] && !visited.containsKey(rightNeighbor)) {
				visited.put(rightNeighbor, true);
				parents.put(rightNeighbor, current);
				queue.offer(rightNeighbor);
			}
			
			if (isValid(topNeighbor.x, topNeighbor.y) && !cells[topNeighbor.x][topNeighbor.y] && !visited.containsKey(topNeighbor)) {
				visited.put(topNeighbor, true);
				parents.put(topNeighbor, current);
				queue.offer(topNeighbor);
			}
			
			if (isValid(bottomNeighbor.x, bottomNeighbor.y) && !cells[bottomNeighbor.x][bottomNeighbor.y] && !visited.containsKey(bottomNeighbor)) {
				visited.put(bottomNeighbor, true);
				parents.put(bottomNeighbor, current);
				queue.offer(bottomNeighbor);
			}
		}
		
		HashSet<Point> solution = new HashSet<>();
		Point current = new Point(gridSize - 1, gridSize - 2); // Backtrack path from the last point
		while (current != start) {
			Point parent = parents.get(current);
			solution.add(parent);
			current = parent;
		}
		mazeDisplay.offerSolution(solution);
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
				userLoc.setLocation(userLoc.x + 1, userLoc.y);
				mazeDisplay.setUserLoc(userLoc);
			}
		} else if (e.getKeyChar() == 'w') {
			if (isValid(userLoc.x - 1, userLoc.y)) {
				userLoc.setLocation(userLoc.x - 1, userLoc.y);
				mazeDisplay.setUserLoc(userLoc);
			}
		} else if (e.getKeyChar() == 'a') {
			if (isValid(userLoc.x, userLoc.y - 1)) {
				userLoc.setLocation(userLoc.x, userLoc.y - 1);
				mazeDisplay.setUserLoc(userLoc);
			}	
		} else if (e.getKeyChar() == 'd') {
			if (isValid(userLoc.x, userLoc.y + 1)) {
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
