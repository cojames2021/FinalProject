package spinPossible;

public class GridDriver
{
	public static void main(String[] args)
	{
		System.out.println("Testing constructor, initializing grid of size 36");
		Grid grid = new Grid(6);
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
			System.out.println("FAILURE - fix grid class.");
		System.out.println(grid.toString());
		
		System.out.print("\nTesting selectTile, selecting two should fill in the rectangle: ");
		grid.selectTile(4);
		grid.selectTile(32);
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
		if(grid.toString().equals(testToString))
			System.out.println("SUCCESS");
		else
		{
			System.out.println("FAILURE - Check grid class");
			System.out.println("\n"+grid.toString()+"\n");
			System.out.println(testToString);
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
			System.out.println("\n"+testToString);
		}
	}
	
	
	
}
