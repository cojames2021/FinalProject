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
	2 * * * * * *				12 13 14 15 16 17
	3 * * * * * ?				18 19 20 21 22 23
	4 * ! * * * *				24 25 26 27 28 29
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
		
	}
	public void switchTiles(int a, int b)			// Previously named "changePosition"
	{
		
	}
	
	private void fillInRectangle(int a, int b)
	{
		
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
