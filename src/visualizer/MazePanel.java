package visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.ArrayList;

import javax.swing.JPanel;

public class MazePanel extends JPanel {
	
	private static final Color HAWKS = new Color(253, 210, 18, 255);
	private static final Color COOL_BLU = new Color(50, 100, 255);
	
	private int rows;
	private int cols;
	
	private boolean[][] obstacles;
	private ArrayList<CJRectangle> cells;
	
	/**
	 * Constructor for MazePanel
	 * @param rows the number of rows the maze has
	 * @param cols the number of columns the maze has
	 * @param obstacles a 2D array containing booleans representing whether a certain cell is open or not
	 */
	public MazePanel(int rows, int cols, boolean[][] obstacles) {
		this.rows = rows;
		this.cols = cols;
		
		this.obstacles = obstacles;
		cells = new ArrayList<>();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		
		int width = getWidth();
		int height = getHeight();
		
		int gridRows = rows * 2 + 1;
		int gridCols = cols * 2 + 1;
		
		int cellWidth = width / gridRows;
		int cellHeight = height / gridCols;
		
		int offsetX = (width - (gridRows * cellWidth)) / 2;
		int offsetY = (height - (gridCols * cellHeight)) / 2;
        
        for (int i = 0; i < gridRows; i++) {
        	for (int j = 0; j < gridCols; j++) {
        		CJRectangle rect = new CJRectangle(offsetX + (j * cellWidth), offsetY + (i * cellHeight), 
        				cellWidth, cellHeight);
        		
        		if (i == 0 && j == 1) {
        			rect.setColor(Color.GREEN);
        		} else if (i == gridRows - 1 && j == gridCols - 2) {
        			rect.setColor(Color.RED);
        		} else if (obstacles[i][j]) {
        			rect.setColor(Color.BLACK);
        		} else {
        			rect.setColor(COOL_BLU);
        		}
        		
        		cells.add(rect);
        	}
        }
        
        for (CJRectangle rect : cells) {
        	g2d.setColor(rect.getColor());
        	g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
        
	}
}
