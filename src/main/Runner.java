package main;

import generator.MazeGenerator;
import visualizer.MazeVisualizer;

public class Runner {
	public static void main(String[] args) {
		
		MazeGenerator gen = new MazeGenerator(12);
		gen.generate();
		
		MazeVisualizer v = new MazeVisualizer(gen.getMaze());
		v.run();
	}
}
