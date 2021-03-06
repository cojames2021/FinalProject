package spinPossible;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Tile<T> extends JPanel {
	private final T VALUE;
	private int orientation;
	public static final int UPSIDE_DOWN = 1;
	public static final int RIGHTSIDE_UP = 0;
	private boolean selected;
	private final Color DEFAULT_COLOR = new Color(220,220,220);
	private final Color SELECTED_COLOR = new Color(255,255,200);
	private JLabel valueText;
	
	public Tile(T value, Dimension size)
	{
		VALUE = value;
		orientation = RIGHTSIDE_UP;
		selected = false;
		this.setLayout(new BorderLayout());
		valueText = new JLabel(value.toString());
		valueText.setHorizontalAlignment(JLabel.CENTER);
		valueText.setVerticalAlignment(JLabel.CENTER);
		Map<TextAttribute, Integer> underlineAttribute = new HashMap<TextAttribute, Integer>(); // Underlined text. Source: https://stackoverflow.com/questions/325840/what-is-the-constant-value-of-the-underline-font-in-java
		underlineAttribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		valueText.setFont(valueText.getFont().deriveFont(60.0f).deriveFont(underlineAttribute));
		valueText.setSize(50, 50);;
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
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		if(orientation == UPSIDE_DOWN)
		{
			g2d.rotate(Math.toRadians(180),getWidth()/2,getHeight()/2);

		}
		super.paintComponent(g2d);
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
