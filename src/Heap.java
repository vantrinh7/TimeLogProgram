/**
 * An interface that defines method to be specified in a heap Created by peter
 * on 9/22/16. Modified by Van trinh on 10/11/17
 */
public interface Heap<T extends Comparable<T>> {

	/**
	 * Returns index of the parent of index i, using the observation that the index
	 * of parent is half of i-1
	 * 
	 * @param i
	 *            the node whose parent index is to be found
	 * @return index of the parent of index i
	 */
	public int parent(int i);

	/**
	 * Returns index of the left child of index i, using the observation that it is
	 * two times i adding one
	 * 
	 * @param i
	 *            the node whose left child index is to be found
	 * @return index of the left child of index i
	 */
	public int leftChild(int i);

	/**
	 * Returns index of the right child of index i, using the observation that it is
	 * two times i adding two
	 * 
	 * @param i
	 *            the node whose right child index is to be found
	 * @return index of the right child of index i
	 */
	public int rightChild(int i);

	/**
	 * Swap values of two indices in the array
	 * 
	 * @param index1
	 *            first index
	 * @param index2
	 *            second index
	 */
	public void swap(int index1, int index2);

	/**
	 * Arranges the array so that it has the property of a max heap. This method
	 * holds the assumption that left(i) and right(i) are proper max heaps, but
	 * array(i) may be smaller than its children. Thus, maxHeapify lets array(i)
	 * floats down below so that the heap rooted in array(i) could become max heap,
	 * by comparing with its children to find out which is the largest value.
	 * 
	 * @param i
	 *            index to start comparing
	 */
	public void maxHeapify(int i);

	/**
	 * Iterates through all nodes that are not leaves, calls maxHeapify on them so
	 * that we end up with the whole tree having max heap property
	 */
	public void buildMaxHeap();

	/**
	 * Returns value at an index
	 * 
	 * @param i
	 *            index to look into
	 * @return value at an index
	 */
	public Comparable<T> getElement(int i);

	/**
	 * Sets value at a certain index to be value of element
	 * 
	 * @param index
	 *            the index to be modified
	 * @param element
	 *            the element to be added
	 */
	public void setElement(int index, Comparable<T> element);

	/**
	 * Checks whether heap is empty
	 * 
	 * @return true if heap is empty
	 */
	public boolean isEmpty();

	/**
	 * Returns number of elements of the heap we are considering
	 * 
	 * @return number of elements of the heap
	 */
	public int size();

	/**
	 * Changes size of the heap
	 * 
	 * @param size
	 *            the desired size to change to
	 */
	public void setSize(int size);

	/**
	 * Returns number of elements of array, or the array length
	 * 
	 * @return the array length
	 */
	public int capacity();

}
