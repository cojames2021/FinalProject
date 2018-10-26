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
	public void select()
	{
		selected = !selected;
	}
	public void select(boolean selected)
	{
		this.selected = selected;
	}
	public void changeOrientation()
	{
		if(orientation==RIGHTSIDE_UP)
		{
			orientation=UPSIDE_DOWN;
		}
		else {
			orientation=RIGHTSIDE_UP;
		}
	}
	public boolean isSelected()
	{
		return selected;
	}
	public int getOrientation() {
		return orientation;
	}
}
