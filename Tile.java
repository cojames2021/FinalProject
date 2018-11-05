package spinPossible;

import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class Tile<T> extends Component {
	private final T VALUE;
	private int orientation;
	private final int UPSIDE_DOWN = 1;
	private final int RIGHTSIDE_UP = 0;
	private boolean selected;
	private Rectangle GUIShape;
	private JLabel GUIValue;
	
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
