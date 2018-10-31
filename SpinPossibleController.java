package spinPossible;

import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SpinPossibleController extends JFrame {
	private Grid gameGrid;
	private JFrame gameFrame;
	private int gridSize;
	private boolean gameIsReady;
	private Container gameContentPane;
	private Timer helpTimer;
	private int helpCounter=0;
	private JButton helpButton;
	private JComboBox difficultyBox;
	private JComboBox sizeBox;
	private JPanel gridPanel;
	private JPanel actionPanel;
	
	public static void main(String[] args) {
		SpinPossibleController myController = new SpinPossibleController("Spin Possible", 50, 50, 800, 800);

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
	
	public void createGrid(int dimensions) {
		gameGrid = new Grid(dimensions);
	}
	
	public void createGrid(String filename)
	{
		readFile(filename);
	}
	
	private void rotate() {
		gameGrid.rotateRectangle();
	}
	
	private boolean finished() {
		return false;
	}
	
	public boolean playGame() {
		return false;
	}
	
	private void readFile(String filename) {
		File gridFile =new File(filename);
		try {
			Scanner fileReader = new Scanner(gridFile);
			int size = fileReader.nextInt();
			gameGrid = new Grid(size*size);
			for(int i = 0; i<size*size;i++) {
				gameGrid.addTile(new Tile<Integer>(fileReader.nextInt()));
			}
			fileReader.close();
		} 
		catch (FileNotFoundException e) {
				
		}
	}
	
	private void setGridSize() {
		
	}
	
	private void help() {
		
	}


}
