import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class calculates and creates bar chart
 * 
 * @author sarah
 *
 */
public class ChartApp {

	public ChartApp(PriorityQueueBH<Activity> pqHeap) {

		int numPriorities = 5;

		// Array to hold times based on priority
		double[] times = new double[numPriorities];

		// Colors priorities
		Color[] colors = { new Color(255, 0, 0), new Color(200, 89, 0), new Color(255, 255, 0), new Color(0, 255, 0),
				new Color(0, 0, 255) };

		// Array to hold activity string's by priority
		String[] names = { "Priority 1", "Priority 2", "Priority 3", "Priority 4", "Priority 5" };

		String[] labels = new String[numPriorities];

		// Initialize label
		for (int i = 0; i < labels.length; i++) {
			labels[i] = "";
		}

		// Hold initial size of heap in a variable for the for loop
		int size = pqHeap.size();

		// Fills the times and strings array depending on the priority of the activity
		for (int i = 0; i < size; i++) {

			// Get the activity with max priority
			Activity currActivity = (Activity) pqHeap.extractMax();

			// Assign that element's priority, time, and string to variables
			int currPriority = currActivity.getPriority();
			double currTime = currActivity.getTime();
			String currLabel = currActivity.getActivity();

			// Add activity's times to the times array
			times[currPriority - 1] = times[currPriority - 1] + currTime;

			// Add activity's strings to the strings array
			labels[currPriority - 1] = labels[currPriority - 1] + " - " + currLabel;
		}

		// Initializes the text fields and sets size
		JTextField priority1 = new JTextField(20);
		JTextField priority2 = new JTextField(20);
		JTextField priority3 = new JTextField(20);
		JTextField priority4 = new JTextField(20);
		JTextField priority5 = new JTextField(20);

		// Sets text fields so they can't be edited
		priority1.setEditable(false);
		priority2.setEditable(false);
		priority3.setEditable(false);
		priority4.setEditable(false);
		priority5.setEditable(false);

		// Sets the text of the text fields to be the activities
		priority1.setText("Priority1 " + labels[0]);
		priority2.setText("Priority2 " + labels[1]);
		priority3.setText("Priority3 " + labels[2]);
		priority4.setText("Priority4 " + labels[3]);
		priority5.setText("Priority5 " + labels[4]);

		// Sets the color of the text to be coordinated with the appropriate priority
		priority1.setForeground(colors[0]);
		priority2.setForeground(colors[1]);
		priority3.setForeground(colors[2]);
		priority4.setForeground(colors[3]);
		priority5.setForeground(colors[4]);

		// Initializes the font
		Font font1 = new Font("SansSerif", Font.BOLD, 17);
		// Sets the font of each text field
		priority1.setFont(font1);
		priority2.setFont(font1);
		priority3.setFont(font1);
		priority4.setFont(font1);
		priority5.setFont(font1);

		// Initializes the panel to hold the text field
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(5, 1, 0, 0));
		// Adds the text fields to the panel
		labelPanel.add(priority1);
		labelPanel.add(priority2);
		labelPanel.add(priority3);
		labelPanel.add(priority4);
		labelPanel.add(priority5);

		// Initialize JFrame
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		ChartPanel barChart = new ChartPanel(times, names, colors, "Priority Bar Chart");

		frame.getContentPane().add(barChart, BorderLayout.CENTER);
		frame.getContentPane().add(labelPanel, BorderLayout.EAST);

		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}
