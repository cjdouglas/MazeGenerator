package main;

import java.util.ArrayList;

import generator.MazeGenerator;
import maze.Maze;
import maze.Wall;
import visualizer.MazeVisualizer;

public class Runner {
	public static void main(String[] args) {
		
		Maze maze = new Maze(5);
		
		MazeVisualizer v = new MazeVisualizer(maze);
		v.run();
		
		/*
		mazeGenerator.rebuild(4);
		walls = mazeGenerator.getMSTWalls();
		for (Wall w : walls) {
			System.out.println(w);
		}
		*/
	}
}
