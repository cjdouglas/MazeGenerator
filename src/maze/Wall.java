package maze;

public class Wall {
	private int x;
	private int y;
	
	/**
	 * Initializer for a Wall
	 * @param x The first node index associated with this Wall
	 * @param y The second node index associated with this Wall
	 */
	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the first node of this Wall
	 * @return The first node of this Wall
	 */
	public int x() {
		return x;
	}
	
	/**
	 * Returns the second node of this wall
	 * @return The second node of this wall
	 */
	public int y() {
		return y;
	}
	
	/**
	 * Returns a String representation of this Wall object
	 * @return String representation of this Wall object
	 */
	public String toString() {
		return x + " -> " + y;
	}
}
