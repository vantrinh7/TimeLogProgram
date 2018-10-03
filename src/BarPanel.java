import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * This class draws horizontal stacked bar chart
 * 
 * @author SarahCaggiano
 *
 */
public class BarPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private double[] values;
	private Color[] colors;
	private String title;

	double percent = 0;

	public BarPanel(double[] values, Color[] colors, String title) {
		this.values = values;
		this.colors = colors;
		this.title = title;
	}

	@Override
	protected void paintComponent(Graphics g) {
		int width = getSize().width;
		int height = getSize().height;
		int x = 50;
		int y = height / 2;
		int barHeight = 100;
		int totalBarWidth = width - 100;
		double total = 0;

		for (int i = 0; i < values.length; i++) {
			total = total + values[i];
		}

		for (int i = 0; i < values.length; i++) {
			g.setColor(colors[i]);
			Double val = values[i];
			int barWidth = (int) ((val / total) * totalBarWidth);
			g.fillRect(x, y, barWidth, barHeight);
			g.setColor(Color.black);
			g.drawRect(x, y, barWidth, barHeight);
			x = x + barWidth;
		}

		// Set fonts of title and labels
		Font titleFont = new Font("SansSerif", Font.BOLD, 25);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);

		// Set title constraints
		int titleWidth = titleFontMetrics.stringWidth(title);
		int yTitle = titleFontMetrics.getAscent();
		int xTitle = (width - titleWidth) / 2;
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString(title, xTitle, yTitle);
	}
}
