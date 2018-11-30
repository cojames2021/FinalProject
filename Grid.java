package spinPossible;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

public class Grid<T> extends JPanel {
	private int numberOfTiles;
	private int dimensions;
	private Tile<T>[][] tileGrid;
	private int numberSelected;
	private final int MAXIMUM_DIMENSIONS = 6;
	private final int MINIMUM_DIMENSIONS = 3;
	private boolean initialized = false;
	
	
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
	
	
	
	@SuppressWarnings("unchecked")
	public Grid(int dimensions, Container gridPanel)
	{
		if(dimensions > MAXIMUM_DIMENSIONS || dimensions < MINIMUM_DIMENSIONS)
		{
			throw new IllegalArgumentException("Grid must be at least 3x3, and no greater than 6x6");
		}
		else
		{
			int tileSize = gridPanel.getHeight()-100;
			tileGrid = new Tile[dimensions][dimensions];
			this.dimensions = dimensions;
			numberOfTiles = 0;
			numberSelected = 0;
			this.setLayout(new GridBagLayout());
			gridPanel.add(this);
			this.setSize(new Dimension(tileSize,tileSize));
			initialized = true;
		}
	}
	public void rotateRectangle() // Rotates the selected rectangle.
	{
		checkInitialization();
		int top1 = -1;
		int top2 = -1;
		int bottom1 = -1;
		int bottom2 = -1;
		Tile<T> temp1;
		Tile<T> temp2;
		int topLeft = findTopLeftSelectedTile();
		int bottomRight = findBottomRightSelectedTile();
		top1 = coord1(topLeft);
		top2 = coord2(topLeft);
		bottom1 = coord1(bottomRight);
		bottom2 = coord2(bottomRight);
		GridBagLayout layout = (GridBagLayout)this.getLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		
		if(top1 < 0 || bottom1 < 0 || top2 < 0 || bottom2 < 0) // one of the coordinates is still -1, indicating that no selected tiles were found, so there is nothing that can be rotated. Throw an exception and terminate the function.
		{
			throw new IllegalStateException("No tiles selected. Cannot rotate.");
		}
		else // A tile was found, so the function can continue as normal.
		{
			if(top1 == bottom1 && top2 == bottom2) // top and bottom are the same tile (meaning this is a 1-tile rectangle)
				tileGrid[top1][bottom2].changeOrientation();
			else
			{		
				int jStopVal; // We want j to go from top2 to bottom 2 if we are currently swapping two different rows. However, if we are swapping one row on itself, we want to stop halfway through the row.
				boolean onMiddleRow = false;
				for(int i = top1; i <= (top1+bottom1)/2; i++)
				{
					if(i==(top1+bottom1)/2 + (top1+bottom1)%2) // If we are swapping one row on itself
					{
						jStopVal = (top2+bottom2)/2;
						onMiddleRow = true;
					}
					else // If we are swapping two different rows
					{
						jStopVal = bottom2;
						onMiddleRow = false;
					}
					for(int j = top2; j <= jStopVal; j++)
					{
						swapTiles(i,j,bottom1-(i-top1),bottom2-(j-top2));
						
					}
					if(onMiddleRow && (top2+bottom2)%2 == 0) // This if makes sure that the center tile only rotates once.
						tileGrid[i][jStopVal].changeOrientation();
				}
			}
			//tileGrid[(top1+bottom1)/2][(top2+bottom2)/2].changeOrientation();
			numberSelected = 0;
			revalidate();
			clear();
			//super.repaint();
		}
	}
	public void swapTiles(int tile1, int tile2)
	{
		swapTiles(coord1(tile1),coord2(tile1),coord1(tile2),coord2(tile2));
	}
	
	public void swapTiles(int tile1Coord1, int tile1Coord2, int tile2Coord1, int tile2Coord2)			// Previously named "changePosition"
	{
		checkInitialization();
		GridBagLayout layout = (GridBagLayout)this.getLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		Tile<T> temp1 = tileGrid[tile1Coord1][tile1Coord2];
		layout.removeLayoutComponent(temp1);
		Tile<T> temp2 = tileGrid[tile2Coord1][tile2Coord2];
		tileGrid[tile1Coord1][tile1Coord2] = temp2;
		layout.removeLayoutComponent(temp2);
		constraints.gridx = tile1Coord2;
		constraints.gridy = tile1Coord1;
		layout.addLayoutComponent(temp2, constraints);
		tileGrid[tile1Coord1][tile1Coord2].changeOrientation();
		
		tileGrid[tile2Coord1][tile2Coord2] = temp1;
		constraints.gridx = tile2Coord2;
		constraints.gridy = tile2Coord1;
		layout.addLayoutComponent(temp1, constraints);
		tileGrid[tile2Coord1][tile2Coord2].changeOrientation();
	}	// */
	
