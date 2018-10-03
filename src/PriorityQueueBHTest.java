
/**
 * Tests for PriorityQueueBH class
 * @author Van Trinh
 */

import static org.junit.Assert.*;
import org.junit.*;

public class PriorityQueueBHTest {

	// Variable for an empty priority queue
	protected static PriorityQueueBH<Integer> emptyPQ;

	// Variable for a filled priority queue
	protected static PriorityQueueBH<Integer> pq;

	@BeforeClass
	public static void init() {

		// Create empty priority queue with arbitrary capacity
		emptyPQ = new PriorityQueueBH<Integer>(10);

		// Create a filled priority queue
		Integer[] array = new Integer[] { 2, 1, 3, 10, 15, 8 };
		pq = new PriorityQueueBH<Integer>(array);
	}

	@AfterClass
	public static void tearDown() {

		// Clean up after all tests are done
		emptyPQ = null;
		pq = null;
	}

	@Test
	public void testConstructor() {
		// Test 1: Test constructor that takes in capacity as the argument
		PriorityQueueBH<Integer> pq1 = new PriorityQueueBH<Integer>(1);
		assertEquals("Capacity should be 1", 1, pq1.capacity());
		assertEquals("First element should be null", null, pq1.getElement(0));
		assertEquals("Second element should be null", null, pq1.getElement(1));

		// Test 2: Test constructor that takes in an array as the argument
		// Case 1: Array with 0 element
		Integer[] array = new Integer[] {};
		PriorityQueueBH<Integer> pq2 = new PriorityQueueBH<Integer>(array);
		assertEquals("Capacity should be 0", 0, pq2.capacity());
		assertEquals("First element should be null", null, pq2.getElement(0));
		assertEquals("Second element should be null", null, pq2.getElement(1));

		// Case 2: Array with more than 0 element
		// Draw out the tree and track the algorithm, we can see our expected array
		// after building max heap indexing from 0 to 5 is as followed:
		// 15-10-8-2-1-3. Now we test each element in the queue.
		assertEquals("First element should be 15", 15, pq.getElement(0));
		assertEquals("Second element should be 10", 10, pq.getElement(1));
		assertEquals("Third element should be 8", 8, pq.getElement(2));
		assertEquals("Fourth element should be 2", 2, pq.getElement(3));
		assertEquals("Fifth element should be 1", 1, pq.getElement(4));
		assertEquals("Sixth element should be 3", 3, pq.getElement(5));
		assertEquals("Seventh element should be null", null, pq.getElement(10));
		assertEquals("Capacity should be 6", 6, pq.capacity());
	}

	@Test
	public void maximumTest() {

		// Expect the maximum value of filled priority queue to be 15
		assertEquals("Maximum should be 15", 15, pq.maximum());

		// Expect the maximum value of empty priority queue to be null
		assertEquals("Maximum should be null", null, emptyPQ.maximum());
	}

	@Test
	public void extractMaxTest() {

		// Create a priority queue to test extractMax()
		Integer[] array = new Integer[] { 2, 1, 3, 10, 15, 8 };
		PriorityQueueBH<Integer> pq2 = new PriorityQueueBH<Integer>(array);

		// Expect the maximum value of filled priority queue to be 15
		assertEquals("Maximum should be 15", 15, pq2.extractMax());

		// Expect that size is now decreased from 6 to 5
		assertEquals("Size after extract maximum should be 5", 5, pq2.size());

		// Expect new heap to have max heap property (since extractMax calls maxHeapify)
		// Draw out the tree and track the algorithm, we can see our expected array
		// after building max heap indexing from 0 to 4 is as followed:
		// 10-3-8-2-1.
		assertEquals("First element should be 10", 10, pq2.getElement(0));
		assertEquals("Second element should be 3", 3, pq2.getElement(1));
		assertEquals("Third element should be 8", 8, pq2.getElement(2));
		assertEquals("Fourth element should be 2", 2, pq2.getElement(3));
		assertEquals("Fifth element should be 1", 1, pq2.getElement(4));
		assertEquals("Sixth element should be null", null, pq2.getElement(5));
	}

	@Test(expected = Error.class)
	public void extractMaxTest2() {
		// Test if extractMax() throws an error when heap size is equal or smaller than
		// 0. If test is successful, extractMax() does throw an error when needed. Here,
		// we call extractMax() on an empty priority queue and see if test passes.
		emptyPQ.extractMax();
	}

	@Test
	public void increaseValueTest() {

		// Create a priority queue to test increaseValue()
		Integer[] array2 = new Integer[] { 2, 1, 3, 10, 15, 8 };
		PriorityQueueBH<Integer> pq3 = new PriorityQueueBH<Integer>(array2);

		// If new value is larger than current one at an index, such as value 27,
		// expect a replacement and increase of value
		pq3.increaseValue(3, 27);

		// Expect swapping to occur so that if child is larger than parent, swap them
		// (as in increaseValue() method). Draw out the tree and track the algorithm,
		// the expected value of index 0 to 5 is as followed: 27-15-8-10-1-3
		assertEquals("First element should be 27", 27, pq3.getElement(0));
		assertEquals("Second element should be 15", 15, pq3.getElement(1));
		assertEquals("Third element should be 8", 8, pq3.getElement(2));
		assertEquals("Fourth element should be 10", 10, pq3.getElement(3));
		assertEquals("Fifth element should be 1", 1, pq3.getElement(4));
		assertEquals("Sixth element should be 3", 3, pq3.getElement(5));
		assertEquals("Seventh element should be null", null, pq3.getElement(6));

	}

