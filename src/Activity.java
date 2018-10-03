/**
 * This class creates the element in the priority queue
 * 
 * @author van & sarah
 *
 */
public class Activity implements Comparable<Activity> {

	private double theHour;
	private String theActivity;
	private int thePriority;

	public Activity(double hour, String activity, int priority) {
		theHour = hour;
		theActivity = activity;
		thePriority = priority;
	}

	public double getTime() {
		return theHour;
	}

	public String getActivity() {
		return theActivity;
	}

	public int getPriority() {
		return thePriority;
	}

	@Override
	public int compareTo(Activity o) {
		if (o.thePriority == thePriority) {
			return 0;
		} else if (o.thePriority > thePriority) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * Returns the string representation of slide
	 */
	public String toString() {
		return "" + theHour;
	}

}
