
/**
 * This class draws bar chart
 * @author sarah
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ChartPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// Array to hold the time values
	private double[] values;
	// Array to hold the activities or priority??
	private String[] names;
	// Array to hold colors
	private Color[] colors;
	// Title of the whole thing
	private String title;

	// Constructor for chart panel
	public ChartPanel(double[] v, String[] n, Color[] c, String t) {
		names = n;
		values = v;
		colors = c;
		title = t;
	}

	// Creates the actual chart
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Return if there are no values
		if (values == null || values.length == 0) {
			return;
		}
		// Initialize min and max values
		double minValue = 0;
		double maxValue = 0;

		// Iterate through the values array and find the minimum and maximum values
		for (int i = 0; i < values.length; i++) {
			if (minValue > values[i])
				minValue = values[i];
			if (maxValue < values[i])
				maxValue = values[i];
		}

		// Get the size of the frame? and set height and width
		Dimension d = getSize();
		int clientWidth = d.width;
		int clientHeight = d.height;
		// Set width of bars to be width of pane divided by number of values
		int barWidth = clientWidth / values.length;

		// Set fonts of title and labels
		Font titleFont = new Font("SansSerif", Font.BOLD, 30);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
		Font labelFont = new Font("SansSerif", Font.PLAIN, 20);
		FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

		// Set title constraints
		int titleWidth = titleFontMetrics.stringWidth(title);
		int y = titleFontMetrics.getAscent();
		int x = (clientWidth - titleWidth) / 2;
		g.setFont(titleFont);
		g.drawString(title, x, y);

		int top = titleFontMetrics.getHeight();
		int bottom = labelFontMetrics.getHeight();
		if (maxValue == minValue)
			return;
		double scale = (clientHeight - top - bottom) / (maxValue - minValue);
		y = clientHeight - labelFontMetrics.getDescent();
		g.setFont(labelFont);

		for (int i = 0; i < values.length; i++) {
			int valueX = i * barWidth + 1;
			int valueY = top;
			double height = (values[i] * scale);
			if (values[i] >= 0)
				valueY += ((maxValue - values[i]) * scale);
			else {
				valueY += (maxValue * scale);
				height = -height;
			}

			// Set color to appropriate color from color array
			g.setColor(colors[i]);
			g.fillRect(valueX, valueY, barWidth - 2, (int) height);
			// Set border color
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, barWidth - 2, (int) height);
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);
		}
	}
}
