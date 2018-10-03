/**
 * PriorityQueueBH extends Comparable class and implements PriorityQueue
 * interface. This class provides methods to create a binary heap, including:
 * constructors, maximum, extract maximum, increase value and insert new item.
 * PriorityQueueBH is tested by PriorityQueuBHTest.
 * 
 * @author Van Trinh
 * @version 10/25/17
 *
 * @param <T>
 *            generic type
 */
public class PriorityQueueBH<T extends Comparable<T>> implements PriorityQueue<T> {

	// Create a BinaryHeap varible that is a priority queue
	protected BinaryHeap<T> pqHeap;

	// Count the number of items extracted
	protected int extracted = 0;

	/**
	 * A constructor to create a priority queue that takes in capacity as a
	 * parameter
	 * 
	 * @param n
	 *            capacity of a priority queue
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueueBH(int n) {

		// Create a new heap with given capacity and an empty array
		pqHeap = new BinaryHeap<T>(n, new Comparable[0]);
	}

	/**
	 * A constructor to create priority queue that takes in an array as a parameter
	 * 
	 * @param array
	 *            array to be used
	 */
	public PriorityQueueBH(Comparable<T>[] array) {

		// Create a new heap with given array
		pqHeap = new BinaryHeap<T>(array);

		// Make it a max heap
		pqHeap.buildMaxHeap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PriorityQueue#maximum()
	 */
	@Override
	public Comparable<T> maximum() {

		// Return the first element in the heap
		return pqHeap.getElement(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PriorityQueue#extractMax()
	 */
	@Override
	public Comparable<T> extractMax() {

		// Create a variable for maximum value
		Comparable<T> max;

		// If heap size is larger than 0, remove and return first element
		if (pqHeap.size() > 0) {

			// Assign first (maximum) element to max variable
			max = pqHeap.getElement(0);

			// Take the last element of heap, put on top
			pqHeap.swap(0, pqHeap.size() - 1);

			// Set the element thrown away to be null
			pqHeap.setElement(pqHeap.size() - 1, null);

			// Decrease the heap size
			pqHeap.setSize(pqHeap.size() - 1);

			// Heapify to ensure max heap property
			pqHeap.maxHeapify(0);

			// Increment number of extracted items
			extracted++;

			return max;

		} else {
			throw new java.lang.Error("Heap size is equal or smaller than 0, heap underflow");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PriorityQueue#increaseValue(int, java.lang.Comparable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void increaseValue(int index, Comparable<T> element) {

		// If element at index is null or element to be added is larger than or equal to
		// current element at the index, put the new element into this index
		if ((pqHeap.getElement(index) == null) || (element.compareTo((T) pqHeap.getElement(index)) >= 0)) {

			// Set value at index to be value at element
			pqHeap.setElement(index, element);

			// Then, if index > 0 and value at index is larger than that of its parent
			while (index > 0 && pqHeap.getElement(index).compareTo((T) pqHeap.getElement(pqHeap.parent(index))) > 0) {

				// Swap them
				pqHeap.swap(index, pqHeap.parent(index));

				// Assign index of parent to index variable
				index = pqHeap.parent(index);
			}
		} else {
			// Else if value is not null, and element to be added is smaller, throw an error
			throw new java.lang.Error("New value is smaller than current value");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PriorityQueue#insert(java.lang.Comparable)
	 */
	@Override
	public void insert(Comparable<T> element) {

		if (pqHeap.size() == 0) {

			// Increase the heap size by 1
			pqHeap.setSize(pqHeap.size() + 1);

			// If heap is empty, add to the beginning
			pqHeap.setElement(0, element);
		}

		// If heap size is smaller than capacity, insert the element
		else if (size() != 0 && (pqHeap.size() < pqHeap.capacity())) {

			// Increase the heap size by 1
			pqHeap.setSize(pqHeap.size() + 1);

			// Set value of new node (the last node) to be value of element and increase
			// value if needed
			increaseValue(pqHeap.size() - 1, element);

		} else {

			// If heap size has reached the capacity limit, throw an error
			throw new java.lang.Error("Heap underlying storage is overflow");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PriorityQueue#getElement(int)
	 */
	@Override
	public Comparable<T> getElement(int index) {

		// Return element at the given index
		return pqHeap.getElement(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PriorityQueue#afterMaximum()
	 */
	@SuppressWarnings("unchecked")
	public Comparable<T> afterMaximum() {

		// If element 2 is null, check element 1
		if (pqHeap.getElement(2) == null) {

			// If element 1 is null, return null, or else return element 1
			if (pqHeap.getElement(1) == null) {
				return null;
			} else {
				return pqHeap.getElement(1);
			}
		}

		// If element 1 is larger than element 2, return element 2
		else if (pqHeap.getElement(1).compareTo((T) pqHeap.getElement(2)) < 0) {
			return pqHeap.getElement(2);
		}

		// If element 1 is smaller than element 2, return element 1
		else {
			return pqHeap.getElement(1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PriorityQueue#isExtracted()
	 */
	public boolean isExtracted() {
		if (extracted == 0) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PriorityQueue#size()
	 */
	@Override
	public int size() {
		return pqHeap.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PriorityQueue#capacity()
	 */
	public int capacity() {
		return pqHeap.capacity();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < pqHeap.size(); i++) {
			s = s + pqHeap.getElement(i).toString() + " ";
		}
		return s;
	}
}