	@Test(expected = Error.class)
	public void increaseValueTest2() {
		// Test if increaseValue() throws an error when new value is smaller than
		// current one, such as value 4 is smaller than value 10 at index 1. If test
		// passes, increaseValue does actually throw an error
		pq.increaseValue(1, 4);
	}

	@Test
	public void insertTest() {

		// Create an empty priority queue
		PriorityQueueBH<Integer> emptyPQ2 = new PriorityQueueBH<Integer>(6);

		// Since priority queue is empty, first element is the element just inserted
		emptyPQ2.insert(2);

		assertEquals("Size should increase from 0 to 1", 1, emptyPQ2.size());
		assertEquals("First element should be 2", 2, emptyPQ2.getElement(0));

		// Now that this priority queue is no longer empty, insert a few elements
		// Size is now increased
		emptyPQ2.insert(1);
		emptyPQ2.insert(3);
		emptyPQ2.insert(10);
		emptyPQ2.insert(15);
		emptyPQ2.insert(8);
		assertEquals("Size should increase from 1 to 6", 6, emptyPQ2.size());

		// Expect swapping to occur so that if child is larger than parent, swap them
		// (since insert() calls increaseValue() method that calls swap()). Draw out the
		// tree and track the algorithm, the expected value of index 0 to 5 is as
		// followed: 15-10-8-1-3-2
		assertEquals("First element should be 15", 15, emptyPQ2.getElement(0));
		assertEquals("Second element should be 10", 10, emptyPQ2.getElement(1));
		assertEquals("Third element should be 8", 8, emptyPQ2.getElement(2));
		assertEquals("Fourth element should be 1", 1, emptyPQ2.getElement(3));
		assertEquals("Fifth element should be 3", 3, emptyPQ2.getElement(4));
		assertEquals("Sixth element should be 2", 2, emptyPQ2.getElement(5));
		assertEquals("Seventh element should be null", null, emptyPQ2.getElement(6));
	}

	@Test(expected = Error.class)
	public void insertTest2() {
		// Test if insert() throws an error when heap size has reached capacity limit.
		// If test passes, insert() does actually throw an error

		// Case 1: an empty queue created with capacity of 1. Try adding 2 items
		PriorityQueueBH<Integer> empty = new PriorityQueueBH<Integer>(1);
		empty.insert(30);
		empty.insert(30);

		// Case 2: a fully filled queue created with an array. Try adding another item
		Integer[] array2 = new Integer[] { 2, 1, 3, 10, 15, 8 };
		PriorityQueueBH<Integer> pq3 = new PriorityQueueBH<Integer>(array2);
		pq3.insert(30);
	}

	@Test
	public void afterMaximumTest() {
		// Case 1: If the queue is empty, value after maximum should be null
		assertEquals("After max in an empty queue is null", null, emptyPQ.afterMaximum());

		// Case 2: If there is 1 element, element after max should be null
		Integer[] array = new Integer[] { 10 };
		PriorityQueueBH<Integer> pq1 = new PriorityQueueBH<Integer>(array);
		assertEquals("After max should return null", null, pq1.afterMaximum());

		// Case 3: If there are 2 elements, element after maximum should be returned
		Integer[] array0 = new Integer[] { 10, 2 };
		PriorityQueueBH<Integer> pq2 = new PriorityQueueBH<Integer>(array0);
		assertEquals("After max should be 2", 2, pq2.afterMaximum());

		// Case 4: If there are 3 or more elements, the second largest is returned.
		Integer[] array2 = new Integer[] { 2, 1, 3 };
		PriorityQueueBH<Integer> pq3 = new PriorityQueueBH<Integer>(array2);
		assertEquals("Expect after max to be 2", 2, pq3.afterMaximum());

		Integer[] array3 = new Integer[] { 2, 3, 4, 5, 1 };
		PriorityQueueBH<Integer> pq4 = new PriorityQueueBH<Integer>(array3);
		assertEquals("Expect after max to be 4", 4, pq4.afterMaximum());
	}

	@Test
	public void isExtractedTest() {
		// Test an empty queue
		assertEquals("Expect nothing is extracted in an empty queue", false, emptyPQ.isExtracted());

		// Create and test a filled priority queue
		Integer[] array = new Integer[] { 2, 1, 3, 10, 15, 8 };
		PriorityQueueBH<Integer> pq = new PriorityQueueBH<Integer>(array);
		assertEquals("Expect false value of isExtracted()", false, pq.isExtracted());

		// Try extracting, expect true value for isExtracted()
		pq.extractMax();
		assertEquals("There should be extraction", true, pq.isExtracted());
	}

	@Test
	public void sizeTest() {

		// We expect empty queue has size 0, and filled queue has size 6
		assertEquals("Empty size test", 0, emptyPQ.size());
		assertEquals("Non-empty size test", 6, pq.size());
	}

	@Test
	public void getElementTest() {

		// Expect to get value 8 at index 2 in a certain priority queue
		assertEquals("Element 2 should be 8", 8, pq.getElement(2));

		// Expect to get null in an empty queue
		assertEquals("Element 2 should be null", null, emptyPQ.getElement(2));
	}

	@Test
	public void toStringTest() {

		// Expect correct string representation of a priority queue
		Integer[] array = new Integer[] { 2, 1, 3, 10, 15, 8 };
		PriorityQueueBH<Integer> pq = new PriorityQueueBH<Integer>(array);
		assertEquals("Expect string representation to be 15 10 8 2 1 3 ", "15 10 8 2 1 3 ", pq.toString());
	}

}
