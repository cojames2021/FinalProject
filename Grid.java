package spinPossible;

public class Grid {
	private int numberOfTiles;
	private int dimensions;
	private Tile[][] tileGrid;
	private boolean[][] selectedTiles;
	private int selectedCounter;
	
/************************************************************************************************************************
	Visualization of the Grid
	Data stored in tileGrid
	tileGrid[3][6] = ?			Each space, numbered 0 to dimensions^2
	tileGrid[0][2] = &			To get coord1 of a space's number, take number/dimensions (integer division)
	tileGrid[4][1] = !			To get coord2 of a space's number, take number%dimensions
	  0 1 2 3 4 5				
	0 * * & * * *				00 01 02 03 04 05
	1 * * * * * *				06 07 08 09 10 11
	2 * * # # # *				12 13 14 15 16 17
	3 * * # # # ?				18 19 20 21 22 23
	4 * ! # # # *				24 25 26 27 28 29
	5 * * * * * *				30 31 32 33 34 35
************************************************************************************************************************/
	
	
	
	public Grid(int dimensions)
	{
		tileGrid = new Tile[dimensions][dimensions];
		selectedTiles = new boolean[dimensions][dimensions];
		this.dimensions = dimensions;
		selectedCounter = 0;
		numberOfTiles = 0;
	}
	public void rotateRectangle()
	{
		int top1 = -1;
		int top2 = -1;
		int bottom1 = -1;
		int bottom2 = -1;
		Tile temp;
		for(int i = 0; i < dimensions*dimensions && top1 < 0 ; i++) // Gets the coordinates of the top-left-most selected Tile
		{
			if(selectedTiles[coord1(i)][coord2(i)])
			{
				top1 = coord1(i);
				top2 = coord2(i);
			}
		}
		for(int j = dimensions*dimensions-1; j >= 0 && bottom1 < 0 ; j--) // Gets the coordinates of the bottom-right-most selected Tile
		{
			if(selectedTiles[coord1(j)][coord2(j)])
			{
				bottom1 = coord1(j);
				bottom2 = coord2(j);
			}
		}
		for(int i = top1; i <= bottom1; i++)
		{
			for(int j = top2; j < bottom2; j++)
			{
				temp = tileGrid[i][j];
				tileGrid[i][j] = tileGrid[bottom1-(i-top1)][bottom2-(j-top2)];
				tileGrid[i][j].changeOrientation();
				tileGrid[bottom1-(i-top1)][bottom2-(j-top2)] = temp;
				tileGrid[bottom1-(i-top1)][bottom2-(j-top2)].changeOrientation();
			}
		}
	}
	/*private void switchTiles(int tile1, int tile2)			// Previously named "changePosition"
	{
		Tile temp = tile(tile1);
		tileGrid[coord1(tile1)][coord2(tile1)] = tileGrid[coord1(tile2)][coord2(tile2)];
		tileGrid[coord1(tile1)][coord2(tile1)].changeOrientation();
		tileGrid[coord1(tile2)][coord2(tile2)] = temp;
		tileGrid[coord1(tile2)][coord2(tile2)].changeOrientation();
	}	// */
	
	private void fillInRectangle(int topTile, int bottomTile)
	{
		if(bottomTile > topTile)
		{
			int temp = topTile;
			topTile=bottomTile;
			bottomTile=topTile;
		}
		int top1 = coord1(topTile);
		int top2 = coord2(topTile);
		int bottom1 = coord1(bottomTile);
		int bottom2 = coord2(bottomTile);
		for(int i = top1; i <= bottom1; i++)
		{
			for(int j = top2; j <= bottom2; j++)
			{
				tileGrid[i][j].select(true);
				selectedTiles[i][j] = true;
				selectedCounter++;
			}
		}
	}
	
	public void addTile(Tile newTile)
	{
		if(numberOfTiles < (dimensions*dimensions)-1)
		{
			tileGrid[coord1(numberOfTiles)][coord2(numberOfTiles)] = newTile;
			numberOfTiles++;
		}
		else
		{
			throw new IndexOutOfBoundsException("Grid is full. Cannot add another tile.");
		}
	}
	
	private void randomize()
	{
		
	}
	
	public void clear() // Deselects all tiles
	{
		for(int i = 0; i < dimensions; i++)
		{
			for(int j = 0; j < dimensions; j++)
			{
				selectedTiles[i][j] = false;
				tileGrid[i][j].select(false);
			}
		}
		selectedCounter = 0;
	}
	
	public int numberSelected()
	{
		return selectedCounter;
	}
	
	
	// Helper Functions
	private int coord1(int tile) // Returns the first coordinate of a given position in tileGrid.
	{
		return tile/dimensions;
	}
	private int coord2(int tile) // Returns the first coordinate of a given position in tileGrid.
	{
		return tile%dimensions;
	}
	private Tile tile(int tile) // Returns the tile found at the given position in tileGrid.
	{
		return tileGrid[coord1(tile)][coord2(tile)];
	}
}
