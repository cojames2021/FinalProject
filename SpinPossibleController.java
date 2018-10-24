package spinPossible;

import java.awt.Color;

import javax.swing.JFrame;

public class SpinPossibleController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public SpinPossibleController(String title, int gameWindowX, int gameWindowY, int gameWindowWidth, int gameWindowHeight)
	{
		 	gameJFrame = new JFrame(title);
	        gameJFrame.setSize(gameWindowWidth, gameWindowHeight);
	        gameJFrame.setLocation(gameWindowX, gameWindowY);
	        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        gameContentPane = gameJFrame.getContentPane();
	        gameContentPane.setLayout(null); // not need layout, will use absolute system
	        gameContentPane.setBackground(Color.blue);
	        gameJFrame.setVisible(true);
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
