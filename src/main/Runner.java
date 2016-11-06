package main;

import java.util.ArrayList;

import generator.Wall;
import maze.Maze;

public class Runner {
	public static void main(String[] args) {
		Maze maze = new Maze(4);
		maze.generate();
		
		ArrayList<Wall> walls = maze.getWalls();
		
		for (Wall w : walls) {
			System.out.println(w);
		}
	}
}
