package visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.ArrayList;

import javax.swing.JPanel;

public class MazePanel extends JPanel {
	
	private int rows;
	private int cols;

	private ArrayList<Rectangle> cells;
	
	public MazePanel(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
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
        	for (int j = 0; j < gridCols * 2 + 1; j++) {
        		cells.add(new Rectangle(offsetX + (i * cellWidth), offsetY + (j * cellHeight), cellWidth, cellHeight));
        	}
        }
        
        g2d.setColor(Color.BLUE);
        for (Rectangle rect : cells) {
        	g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
        
	}
}
