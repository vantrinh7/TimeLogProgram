/**
 * An interface that defines method to be specified in a priority queue
 * 
 * @author Van Trinh
 * @version 10/25/17
 */

public interface PriorityQueue<T extends Comparable<T>> {

	/**
	 * This method peeks at the maximum and returns the maximum value in the
	 * priority queue
	 * 
	 * @return maximum value
	 */
	public Comparable<T> maximum();

	/**
	 * This method removes and returns the maximum element
	 * 
	 * @return maximum element
	 */
	public Comparable<T> extractMax();

	/**
	 * This method sets the element at specified index to new elements. It fixes
	 * heap (through swaps) to move element to the correct position, by comparing
	 * values, so that the queue maintains max heap property
	 * 
	 * @param index
	 *            the specified index
	 * @param element
	 *            the element to be increased to
	 */
	public void increaseValue(int index, Comparable<T> element);

	/**
	 * This method inserts a new element and arrange the tree to keep max heap
	 * property
	 * 
	 * @param element
	 *            element to be inserted
	 */
	public void insert(Comparable<T> element);

	/**
	 * Method to get element at a certain index
	 * 
	 * @param index
	 *            the index to be considered
	 * @return element at this index
	 */
	public Comparable<T> getElement(int index);

	/**
	 * Returns the second largest element
	 * 
	 * @return element after the maximum
	 */
	public Comparable<T> afterMaximum();

	/**
	 * Checks whether the queue is extracted yet or not
	 * 
	 * @return true or false
	 */
	public boolean isExtracted();

	/**
	 * Returns size of the priority queue
	 * 
	 * @return size
	 */
	public int size();

	/**
	 * Returns the capacity of the queue
	 * 
	 * @return capacity
	 */
	public int capacity();

	/**
	 * Returns string representation of the queue
	 * 
	 * @return list of items in order
	 */
	public String toString();
}
