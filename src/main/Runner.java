package main;

import java.util.ArrayList;

import generator.MazeGenerator;
import maze.Maze;
import maze.Wall;
import visualizer.MazeVisualizer;

public class Runner {
	public static void main(String[] args) {
		
		MazeGenerator gen = new MazeGenerator(12);
		gen.generate();
		
		MazeVisualizer v = new MazeVisualizer(gen.getMaze());
		v.run();
	}
}
