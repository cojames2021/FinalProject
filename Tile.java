package spinPossible;

public class Tile<T> {
	private final T VALUE;
	private int orientation;
	private final int UPSIDE_DOWN = 1;
	private final int RIGHTSIDE_UP = 0;
	private boolean selected;
	
	public Tile(T value)
	{
		VALUE = value;
		orientation = RIGHTSIDE_UP;
		selected = false;
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
	public T getValue()
	{
		return VALUE;
	}
}
