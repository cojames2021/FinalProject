package spinPossible;

public class Tile<T> {
	private final T VALUE;
	private int orientation = 0;
	private final int UPSIDE_DOWN = 1;
	private final int RIGHTSIDE_UP = 0;
	private boolean selected  = false;
	
	public Tile(T value)
	{
		VALUE = value;
		
	}
	private void select()
	{
		if(!selected)
		{
			selected = true;
		}
		else
		{
			selected = false;
		}
	}
	private void changeOrientation()
	{
		if(orientation==RIGHTSIDE_UP)
		{
			orientation=UPSIDE_DOWN;
		}
		else {
			orientation=RIGHTSIDE_UP;
		}
	}
	private boolean isSelected()
	{
		return selected;
	}
	private int getOrientation() {
		return orientation;
	}
}
