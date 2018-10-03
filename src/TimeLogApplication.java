
/**
 * * This class has the main method, runs the program and provides JFrame for the program
 * @author Van Trinh
 *
 */
import javax.swing.JFrame;

public class TimeLogApplication {

	// The height of frame
	public static int FRAME_WIDTH = 700;

	// The width of frame
	public static int FRAME_HEIGHT = 800;

	/**
	 * This method creates JFrame that holds the panel for GUI view
	 * 
	 * @param args
	 *            argument
	 * @throws ParseException
	 */
	public static void main(String[] args) {

		// Create a new frame
		JFrame guiFrame = new JFrame("Time Log Program");

		// Set size of the frame
		guiFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

		// Add the panel
		guiFrame.add(new TimeLogPanel());

		// Exit normally on closing the window
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Show the frame
		guiFrame.setVisible(true);
	}
}
