package main;

import java.util.ArrayList;

import generator.MazeGenerator;
import maze.Wall;
import visualizer.MazeVisualizer;

public class Runner {
	public static void main(String[] args) {
		MazeVisualizer v = new MazeVisualizer(5);
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
