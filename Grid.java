package spinPossible;

public class Grid {
	private int numberOfTiles;
	private int dimensions;
	private Tile[][] tileGrid;
	private boolean[][] selectedTiles;
	public int selectedCounter;
	
	public Grid(int dimensions)
	{
		tileGrid = new Tile[dimensions][dimensions];
		selectedTiles = new boolean[dimensions][dimensions];
		this.dimensions = dimensions;
		selectedCounter = 0;
		numberOfTiles = 0;
	}
	public void switchTiles(int a, int b) {			// Previously named "changePosition"
		
	}
	
	private void fillInRectangle(int a, int b)
	{
		
	}
	
	public void addTile(Tile a)
	{
		
	}
	
	private void randomize()
	{
		
	}
	
	public void clear()
	{
		
	}
}
