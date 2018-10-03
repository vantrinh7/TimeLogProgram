
/**
 * This class creates GUI view and handle events on the GUI
 * @author vantrinh
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;
import java.awt.event.*;
import java.text.ParseException;

public class TimeLogPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_NUM = 4;
	private static final String DEFAULT_TIME = "12:00 AM";
	private static final int PQ_CAPACITY = 100;
	private int panelWidth = 671;
	private int panelHeight = 637;
	private int rowNum = 11;
	private int selected;
	private JTable table;
	private JPanel tablePanel;
	private JRadioButton pieBtn;
	private JRadioButton barBtn;
	private JRadioButton stackedBtn;
	private String pieString;
	private String barString;
	private String stackedString;
	private JFrame optionFrame;
	private JButton okBtn;
	private JButton cancelBtn;
	private PriorityQueueBH<Activity> pq1;
	private PriorityQueueBH<Activity> pq2;
	private PriorityQueueBH<Activity> pq3;

	/**
	 * Constructor, initiate GUI and create priority queues
	 */
	public TimeLogPanel() {
		super();
		iniGUI();
		pq1 = new PriorityQueueBH<Activity>(PQ_CAPACITY);
		pq2 = new PriorityQueueBH<Activity>(PQ_CAPACITY);
		pq3 = new PriorityQueueBH<Activity>(PQ_CAPACITY);
	}

	/**
	 * Method to initiate the GUI and add panels to north, east and center
	 * 
	 * @throws ParseException
	 */
	public void iniGUI() {
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 20));
		add(iniNorthPanel(), BorderLayout.NORTH);
		add(iniCenterPanel(), BorderLayout.CENTER);
		add(iniSouthPanel(), BorderLayout.SOUTH);
	}

	/**
	 * Create north panel including welcome labels of the program
	 * 
	 * @return north panel
	 */
	public JPanel iniNorthPanel() {
		JTextField field1 = new JTextField("Welcome to PaperClip Program");
		field1.setForeground(new Color(185, 30, 75));
		field1.setFont(new Font("Calibri", Font.PLAIN, 40));
		field1.setEditable(false);
		field1.setHorizontalAlignment(JTextField.CENTER);

		JTextField field2 = new JTextField("Keep It Together and Get Priorities Straight since 2017");
		field2.setForeground(new Color(190, 35, 145));
		field2.setFont(new Font("Calibri", Font.PLAIN, 18));
		field2.setEditable(false);
		field2.setHorizontalAlignment(JTextField.CENTER);

		JTextField field3 = new JTextField("Record activity time, content and priority level to get started");
		field3.setFont(new Font("Calibri", Font.PLAIN, 11));
		field3.setEditable(false);
		field3.setHorizontalAlignment(JTextField.LEFT);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 0, -13));
		panel.add(field1);
		panel.add(field2);
		panel.add(field3);
		return panel;
	}

	/**
	 * Create center panel of the program with table
	 * 
	 * @return center panel
	 */
	public JPanel iniCenterPanel() {
		table = new JTable(tableModel());
		if (panelHeight / rowNum >= 1) {
			table.setRowHeight(panelHeight / rowNum);
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(panelWidth / 5);
		table.getColumnModel().getColumn(1).setPreferredWidth(panelWidth / 5);
		table.getColumnModel().getColumn(2).setPreferredWidth(panelWidth / 2);
		table.getColumnModel().getColumn(3).setPreferredWidth(panelWidth / 10);

		formatTable(table);
		JScrollPane scrollPane = new JScrollPane(table);

		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				panelHeight = tablePanel.getHeight();
				panelWidth = tablePanel.getWidth();
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});

		tablePanel.add(scrollPane);
		revalidate();
		repaint();
		return tablePanel;
	}

	/**
	 * Override table model to have column names
	 * 
	 * @return table model
	 */
	public DefaultTableModel tableModel() {
		DefaultTableModel model = new DefaultTableModel(rowNum, COLUMN_NUM) {

			private static final long serialVersionUID = 1L;
			String[] columnNames = { "Start Time", "End Time", "Activity", "Priority" };

			@Override
			public String getColumnName(int index) {
				return columnNames[index];
			}

		};
		return model;
	}

	/**
	 * Set the format of the table
	 * 
	 * @param table
	 *            table to be formatted
	 */
	public void formatTable(JTable table) {
		// Set grid border to black, background to white, foreground (text color) to
		// black and set an etched border
		table.setShowGrid(true);
		table.setGridColor(Color.BLACK);
		table.setSelectionBackground(Color.WHITE);
		table.setSelectionForeground(Color.BLACK);
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		JTextField text = new JTextField();

		table.getColumnModel().getColumn(0).setCellEditor(new TimeCellEditor(DEFAULT_TIME));
		table.getColumnModel().getColumn(1).setCellEditor(new TimeCellEditor(DEFAULT_TIME));
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(text));

		// Set the priority column to a JComboBox with priority options
		int[] list = { 1, 2, 3, 4, 5 };
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.addItem(null);
		for (int i = 0; i < list.length; i++) {
			comboBox.addItem(list[i]);
		}
		TableColumn priority = table.getColumnModel().getColumn(3);
		priority.setCellEditor(new DefaultCellEditor(comboBox));

	}

	/**
	 * Create south panel including buttons
	 * 
	 * @return south panel
	 */
	public JPanel iniSouthPanel() {
		JPanel south = new JPanel();
		south.add(iniClearButton());
		south.add(iniAnalyzeButton());
		return south;
	}

	/**
	 * Initiate analyze buttons and invoke events to analyze
	 * 
	 * @return analyze buttons
	 */
	public JButton iniAnalyzeButton() {
		JButton analyze = new JButton("Analyze");
		analyze.setPreferredSize(new Dimension(200, 70));
		analyze.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				analyzeData();
				createChartOptionDialog();
				addActionListenerToRadioButtons();
				addActionListenerToDialogButtons();
			}
		});
		return analyze;
	}

	/**
	 * Method to analyze data given by the user, and add to priority queue
	 */
	public void analyzeData() {
		double activityHour = 0;
		String activity = "";
		int priority = 0;

		for (int i = 0; i < rowNum; i++) {
			if ((table.getValueAt(i, 0) != null) && (table.getValueAt(i, 1) != null) && (table.getValueAt(i, 2) != null)
					&& (table.getValueAt(i, 3) != null)) {
				if (getActivityTime(i) != -1.0) {
					activityHour = getActivityTime(i);
					activity = (String) table.getValueAt(i, 2);
					priority = (int) table.getValueAt(i, 3);
					Activity newItem = new Activity(activityHour, activity, priority);
					pq1.insert(newItem);
					pq2.insert(newItem);
					pq3.insert(newItem);
				}
			}
		}
		table.revalidate();
	}

	/**
	 * Parse the string of start and end time to extract the numbers and period of
	 * the day
	 * 
	 * @param rowNum
	 *            the row number
	 * @return the time of the activity
	 */
	public double getActivityTime(int rowNum) {
		double startHour = 0;
		double startMin = 0;
		String startPeriod = "";
		double endHour = 0;
		double endMin = 0;
		String endPeriod = "";
		if ((table.getValueAt(rowNum, 0) != null) && (table.getValueAt(rowNum, 1) != null)) {

			// Get the start time
			String start = (String) table.getValueAt(rowNum, 0);
			String[] parts = start.split(":");
			String hour = parts[0]; // The hour
			startHour = Integer.parseInt(hour.replaceAll("[^0-9]", ""));

			String minutes = parts[1]; // The minutes and day period
			String[] parts2 = minutes.split(" ");
			minutes = parts2[0]; // The minutes
			startMin = Integer.parseInt(minutes.replaceAll("[^0-9]", ""));
			startPeriod = parts2[1]; // The period
			if (startPeriod.equals("AM") || startPeriod.equals("am") || startPeriod.equals("aM")
					|| startPeriod.equals("Am")) {
				startPeriod = "AM";
			} else if (startPeriod.equals("PM") || startPeriod.equals("pm") || startPeriod.equals("pM")
					|| startPeriod.equals("Pm")) {
				startPeriod = "PM";
			}

			// Get the end time
			String end = (String) table.getValueAt(rowNum, 1);
			String[] parts3 = end.split(":");
			String hour2 = parts3[0]; // The hour
			endHour = Integer.parseInt(hour2.replaceAll("[^0-9]", ""));

			String minutes2 = parts3[1]; // The minutes and day period
			String[] parts4 = minutes2.split(" ");
			minutes2 = parts4[0]; // The minutes
			endMin = Integer.parseInt(minutes2.replaceAll("[^0-9]", ""));
			endPeriod = parts4[1]; // The period
			if (endPeriod.equals("AM") || endPeriod.equals("am") || endPeriod.equals("aM") || endPeriod.equals("Am")) {
				endPeriod = "AM";
			} else if (endPeriod.equals("PM") || endPeriod.equals("pm") || endPeriod.equals("pM")
					|| endPeriod.equals("Pm")) {
				endPeriod = "PM";
			}
			return calculateTime(startHour, startMin, startPeriod, endHour, endMin, endPeriod);
		}
		return -1;
	}

	/**
	 * Calculate the time of an activity
	 * 
	 * @param startHour
	 *            the hour of start time
	 * @param startMin
	 *            the minutes of start time
	 * @param startPeriod
	 *            the period of start time (AM or PM)
	 * @param endHour
	 *            the hour of end time
	 * @param endMin
	 *            the minutes of end time
	 * @param endPeriod
	 *            the period of end time (AM or PM)
	 * @return the time of the activity
	 */
	public double calculateTime(double startHour, double startMin, String startPeriod, double endHour, double endMin,
			String endPeriod) {
		double time = 0;
		if (startPeriod.equals(endPeriod)) {
			if (startHour == 12) {
				startHour = 0;
			}
			time = (endHour - startHour) + (endMin - startMin) / 60;
		} else {
			if (endHour == 12) {
				time = (endHour - startHour) + (endMin - startMin) / 60;
			} else {
				time = (endHour + 12 - startHour) + (endMin - startMin) / 60;
			}
		}
		return time;
	}

	/**
	 * Create the dialog box that shows different options for charts
	 */
	public void createChartOptionDialog() {
		optionFrame = new JFrame("Choose Chart Option");
		optionFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				clearHeap();
			}
		});
		optionFrame.setSize(300, 300);
		optionFrame.setVisible(true);

		JPanel outerPanel = new JPanel(new BorderLayout());
		optionFrame.add(outerPanel);

		JPanel centerPanel = new JPanel(new GridLayout(0, 1, 5, 5));
		JPanel southPanel = new JPanel(new GridLayout(1, 2, 0, 10));
		outerPanel.add(centerPanel, BorderLayout.CENTER);
		outerPanel.add(southPanel, BorderLayout.SOUTH);

		pieString = "Show Pie Chart";
		pieBtn = new JRadioButton(pieString);
		pieBtn.setActionCommand(pieString);

		barString = "Show Bar Chart";
		barBtn = new JRadioButton(barString);
		barBtn.setActionCommand(barString);

		stackedString = "Show Horizontal Stacked Bar Chart";
		stackedBtn = new JRadioButton(stackedString);
		stackedBtn.setActionCommand(stackedString);

		ButtonGroup group = new ButtonGroup();
		group.add(pieBtn);
		group.add(barBtn);
		group.add(stackedBtn);

		centerPanel.add(pieBtn);
		centerPanel.add(barBtn);
		centerPanel.add(stackedBtn);

		okBtn = new JButton("OK");
		cancelBtn = new JButton("Cancel");
		southPanel.add(okBtn);
		southPanel.add(cancelBtn);
	}

	/**
	 * Add action listeners to radio buttons corresponding to each chart
	 */
	public void addActionListenerToRadioButtons() {
		pieBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals(pieString)) {
					selected = 1;
				}
			}

		});
		barBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals(barString)) {
					selected = 2;
				}
			}
		});
		stackedBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals(stackedString)) {
					selected = 3;
				}
			}
		});
	}

	/**
	 * Add action listeners to OK and Cancel buttons of dialog box
	 */
	public void addActionListenerToDialogButtons() {

		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected == 1) {
					new PieApp(pq1);
				} else if (selected == 2) {
					new ChartApp(pq2);
				} else {
					new BarApp(pq3);
				}
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				optionFrame.dispose();
				clearHeap();
			}
		});
		repaint();
		revalidate();
	}

	/**
	 * Create and handle events of Clear button
	 * 
	 * @return clear button
	 */
	public JButton iniClearButton() {
		JButton clear = new JButton("Clear");
		clear.setPreferredSize(new Dimension(200, 70));
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
					for (int j = 0; j < table.getColumnCount(); j++) {
						table.setValueAt("", i, j);
					}
				}
				table.revalidate();
				clearHeap();
			}
		});
		return clear;
	}

	/**
	 * Reset the priority queues
	 */
	public void clearHeap() {
		pq1 = new PriorityQueueBH<Activity>(PQ_CAPACITY);
		pq2 = new PriorityQueueBH<Activity>(PQ_CAPACITY);
		pq3 = new PriorityQueueBH<Activity>(PQ_CAPACITY);
	}
}
