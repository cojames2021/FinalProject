package spinPossible;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Tile<T> extends JPanel {
	private final T VALUE;
	private int orientation;
	private final int UPSIDE_DOWN = 1;
	private final int RIGHTSIDE_UP = 0;
	private boolean selected;
	private final Color DEFAULT_COLOR = new Color(220,220,220);
	private final Color SELECTED_COLOR = new Color(255,255,200);
	private JLabel valueText;
	
	public Tile(T value/*, int x, int y, int width, int height*/, Dimension size)
	{
		VALUE = value;
		orientation = RIGHTSIDE_UP;
		selected = false;
		/*this.setBounds(x,y,width,height);*/
		this.setLayout(new BorderLayout());
		valueText = new JLabel(value.toString());
		valueText.setHorizontalAlignment(JLabel.CENTER);
		valueText.setVerticalAlignment(JLabel.CENTER);
		valueText.setFont(valueText.getFont().deriveFont(60.0f));
		this.setBackground(DEFAULT_COLOR);
		setBorder(new LineBorder(Color.BLACK));
		this.add(valueText,BorderLayout.CENTER);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		
	}
	public void setTileVisible(boolean visible)
	{
		this.setVisible(visible);
		valueText.setVisible(visible);
	}
	public void select()
	{
		select(!selected);
	}
	public void select(boolean selected)
	{
		this.selected = selected;
		if(selected)
			setBackground(SELECTED_COLOR);
		else
			setBackground(DEFAULT_COLOR);
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