	private void fillInRectangle(int topTile, int bottomTile)
	
	// Takes two tile positions as input. These are taken to be the top-left corner and bottom-right corner of the rectangle that needs to be filled.
	// Selects all of the tiles in the rectangle.
	
	{
		if(bottomTile < topTile)
		{
			int temp = topTile;
			topTile=bottomTile;
			bottomTile=temp;
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
				numberSelected++;
			}
		}
	}
	
	public void addTile(T value) // Adds a tile to tileGrid. If tileGrid is full, it throws an indexOutOfBounds exception.
	{
		checkInitialization();
		if(numberOfTiles < (dimensions*dimensions)) // If tileGrid is not full
		{
			Tile<T> newTile = new Tile<T>(value, new Dimension(this.getWidth()/dimensions,this.getHeight()/dimensions));
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = coord2(numberOfTiles);
			constraints.gridy = coord1(numberOfTiles);
			//constraints.gridwidth = GridBagConstraints.REMAINDER;
			//constraints.gridheight = GridBagConstraints.REMAINDER;
			//constraints.fill = GridBagConstraints.BOTH;
			tileGrid[coord1(numberOfTiles)][coord2(numberOfTiles)] = newTile;
			numberOfTiles++;
			this.add(newTile, constraints);
			newTile.setTileVisible(true);
			if(numberOfTiles < (dimensions*dimensions))
				initialized = true;
		}
		else // tileGrid is full
		{
			throw new IndexOutOfBoundsException("Grid is full. Cannot add another tile.");
		}
	}
	public void addTile(T value, int orientation)
	{
		addTile(value);
		if(orientation == Tile.UPSIDE_DOWN)
			getTile(numberOfTiles-1).changeOrientation();
	}
	
	public void randomize(int turns) // Randomly chooses two tiles for fillRectangle and rotateRectangle. It repeats this process "turns" number of times.
	{
		checkInitialization();
		Random randomTile = new Random();
		for(int i = 0; i < turns; i++)
		{
			fillInRectangle(randomTile.nextInt(dimensions*dimensions),randomTile.nextInt(dimensions*dimensions));
			rotateRectangle();
		}
	}
	
	public void clear() // Deselects all tiles
	{
		checkInitialization();
		for(int i = 0; i < dimensions; i++)
		{
			for(int j = 0; j < dimensions; j++)
			{
				tileGrid[i][j].select(false);
			}
		}
	}
	
	public String toString()
	{
		checkInitialization();
		String returnVal = "";
		for(int i = 0; i < dimensions; i++)
		{
			for(int j = 0; j < dimensions; j++)
			{
				returnVal = returnVal+tileGrid[i][j].getValue()+","+tileGrid[i][j].isSelected()+","+tileGrid[i][j].getOrientation();
				if(j == dimensions-1)
				{
					returnVal+="\n";
				}
				else
				{
					returnVal+="\t";
				}
			}
		}
		return returnVal;
	}
	
	// Helper Functions
	private void checkInitialization()
	{
		if(!initialized)
			throw new SecurityException("Grid was not properly initialized");
	}
	private int coord1(int tile) // Returns the first coordinate of a given position in tileGrid.
	{
		return tile/dimensions;
	}
	private int coord2(int tile) // Returns the first coordinate of a given position in tileGrid.
	{
		return tile%dimensions;
	}
	private Tile<T> getTile(int tile)
	{
		checkInitialization();
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
	public void selectTile(int mouseX, int mouseY)
	{
		int tileWidth = this.getWidth()/dimensions;
		int tileHeight = this.getHeight()/dimensions;
		int gridX = mouseX/tileWidth;
		int gridY = mouseY/tileHeight;
		selectTile(tileGrid[gridY][gridX]);
	}
	public void selectTile(Tile<T> tile) // Originally returned the tile found at the given position in tileGrid; now, sets that tile's selected value to the boolean parameter.
	{
		checkInitialization();
		tile.select(true);
		if(numberSelected == 1)
		{
			fillInRectangle(findTopLeftSelectedTile(),findBottomRightSelectedTile());
		}
		else if(numberSelected == 0)
		{
			numberSelected++;
		}
		else
		{
			numberSelected = 0;
			clear();
		}
	}
	public boolean tileIsSelected(int tile)
	{
		return getTile(tile).isSelected();
	}
	public Tile<T>[][] getGrid()
	{
		return tileGrid;
	}
	
}
