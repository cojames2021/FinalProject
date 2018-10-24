package spinPossible;

public class Tile<T> {
	private final T VALUE;
	private int orientation = 0;
	private boolean selected  = false;
	
	public Tile(T value)
	{
		VALUE = value;
		
	}
	private void select()
	{
		selected = !selected;
	}
	private void changeOrientation()
	{
		if(orientation==0)
		{
			orientation=1;
		}
		else {
			orientation=0;
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
