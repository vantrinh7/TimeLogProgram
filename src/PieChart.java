
/**
 * This class draws pie chart
 * @author sarah
 */

import java.awt.*;

import javax.swing.JPanel;

public class PieChart extends JPanel {

	private static final long serialVersionUID = 1L;

	private double[] values;
	private Color[] colors;
	private String title;

	double percent = 0;

	public PieChart(double[] values, Color[] colors, String title) {
		this.values = values;
		this.colors = colors;
		this.title = title;
	}

	@Override
	protected void paintComponent(Graphics g) {
		int width = getSize().width;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int lastPoint = -270;
		double total = 0;

		for (int i = 0; i < values.length; i++) {
			total = (total + values[i]);
		}

		for (int i = 0; i < values.length; i++) {
			g2d.setColor(colors[i]);
			Double val = values[i];
			int angle = (int) ((val / total) * 360);
			g2d.fillArc(0, 40, width, width, lastPoint, -angle);
			lastPoint = lastPoint + -angle;
		}

		Dimension d = getSize();
		int clientWidth = d.width;

		// Set fonts of title and labels
		Font titleFont = new Font("SansSerif", Font.BOLD, 20);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);

		// Set title constraints
		int titleWidth = titleFontMetrics.stringWidth(title);
		int y = titleFontMetrics.getAscent();
		int x = (clientWidth - titleWidth) / 2;
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString(title, x, y);

	}
}