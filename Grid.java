package spinPossible;

import java.util.Random;

public class Grid {
	private int numberOfTiles;
	private int dimensions;
	private Tile[][] tileGrid;
	private int numberSelected;
	
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
		this.dimensions = dimensions;
		numberOfTiles = 0;
		numberSelected = 0;
	}
	public void rotateRectangle() // Rotates the selected rectangle.
	{
		int top1 = -1;
		int top2 = -1;
		int bottom1 = -1;
		int bottom2 = -1;
		Tile temp;
		/*for(int i = 0; i < dimensions*dimensions && top1 < 0 ; i++) // Gets the coordinates of the top-left-most selected Tile
		{
			if(tileGrid[coord1(i)][coord2(i)].isSelected())
			{
				top1 = coord1(i);
				top2 = coord2(i);
			}
		}	//*/
		if(top1 < 0) // top1 is still -1, indicating that no selected tiles were found, so there is nothing that can be rotated. Throw an exception and terminate the function.
		{
			throw new IllegalStateException("No tiles are currently selected. No rectangle to rotate.");
		}
		else // A tile was found, so the function can continue as normal.
		{
			/*for(int j = dimensions*dimensions-1; j >= 0 && bottom1 < 0 ; j--) // Gets the coordinates of the bottom-right-most selected Tile
			{
				if(tileGrid[coord1(j)][coord2(j)].isSelected())
				{
					bottom1 = coord1(j);
					bottom2 = coord2(j);
				}
			}//*/
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
	
	// Takes two tile positions as inuput. These are taken to be the top-left corner and bottom-right corner of the rectangle that needs to be filled.
	// Selects all of the tiles in the rectangle.
	
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
		for(int i = Math.min(top1,bottom1); i <= Math.max(top1,bottom1); i++)
		{
			for(int j = Math.min(top2,bottom2); j <= Math.max(top2,bottom2); j++)
			{
				tileGrid[i][j].select(true);
			}
		}
	}
	
	public void addTile(Tile newTile) // Adds a tile to tileGrid. If tileGrid is full, it throws an indexOutOfBounds exception.
	{
		if(numberOfTiles < (dimensions*dimensions)-1) // If tileGrid is not full
		{
			tileGrid[coord1(numberOfTiles)][coord2(numberOfTiles)] = newTile;
			numberOfTiles++;
		}
		else // tileGrid is full
		{
			throw new IndexOutOfBoundsException("Grid is full. Cannot add another tile.");
		}
	}
	
	public void randomize(int turns) // Randomly chooses two tiles for fillRectangle and rotateRectangle. It repeats this process "turns" number of times.
	{
		Random randomTile = new Random();
		for(int i = 0; i < turns; i++)
		{
			fillInRectangle(randomTile.nextInt((dimensions*dimensions)-1),randomTile.nextInt((dimensions*dimensions)-1));
			rotateRectangle();
		}
	}
	
	public void clear() // Deselects all tiles
	{
		for(int i = 0; i < dimensions; i++)
		{
			for(int j = 0; j < dimensions; j++)
			{
				tileGrid[i][j].select(false);
			}
		}
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
	private Tile getTile(int tile)
	{
		return tileGrid[coord1(tile)][coord2(tile)];
	}
	private int findTopLeftSelectedTile()
	{
		int topLeftTile = -1;
		for(int i = 0; i < dimensions*dimensions && topLeftTile < 0 ; i++) // Gets the coordinates of the top-left-most selected Tile
		{
			if(tileGrid[coord1(i)][coord2(i)].isSelected())
			{
				topLeftTile = i;
			}
		}
		return topLeftTile;
	}
	private int findBottomRightSelectedTile()
	{
		int bottomLeftTile = -1;
		for(int j = dimensions*dimensions-1; j >= 0 && bottomLeftTile < 0 ; j--) // Gets the coordinates of the bottom-right-most selected Tile
		{
			if(tileGrid[coord1(j)][coord2(j)].isSelected())
			{
				bottomLeftTile = j;
			}
		}
		return bottomLeftTile;
	}
	public void selectTile(int tile) // Originally returned the tile found at the given position in tileGrid; now, sets that tile's selected value to the boolean parameter.
	{
		getTile(tile).select(true);
		if(numberSelected == 1)
		{
			fillInRectangle(findTopLeftSelectedTile(),findBottomRightSelectedTile());
		}
	}
	public boolean tileIsSelected(int tile)
	{
		return getTile(tile).isSelected();
	}
}
