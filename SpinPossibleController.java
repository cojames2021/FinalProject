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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SpinPossibleController extends JPanel implements MouseListener {
	private Grid gameGrid;
	private JFrame gameFrame;
	private int gridSize;
	private boolean gameIsReady;
	private boolean finished =false;
	private Container gameContentPane;
	private int helpCounter=0;
	private Tile<Integer>[][] grid;
	
	private final int HARD_DIFFICULTY_NUMBER_OF_ROTATIONS = 7;
	private final int MEDIUM_DIFFICULTY_NUMBER_OF_ROTATIONS = 5;
	private final int EASY_DIFFICULTY_NUMBER_OF_ROTATIONS = 3;
	private final int THREE_BY_THREE_GRID_SIZE = 3;
	private final int FOUR_BY_FOUR_GRID_SIZE = 4;
	private final int FIVE_BY_FIVE_GRID_SIZE = 5;
	private final int SIX_BY_SIX_GRID_SIZE = 6;
	private final int HELP_MAX = 3;
	private final int BOX_AND_BUTTON_Y;
	private final Dimension BOX_AND_BUTTON_SIZE = new Dimension(75,25);
	private final int SPACE_BETWEEN_BOXES_AND_BUTTONS=100;

	private JButton playButton;
	private JButton helpButton;
	private JButton clearButton;
	private JButton rulesButton;
	private JButton rotateButton;
	
	private JComboBox<String> difficultyBox;
	private JComboBox<String> sizeBox;
	private JComboBox<String> presetRandomizeBox;
	
	private JPanel gridPanel;
<<<<<<< HEAD
	private JPanel actionPanel;	

=======
	private JPanel actionPanel;
	
>>>>>>> ef57b80d1214bf8657a10421bb4d9379b0ebd479
	
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
	        actionPanel.setSize(gameWindowWidth,(int)(gameWindowHeight*0.05));//The 0.05 is arbitrary
	        gameFrame.add(actionPanel);
	        
	        gridPanel = new JPanel();
	        gridPanel.setSize(gameWindowWidth, (int)(gameWindowHeight*0.9));//This is based off of the 0.05 so the spacing looks nice
	        gridPanel.setLocation(actionPanel.getX()-5, actionPanel.getHeight());
	        gameFrame.add(gridPanel);
	    
	        playButton = new JButton("Play");
	        playButton.setSize(BOX_AND_BUTTON_SIZE);
	        playButton.setLocation(10, 5);//This is an arbitrary location that is convenient
	        actionPanel.add(playButton);
	        BOX_AND_BUTTON_Y=playButton.getY();
	        
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
	        presetRandomizeBox.setLocation(playButton.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS, BOX_AND_BUTTON_Y);
	        sizeBox.setLocation(presetRandomizeBox.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS, BOX_AND_BUTTON_Y);
	        difficultyBox.setLocation(sizeBox.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS,BOX_AND_BUTTON_Y);
	        
	        helpButton = new JButton("Help?");
	        rulesButton = new JButton("Rules");
	        rotateButton = new JButton("Rotate");
	        clearButton = new JButton("Clear");
	        actionPanel.add(helpButton);
	        actionPanel.add(rulesButton);
	        actionPanel.add(rotateButton);
	        actionPanel.add(clearButton);
	        helpButton.setLocation(difficultyBox.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS,BOX_AND_BUTTON_Y);
	        rulesButton.setLocation(helpButton.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS, BOX_AND_BUTTON_Y);
	        rotateButton.setLocation(rulesButton.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS,BOX_AND_BUTTON_Y);
	        clearButton.setLocation(rotateButton.getX()+SPACE_BETWEEN_BOXES_AND_BUTTONS, BOX_AND_BUTTON_Y);
	        rulesButton.setSize(BOX_AND_BUTTON_SIZE);
	        helpButton.setSize(BOX_AND_BUTTON_SIZE);
	        rotateButton.setSize(BOX_AND_BUTTON_SIZE);
	        clearButton.setSize(BOX_AND_BUTTON_SIZE);

	        playButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		int size = gridSize();
	        		int difficulty = gridDifficulty();
	        		if(presetRandomizeBox.getSelectedItem().equals("Preset"))
	        		{
	        			createGrid(choosePresetGrid());
	        		}
	        		else 
	        		{
	        			createGrid(size,difficulty);
	        			
	        		}
	        		resize();
	        		grid = gameGrid.getGrid();
	        		gameIsReady = true;

	        	}
	        });
	        
	        helpButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(gameIsReady)
	        		{
	        			finished();
	        			if(!finished)
	        			{
	        				help();
	        				}
	        		}
	        	}
	        });
	       	rulesButton.addActionListener(new ActionListener() {
	       		public void actionPerformed(ActionEvent e) {
	       			File rules = new File("Rules");
	       			try {
						Scanner ruleReader = new Scanner(rules);
						String rulebook = "";
						while(ruleReader.hasNextLine())
						{
							if(ruleReader.next().equals("/n"))
							{
								rulebook = rulebook + "\n";
							}
							else {
								rulebook = rulebook + " " + ruleReader.nextLine();
							}
						}
						ruleReader.close();
						JTextArea text = new JTextArea(rulebook);
						JScrollPane logEntries = new JScrollPane(text);
						JOptionPane.showMessageDialog(gameFrame, logEntries);
					} catch (FileNotFoundException e1) {
						
					}
	       		}
	       	});
	        
		    rotateButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	if(gameIsReady)
		        	{
		        		rotate();
		        		finished();
		        	}
		        }
		    });
		    clearButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	if(gameIsReady)
		        	{
		        		gameGrid.clear();
		        	}
		        }
		    });
	        
	        
	        
	}
	
	public void createGrid(int dimensions, int turns) {
		gridPanel.removeAll();
		helpCounter = 0;
		gameGrid = new Grid(dimensions, gridPanel);
		gridSize=dimensions;
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
		helpCounter = 0;
		gridPanel.removeAll();
		readFile(filename);
		gameGrid.addMouseListener(this);
	}
	
	private void rotate() {
		try{gameGrid.rotateRectangle();}
		catch (IllegalStateException i)
		{
		}
	}
	
	private void finished() {
		finished = false;
		int correctSpotCounter=0;
		int currentSpot = 1;
		int correctOrientationCounter = 0;
 		for(int i = 0; i<gridSize;i++)
		{
 			for(int j=0;j<gridSize;j++)
 			{
	 			if(grid[i][j].getValue()==currentSpot)
	 			{
	 				correctSpotCounter++;
	 				if(grid[i][j].getOrientation()==Tile.RIGHTSIDE_UP)
	 				{
	 					correctOrientationCounter++;
	 				}
	 			}
	 			currentSpot++;
 			}
 			
 			
		}
 		
 		if(correctSpotCounter == gridSize*gridSize && correctOrientationCounter == gridSize*gridSize)
 		{
 			finished = true;
 		}
 		if(finished)
 		{
 			JOptionPane.showMessageDialog(gameFrame, "You won!");
 			
 		}
	}
	
	private void readFile(String filename) {
		File gridFile =new File(filename);
		try {
			Scanner fileReader = new Scanner(gridFile);
			int size = fileReader.nextInt();
			gridSize=size;
			int currentValue;
			int currentOrientation;
			gameGrid = new Grid(size, gridPanel);
			for(int i = 0; i<size*size;i++) 
			{
				currentValue = fileReader.nextInt();
				currentOrientation = fileReader.nextInt();
				gameGrid.addTile(currentValue, currentOrientation);
			}
			fileReader.close();
		} 
		catch (FileNotFoundException e) {
				
		}
	}
	

	
	private void help() {
		int currentSpot = 0;
		boolean isCorrect = true;
		boolean foundCorrect = false;
		int wrongTile;
		int wrong1 = -1;
		int correctTile;
		int correct1 = -1;
		if(helpCounter>=HELP_MAX)
		{
			JOptionPane.showMessageDialog(gameFrame, "You have no more helps left!");
		}
		else
		{
			for(int i = 0;i<gridSize && isCorrect;i++)
			{
				for(int j = 0;j<gridSize && isCorrect;j++)
				{
					if(grid[i][j].getValue() == currentSpot+1)
					{
						currentSpot++;
					}
					else
					{	
						isCorrect = false;
						wrong1 = j;
					}
				}
			}
			wrongTile = currentSpot;
			currentSpot = 0;
			for(int i=0;i<gridSize && !foundCorrect;i++)
			{
				for(int j=0;j<gridSize && !foundCorrect;j++)
				{
					if(grid[i][j].getValue()==wrongTile+1)
					{
						foundCorrect = true;
						correct1 = j;
					}
					else currentSpot++;
				}
			}
			correctTile = currentSpot;
			
			if(wrong1 <= correct1)
			{
				gameGrid.clear();
				gameGrid.selectTile(wrongTile);
				gameGrid.selectTile(correctTile);
				gameGrid.rotateRectangle();
				helpCounter++;
			}
			else
			{
				if(JOptionPane.showConfirmDialog(gameFrame, "About to move the "+(wrongTile+1)+" tile to its correct place by swapping the two tiles without rotating. Is this ok?",
						"Warning: Swapping tiles", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == 0)
				{
					gameGrid.swapTiles(wrongTile, correctTile);
					helpCounter++;
				}
			}
			
		}
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
			size=THREE_BY_THREE_GRID_SIZE;
		}
		else if(sizeBox.getSelectedItem().equals("4x4"))
		{
			size=FOUR_BY_FOUR_GRID_SIZE;
		}
		else if(sizeBox.getSelectedItem().equals("5x5"))
		{
			size=FIVE_BY_FIVE_GRID_SIZE;
		}
		else
		{
			size=SIX_BY_SIX_GRID_SIZE;
		}
		
		return size;
	}
	
	private int gridDifficulty() {
		int difficulty;
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
	private String choosePresetGrid()
	{
		String filename;
		if(sizeBox.getSelectedItem().equals("3x3"))
		{
			if(difficultyBox.getSelectedItem().equals("Easy"))
			{
				filename = "easy3x3";
			}
			else if(difficultyBox.getSelectedItem().equals("Medium"))
			{
				filename = "medium3x3";
			}
			else
			{
				filename = "hard3x3";
			}
		}
		else if(sizeBox.getSelectedItem().equals("4x4"))
		{
			if(difficultyBox.getSelectedItem().equals("Easy"))
			{
				filename = "easy4x4";
			}
			else if(difficultyBox.getSelectedItem().equals("Medium"))
			{
				filename = "medium4x4";
			}
			else
			{
				filename = "hard4x4";
			}
		}
		else if(sizeBox.getSelectedItem().equals("5x5"))
		{
			if(difficultyBox.getSelectedItem().equals("Easy"))
			{
				filename = "easy5x5";
			}
			else if(difficultyBox.getSelectedItem().equals("Medium"))
			{
				filename = "medium5x5";
			}
			else
			{
				filename = "hard5x5";
			}
		}
		else
		{
			if(difficultyBox.getSelectedItem().equals("Easy"))
			{
				filename = "easy6x6";
			}
			else if(difficultyBox.getSelectedItem().equals("Medium"))
			{
				filename = "medium6x6";
			}
			else
			{
				filename = "hard6x6";
			}
		}
		
		return filename;
	}

	
}