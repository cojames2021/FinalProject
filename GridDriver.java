package spinPossible;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class GridDriver
{
	public static void main(String[] args) throws InterruptedException
	{
		if(GUITest())
		{
			System.out.println("good");
		}
		
		/* // */
	}
	
	public static boolean GUITest() throws InterruptedException
	{
		boolean testSuccessful = true;
		JFrame testFrame = new JFrame();
		testFrame.setBounds(100, 100, 800, 800);
		testFrame.setLayout(new BorderLayout());
		System.out.println("Adding tiles to grid...");
		Container contentPane = testFrame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.WHITE);
		Grid grid = new Grid(6,contentPane);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setVisible(true);
		for(int i = 1; i <= 36; i++)
		{
			//Tile temp = new Tile<Integer>(i, new Dimension(600/6,600/6));
			grid.addTile(i);
		}
		
		/*//Tile testTile = new Tile<Integer>(1,0,0,100,100);
		//contentPane.add(testTile);
		for(int i = 0; i < 5; i++)
		{	
			grid;
			Thread.sleep(1000);
		}//*/
		
		for(int i = 0; i < 5; i++)
		{
			grid.selectTile(1);
			Thread.sleep(1000);
			grid.rotateRectangle();
			Thread.sleep(1000);
		}
		
		return testSuccessful;
	}
	
	public static boolean methodsTest()
	{
		boolean testSuccessful = true;
		/*System.out.println("Testing constructor, initializing grid of size 36");
		Grid grid = new Grid(6,0,0,1600,900);
		System.out.println("Adding tiles to grid...");
		for(int i = 0; i < 36; i++)
		{
			grid.addTile(new Tile(i));
		}
		System.out.println("Successfully added 36 tiles.");
		System.out.print("Does it throw an exception if one too many tiles is added?: ");
		try
		{
			grid.addTile(new Tile(36));
			System.out.println("FAILURE - fix grid class.");
			testSuccessful = false;
		}
		catch(IndexOutOfBoundsException i)
		{
			System.out.println("SUCCESS");
		}
		System.out.print("\nTesting toString method: ");
		String testToString = "";
		for(int i = 0; i < 36; i++)
		{
			testToString = testToString+i+","+false+","+0;
			if(i%6 == 5)
				testToString+="\n";
			else
				testToString+="\t";
		}
		if(grid.toString().equals(testToString))
			System.out.println("SUCCESS");
		else
		{	System.out.println("FAILURE - fix grid class.");
			testSuccessful = false;
		}
		System.out.println(grid.toString());
		
		System.out.println("\nTesting selectTile, selecting two should fill in the rectangle...");
		grid.selectTile(4);
		System.out.println("Tile 4 selected...");
		grid.selectTile(32);
		System.out.println("Tile 32 selected...");
		testToString = "";
		for(int i = 0; i < 36; i++)
		{
			testToString = testToString+i+",";
			if(i%6 >= 2 && i%6 <= 4)
				testToString = testToString+true+","+0+"\t";
			else if(i%6 == 5)
				testToString = testToString+false+","+0+"\n";
			else
				testToString = testToString+false+","+0+"\t";
		}
		System.out.print("selectTile: ");
		if(grid.toString().equals(testToString))
			System.out.println("SUCCESS");
		else
		{
			System.out.println("FAILURE - Check grid class");
			System.out.println("\n"+grid.toString()+"\n");
			System.out.println(testToString);
			testSuccessful = false;
		}
		System.out.print("Testing rotateRectangle method: ");
		testToString = "";
		for(int i = 0; i < 36; i++)
		{
			if(i%6 >= 2 && i%6 <= 4)
				testToString = testToString+(36-i)+","+false+","+1+"\t";
			else if(i%6 == 5)
				testToString = testToString+i+","+false+","+0+"\n";
			else
				testToString = testToString+i+","+false+","+0+"\t";
		}
		grid.rotateRectangle();
		if(grid.toString().equals(testToString))
			System.out.println("SUCCESS");
		else
		{
			System.out.println("FAILURE - Check grid class");
			System.out.println(grid.toString());
			testSuccessful = false;
		}
		System.out.println("\n"+testToString+"\n");
		
		System.out.println("Testing swapTile method: ");
		grid.swapTiles(0,35);
		System.out.println(grid.toString());
		/*String[] testStringGrid = grid.toString().split("\t");
		
		if(testStringGrid[0].equals("35,false,0") && testStringGrid[1].equals("1,false,0"))
			System.out.println("SUCCESS");
		else
		{
			System.out.println("FAILURE - Check Grid Class");
			System.out.println(grid.toString());
		}
		for(int i = 0; i < testStringGrid.length; i++)
		{
			System.out.print(testStringGrid[i]+"\t");
			if(i%6 == 5)
				System.out.println("");
		} // */
		return testSuccessful;
	}
}
