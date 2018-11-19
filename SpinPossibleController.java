package spinPossible;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private Tile<Integer>[][] grid;
	
	private final int HARD_DIFFICULTY_NUMBER_OF_ROTATIONS = 6;
	private final int MEDIUM_DIFFICULTY_NUMBER_OF_ROTATIONS = 4;
	private final int EASY_DIFFICULTY_NUMBER_OF_ROTATIONS = 2;
	
	private final Dimension BOX_AND_BUTTON_SIZE = new Dimension(75,25);
	private final int SPACE_BETWEEN_BOXES_AND_BUTTONS=100;

	private JButton playButton;
	private JButton helpButton;
	private JButton clearButton;
	private JButton rulesButton;
	private JButton finishedButton;
	
	private JComboBox difficultyBox;
	private JComboBox sizeBox;
	private JComboBox presetRandomizeBox;
	
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
	        actionPanel.setLayout(null);
	        actionPanel.setSize(gameWindowWidth,(int)(gameWindowHeight*0.05));
	        gameFrame.add(actionPanel);
	        
	        gridPanel = new JPanel();
	        gridPanel.setSize(gameWindowWidth, (int)(gameWindowHeight*0.95));
	        gridPanel.setLocation(actionPanel.getX()-5, actionPanel.getHeight());
	        gameFrame.add(gridPanel);
	    
	        playButton = new JButton("Play");
	        playButton.setSize(BOX_AND_BUTTON_SIZE);
	        playButton.setLocation(10, 5);
	        actionPanel.add(playButton);
	        
	        String[] presetOrRandom = {"Preset","Random"};
	        presetRandomizeBox = new JComboBox<String>(presetOrRandom);
	        presetRandomizeBox.setSize(BOX_AND_BUTTON_SIZE);
	        
	        String[] sizeList = {"3x3","4x4","5x5","6x6"};
	        sizeBox = new JComboBox<String>(sizeList);
	        sizeBox.setSize(BOX_AND_BUTTON_SIZE);
	        
	        String[] diffList = {"Easy","Medium","Hard"};
	        difficultyBox = new JComboBox<String>(diffList);
	        difficultyBox.setSize(BOX_AND_BUTTON_SIZE);
	        
	        actionPanel.add(presetRandomizeBox);
	        actionPanel.add(difficultyBox);
	        actionPanel.add(sizeBox);
	        presetRandomizeBox.setLocation(playButton.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS, playButton.getY());
	        sizeBox.setLocation(presetRandomizeBox.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS, presetRandomizeBox.getY());
	        difficultyBox.setLocation(sizeBox.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS,sizeBox.getY());
	        
	        helpButton = new JButton("Help");
	        rulesButton = new JButton("Rules");
	        finishedButton = new JButton("Check");
	        actionPanel.add(helpButton);
	        actionPanel.add(rulesButton);
	        actionPanel.add(finishedButton);
	        finishedButton.setLocation(difficultyBox.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS, playButton.getY());
	        helpButton.setLocation(finishedButton.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS, playButton.getY());
	        rulesButton.setLocation(helpButton.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS, playButton.getY());
	        finishedButton.setSize(BOX_AND_BUTTON_SIZE);
	        rulesButton.setSize(BOX_AND_BUTTON_SIZE);
	        helpButton.setSize(BOX_AND_BUTTON_SIZE);

	        playButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		gridPanel.removeAll();
	        		int size = gridSize();
	        		int difficulty = gridDifficulty();
	        		
	        		createGrid(size,0);
	        		resize();

	        	}
	        });
	        
	}
	
	public void createGrid(int dimensions, int turns) {
		gameGrid = new Grid(dimensions, gridPanel);
		for(int i = 1; i <= dimensions*dimensions; i++)
		{
			gameGrid.addTile(i);
		}
		gameGrid.randomize(turns);
		gameGrid.addMouseListener(this);
		gameGrid.clear();
	}
	
	public void createGrid(String filename)
	{
		readFile(filename);
		gameGrid.addMouseListener(this);
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
			gameGrid = new Grid(size*size, gridPanel);
			for(int i = 0; i<size*size;i++) 
			{
				gameGrid.addTile(fileReader.nextInt());
			}
			fileReader.close();
		} 
		catch (FileNotFoundException e) {
				
		}
	}
	

	
	private void help() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gameGrid.selectTile(e.getX(),e.getY());
		
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
	private void resize() {
		gameFrame.setSize(gameFrame.getWidth()+1, gameFrame.getHeight());
		gameFrame.setSize(gameFrame.getWidth()-1, gameFrame.getHeight());
	}
	
	private int gridSize() {
		int size;
		if(sizeBox.getSelectedItem().equals("3x3"))
		{
			size=3;
		}
		else if(sizeBox.getSelectedItem().equals("4x4"))
		{
			size=4;
		}
		else if(sizeBox.getSelectedItem().equals("5x5"))
		{
			size=5;
		}
		else
		{
			size=6;
		}
		return size;
	}
	
	private int gridDifficulty() {
		int difficulty =0;
		if(difficultyBox.getSelectedItem().equals("Easy"))
		{
			difficulty=EASY_DIFFICULTY_NUMBER_OF_ROTATIONS;
		}
		else if(difficultyBox.getSelectedItem().equals("Medium"))
		{
			difficulty=MEDIUM_DIFFICULTY_NUMBER_OF_ROTATIONS;
		}
		else
		{
			difficulty=HARD_DIFFICULTY_NUMBER_OF_ROTATIONS;
		}
		return difficulty;
		
	}


}
