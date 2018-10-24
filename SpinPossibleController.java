package spinPossible;

import java.awt.Container;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.Color;

import javax.swing.JFrame;


public class SpinPossibleController extends JFrame {
	private Grid gameGrid;
	private JFrame gameFrame;
	private int gridSize;
	private boolean gameIsReady;
	private Container gameContentPane;
	private Timer helpTimer;
	private int helpCounter=0;
	
	public static void main(String[] args) {
		new SpinPossibleController("Spin Possible", 50, 50, 800, 800);

	}
	
	public SpinPossibleController(String title, int gameWindowX, int gameWindowY, int gameWindowWidth, int gameWindowHeight)
	{
		 	gameFrame = new JFrame(title);
	        gameFrame.setSize(gameWindowWidth, gameWindowHeight);
	        gameFrame.setLocation(gameWindowX, gameWindowY);
	        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        gameContentPane = gameFrame.getContentPane();
	        gameContentPane.setLayout(null); // not need layout, will use absolute system
	        gameFrame.setVisible(true);
	}
	
	public void createGrid(String filename) {
		
	}
	
	public void placeTilesInGrid() {
		
	}
	
	private void rotate() {
		
	}
	
	private boolean finished() {
		return false;
	}
	
	public boolean playGame() {
		return false;
	}
	
	private void readFile(String filename) {
		
	}
	
	private void help() {
		
	}


}
