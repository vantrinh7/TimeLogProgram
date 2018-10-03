
/**
 * This class extends DefaultCellEditor, and checks if the times user enters are in the right format
 * @author vantrinh
 */
import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import java.awt.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeCellEditor extends DefaultCellEditor {
	private static final long serialVersionUID = 1L;

	private JFormattedTextField ftf;
	private SimpleDateFormat dateFormat;
	private String theText;

	public TimeCellEditor(String text) {
		super(new JFormattedTextField());
		theText = text;
		ftf = (JFormattedTextField) getComponent();

		// Set up the editor for the date cells
		dateFormat = new SimpleDateFormat("h:mm a");
		DateFormatter dateFormatter = new DateFormatter(dateFormat);
		Date date = new Date();
		try {
			date = dateFormat.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ftf.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
		ftf.setValue(date);
		ftf.setHorizontalAlignment(JTextField.TRAILING);
		ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);
	}

	/**
	 * This method provides the cell editor component
	 * 
	 * @param table
	 *            the JTable that is asking the editor to edit; can be null
	 * @param value
	 *            the value of the cell to be edited
	 * @param isSelected
	 *            true if the cell is to be rendered with highlighting
	 * @param row
	 *            the row of the cell being edited
	 * @param column
	 *            the column of the cell being edited
	 * @return
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		JTextField editor = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
		String newDate = new String();
		dateFormat = new SimpleDateFormat("h:mm a");
		try {
			Date originalDate = dateFormat.parse(theText);
			newDate = dateFormat.format(originalDate);
		} catch (ParseException ex) {
		}

		if (editor.getText() == null) {
			value = newDate;
			editor.setText(value.toString());
			editor.setHorizontalAlignment(SwingConstants.CENTER);
		} else {
			value = editor.getText();
		}
		return editor;
	}

	/**
	 * Lets the user know that the text they entered is bad. Returns true if the
	 * user elects to revert to the last good value. Otherwise, returns false,
	 * indicating that the user wants to continue editing.
	 */
	protected boolean userSaysRevert() {
		Toolkit.getDefaultToolkit().beep();
		ftf.selectAll();
		Object[] options = { "Edit", "Cancel" };
		int answer = JOptionPane.showOptionDialog(SwingUtilities.getWindowAncestor(ftf),
				"The value must be in time format, such as 8:00 AM or 7:30 PM. You can either continue editing or cancel.",
				"Invalid Text Entered", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options,
				options[1]);

		if (answer == 1) { // Revert!
			ftf.setValue(ftf.getValue());
			return true;
		}
		return false;
	}

	/**
	 * Override DefaultCellEditor to check whether the edit is valid, setting the
	 * value if it is and complaining if it isn't. If it's OK for the editor to go
	 * away, we need to invoke the superclass's version of this method so that
	 * everything gets cleaned up.
	 */
	public boolean stopCellEditing() {
		JFormattedTextField ftf = (JFormattedTextField) getComponent();
		if (ftf.isEditValid()) {
			try {
				ftf.commitEdit();
			} catch (java.text.ParseException exc) {
			}

		} else { // text is invalid
			if (ftf.getText().equals("")) { // user wants to delete
				ftf.setValue(null);
				return true;
			}

			if (!userSaysRevert()) { // user wants to edit
				return false; // don't let the editor go away
			}
		}
		return super.stopCellEditing();
	}
}
