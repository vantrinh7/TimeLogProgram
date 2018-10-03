
/**
 * Binary Heap extends Comparable class and implements Heap interface. This
 * class provides methods to create a binary heap, including: constructors, find
 * index of parent, left child, right child, get value at an index, swap values
 * of two indices, arrange array in a max heap order (maxHeapify), build a max
 * heap, check its size and set size, check its capacity and check whether it is
 * empty or not. Binary Heap is tested by BinaryHeapBasicTests.
 * 
 * Created by peter on 9/22/16. Modified by Van Trinh on 10/11/17
 */

public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {
	// Array to hold the heap
	private Comparable<T>[] internalArray;
	// Keep track of the heap size (different from the capacity)
	private int heapSize;
	// Capacity of heap
	private int capacity;

	/**
	 * Constructor of binary heap, takes in an array and is likely to be used for
	 * heap sort
	 * 
	 * @param array
	 *            array to be passed in
	 */
	public BinaryHeap(Comparable<T>[] array) {
		internalArray = array;
		// Make the array a heap
		this.heapSize = array.length;
		this.capacity = array.length;
	}

	/**
	 * Another constructor of binary heap, takes in an array and a specified size,
	 * is likely to be used for priority queue
	 * 
	 * @param n
	 *            capacity
	 * @param arr
	 *            array
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(int n, Comparable<T>[] arr) {
		// creates a BinaryHeap of capacity n
		// Starting with the elements in arr
		internalArray = new Comparable[n];
		System.arraycopy(arr, 0, internalArray, 0, arr.length);
		// Make the array a heap
		this.heapSize = arr.length;
		this.capacity = n;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#parent(int)
	 */
	public int parent(int i) {
		return (i - 1) / 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#leftChild(int)
	 */
	public int leftChild(int i) {
		return 2 * i + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#rightChild(int)
	 */
	public int rightChild(int i) {
		return 2 * i + 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#getElement(int)
	 */
	public Comparable<T> getElement(int index) {
		int found = 0;

		// Iterate through the heap, if index of array is equal the given index,
		// store value in 'found' variable, then return value at this index.
		for (int i = 0; i < internalArray.length; i++) {
			if (i == index) {
				found = index;
				return internalArray[found];
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#setElement(int, java.lang.Comparable)
	 */
	@Override
	public void setElement(int index, Comparable<T> element) {

		// Set value at the index to be value of element
		internalArray[index] = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#swap(int, int)
	 */
	public void swap(int index1, int index2) {

		// Create a temporary value to hold the value of first index
		Comparable<T> temp = internalArray[index1];
		// Assign value of second index to that of first index
		internalArray[index1] = internalArray[index2];
		// Assign temporary value to value of second index
		internalArray[index2] = temp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#maxHeapify(int)
	 */
	@SuppressWarnings("unchecked")
	public void maxHeapify(int i) {
		int l; // Index of left child
		int r; // Index of right child
		int largest; // Index of largest element

		// Loop through the elements that are not leaves, whose indices are always
		// smaller than half of heap size
		while (i < heapSize / 2) {
			// Get index of the left child
			l = leftChild(i);
			// Get index of the right child
			r = rightChild(i);

			// If left child is in the heap, compare value of left and parent
			// Assign the value of whichever is larger to 'largest' variable
			if (l < heapSize && internalArray[l].compareTo((T) internalArray[i]) > 0) {
				largest = l;
			} else {
				largest = i;
			}

			// If right child is in the heap, compare value of right child and the largest
			// If right child is larger than largest, assign its value to 'largest;
			if (r < heapSize && internalArray[r].compareTo((T) internalArray[largest]) > 0) {
				largest = r;
			}

			// If index with largest value is not i, swap them, and then assign largest to i
			if (largest != i) {
				swap(i, largest);
				i = largest;
			} else {
				return;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#buildMaxHeap()
	 */
	public void buildMaxHeap() {

		// Iterate through nodes that are not leaves all the way to the top
		// Making each subtree a max heap by calling maxHeapify
		for (int i = heapSize / 2; i >= 0; i--) {
			maxHeapify(i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#isEmpty()
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#size()
	 */
	public int size() {
		return heapSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#setSize(int)
	 */
	public void setSize(int size) {
		heapSize = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Heap#capacity()
	 */
	public int capacity() {
		return capacity;
	}
}
