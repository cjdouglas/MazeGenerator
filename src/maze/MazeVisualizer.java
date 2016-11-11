package maze;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

import generator.MazeGenerator;

public class MazeVisualizer {
	private JFrame mainFrame;
	private GridBagConstraints gc;
	
	private JPanel mazeDisplay;
	private JPanel controlPanel;
	
	private MazeGenerator mazeGenerator;
	
	public MazeVisualizer() {
		mainFrame = new JFrame("Maze Generator");
		gc = new GridBagConstraints();
		
		mazeDisplay = new JPanel();
		controlPanel = new JPanel();
		
		mazeGenerator = new MazeGenerator(5); // Default value of 5 to start
		
		setup();
	}
	
	private void setup() {
		mainFrame.getContentPane().setLayout(new GridBagLayout());
		mainFrame.setSize(750, 750);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
	}
	
	public void run() {
		mainFrame.setVisible(true);
	}
}
