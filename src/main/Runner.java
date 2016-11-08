package main;

import java.util.ArrayList;

import generator.MazeGenerator;
import maze.Wall;

public class Runner {
	public static void main(String[] args) {
		MazeGenerator mazeGenerator = new MazeGenerator(5);
		
		ArrayList<Wall> walls = mazeGenerator.getMSTWalls();		
		for (Wall w : walls) {
			System.out.println(w);
		}
		
		System.out.println("\n");
		
		mazeGenerator.rebuild(4);
		walls = mazeGenerator.getMSTWalls();
		for (Wall w : walls) {
			System.out.println(w);
		}
	}
}
