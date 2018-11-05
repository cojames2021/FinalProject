package spinPossible;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SpinPossibleController extends JPanel implements MouseListener {
	private Grid gameGrid;
	private JFrame gameFrame;
	private int gridSize;
	private boolean gameIsReady;
	private Container gameContentPane;
	private JPanel panelContainer;
	private Timer helpTimer;
	private int helpCounter=0;
	private JButton playButton;
	private JButton helpButton;
	private JButton clearButton;
	private JButton rulesButton;
	private JComboBox difficultyBox;
	private JComboBox sizeBox;
	private JLabel difficulty;
	private JLabel sizeLabel;
	private JPanel gridPanel;
	private JPanel actionPanel;
	
	/**********************************************************************************
	 * (Initial JOptionPane, probably)
	 * Welcome to SpinPossible!
	 * - Play
	 * - Import Custom Grid
	 * - Quit
	 * 
	 * ("Import Custom Grids")
	 * JInputDialogue asking for filename, etc >> opens file or errors out back to main menu
	 * 
	 * ("Play" Menu)
	 * 		[easy 4x4]
	 *  	{Preset Grid}		{Randomly generate a grid}
	 *  
	 * 
	 * (The actual grid)
	 * 		{ROTATE!!!!!!}
	 * 		
	 * 
	 * [] - dropdown menu
	 * {} - button
	 * O - radio button
	 ********************************************************************************/
	
	public static void main(String[] args) {
		SpinPossibleController myController = new SpinPossibleController("Spin Possible", 50, 50, 1000, 700);
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
	        actionPanel=new JPanel();
	        actionPanel.setSize((int)(gameWindowWidth*0.2), gameWindowHeight);
	        gameFrame.add(actionPanel);
	        gridPanel = new JPanel();
	        gridPanel.setSize((int)(gameWindowWidth*0.8), gameWindowHeight);
	        gameFrame.add(gridPanel);
	        actionPanel.setLocation((int)(gameWindowWidth*0.8), 0);
	        actionPanel.setBackground(Color.green);
	        playButton = new JButton("Play");
	        playButton.setSize((int)(actionPanel.getWidth()*0.5), 50);
	        actionPanel.add(playButton);
	        playButton.setLocation(0, 5);
	        String[] sizeList = {"3x3","4x4","5x5"};
	        sizeBox = new JComboBox<String>(sizeList);
	        sizeBox.setSize((int)(actionPanel.getWidth()*0.5), 25);
	        String[] diffList = {"Easy","Medium","Hard"};
	        difficultyBox = new JComboBox<String>(diffList);
	        difficultyBox.setSize((int)(actionPanel.getWidth()*0.5), 25);
	        actionPanel.add(difficultyBox);
	        actionPanel.add(sizeBox);
	        sizeBox.setLocation(playButton.getX(), playButton.getY()+100);
	        difficultyBox.setLocation(sizeBox.getX(),sizeBox.getY()+75);
	        difficulty = new JLabel("Game Difficulty:");
	        sizeLabel = new JLabel("Grid Size:");
	        actionPanel.add(sizeLabel);
	        actionPanel.add(difficulty);
	        sizeLabel.setLocation(sizeBox.getX(), sizeBox.getY()-10);
	}
	
	public void createGrid(int dimensions, int turns) {
		gameGrid = new Grid(dimensions);
		for(int i = 1; i <= dimensions; i++)
		{
			gameGrid.addTile(new Tile(i));
		}
		gameGrid.randomize(turns);
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
	

	
	private void help() {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
