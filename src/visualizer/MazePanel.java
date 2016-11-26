package visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.util.HashSet;

import javax.swing.JPanel;

public class MazePanel extends JPanel {
	
	private static final Color HAWKS = new Color(253, 210, 18, 255);
	private static final Color COOL_BLU = new Color(50, 100, 255);
	private static final Color SOLUTION = Color.GREEN;
	
	private int rows;
	private int cols;
	private int gridRows;
	private int gridCols;
	
	private boolean[][] obstacles;
	private HashSet<Point> solution;
	private Point userLoc;
	private Point check;
	
	/**
	 * Constructor for MazePanel
	 * @param rows the number of rows the maze has
	 * @param cols the number of columns the maze has
	 * @param obstacles a 2D array containing booleans representing whether a certain cell is open or not
	 */
	public MazePanel(int rows, int cols, boolean[][] obstacles) {
		this.rows = rows;
		this.cols = cols;
		gridRows = rows * 2 + 1;
		gridCols = cols * 2 + 1;
		
		this.obstacles = obstacles;
		solution = null;
		userLoc = null;
		check = new Point(-1, -1); // We are using this point to prevent the program from creating a new point
								   // every time we check if a point is in the solution, this prevents unnecessary
								   // garbage collection
	}
	
	/**
	 * Sets the user location to a new point
	 * @param p the point representing the new user location
	 */
	public void setUserLoc(Point p) {
		userLoc = p;
		repaint();
	}
	
	/**
	 * Offers the solution to the MazeVisualizer
	 * If a null value is passed into the function, the solution will not display
	 * @param solution the HashSet containing all points in the solution.
	 */
	public void offerSolution(HashSet<Point> solution) {
		this.solution = solution;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		
		int width = getWidth();
		int height = getHeight();
		
		int cellWidth = width / gridRows;
		int cellHeight = height / gridCols;
		
		int offsetX = (width - (gridRows * cellWidth)) / 2;
		int offsetY = (height - (gridCols * cellHeight)) / 2;
		
        for (int i = 0; i < gridRows; i++) {
        	for (int j = 0; j < gridCols; j++) {
        		
        		int x = offsetX + (j * cellWidth);
        		int y = offsetY + (i * cellHeight);
        		int w = cellWidth;
        		int h = cellHeight;
        		Color color = Color.BLACK;
        		
        		if (i == 0 && j == 1) {
        			color = Color.GREEN;
        		} else if (i == gridRows - 1 && j == gridCols - 2) {
        			color = Color.RED;
        		} else if (obstacles[i][j]) {
        			color = Color.BLACK;
        		} else {
        			color = Color.WHITE;
        		}
        		
        		if (solution != null) {
        			check.x = i;
        			check.y = j;
        			if (solution.contains(check)) {
        				color = SOLUTION;
        			}
        		}
        		
        		if (userLoc != null && i == userLoc.x && j == userLoc.y) {
        			color = Color.ORANGE;
        		}
        		
        		g2d.setColor(color);
        		g2d.fillRect(x, y, w, h);
        	}
        }
	}
}
