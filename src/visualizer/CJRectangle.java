package visualizer;

import java.awt.Color;
import java.awt.Rectangle;

public class CJRectangle extends Rectangle {
	
	private Color color;
	
	public CJRectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public CJRectangle(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}
	
	/**
	 * Returns the Color of this Rectangle
	 * @return The Color of this Rectangle
	 */
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color newColor) {
		color = newColor;
	}
}
